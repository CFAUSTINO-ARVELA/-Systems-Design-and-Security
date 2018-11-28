package university;

import java.sql.*;
import java.util.ArrayList;

public class Degree {
	private String code;
	private String name;
	private Department mainDept;
	private ArrayList<Department> seconDepts;
	private String type;
	private Boolean placement;
	private static Connection con;

	// Constructor for degree with just one department
	public Degree(String name, Department mainDept, String type, Boolean placement) throws SQLException {
		this.name = name;
		this.mainDept = mainDept;
		this.type = type;
		this.placement = placement;
		this.seconDepts = new ArrayList<Department>();
	}

	// Constructor for degree with several departments
	public Degree(String name, Department mainDept, ArrayList<Department> seconDepts, String type, Boolean placement)
			throws SQLException {
		this.name = name;
		this.mainDept = mainDept;
		this.seconDepts = seconDepts;
		this.type = type;
		this.placement = placement;
	}

	public Degree(String code) {
		this.code = code;
	}

	// Connect to the database
	public static void connectToDB() throws SQLException {
		try {
			con = DriverManager.getConnection("jdbc:mysql://stusql.dcs.shef.ac.uk/team002", "team002", "e8f208af");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public void setCode() throws SQLException {
		int no = 1;

		String degCode = getMainDept().getCode();
		if (getType() == "MSc")
			degCode += "P";
		else
			degCode += "U";
		// System.out.println(degCode+"%");
		connectToDB();
		PreparedStatement noDeg = null;
		try {
			noDeg = con.prepareStatement("SELECT MAX(code) FROM degree WHERE mainDept =  ? AND code LIKE ?");
			noDeg.setString(1, getMainDept().getCode());
			noDeg.setString(2, degCode + "%");
			// System.out.println(noDeg.toString());
			ResultSet res = noDeg.executeQuery();
			res.next();
			// System.out.println(res.getString("MAX(code)"));
			// System.out.println(res.getString("MAX(code)") == null);
			// System.out.println(res.getString(0).isEmpty()+ "Aqui");
			if (res.getString("MAX(code)") != null)
				no = Integer.parseInt(res.getString("MAX(code)").substring(4)) + 1;
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (noDeg != null)
				noDeg.close();
		}

		degCode += String.format("%02d", no);
		con.close();
		setCode(degCode);
	}

	public void setCode(String c) {
		this.code = c;
	}

	// get code of Degree
	public String getCode() {
		return code;
	}

	// get name of Degree
	public String getName() {
		return name;
	}

	// get main department of Degree
	public Department getMainDept() {
		return mainDept;
	}

	// get list of secondary department of Degree
	public ArrayList<Department> getSeconDepts() {
		return seconDepts;
	}

	// get type of Degree (Undergraduate or Postgraduate
	public String getType() {
		return type;
	}

	// get if Degree has placement year
	public Boolean getPlacement() {
		return placement;
	}

	// Create DegrEe with just one department
	@SuppressWarnings("resource")
	public int createDegree() throws SQLException {
		connectToDB();
		PreparedStatement newDeg = null, deg = null, checkDeser = null, newSeconDep = null;
		int count = 0;

		try {
			deg = con.prepareStatement("SELECT COUNT(*) FROM degree WHERE name = ?");
			newDeg = con
					.prepareStatement("INSERT INTO degree (code, name, mainDept, type, placement)  VALUES (?,?,?,?,?)");
			newSeconDep = con.prepareStatement("INSERT INTO seconDepts VALUES(?,?)");
			checkDeser = con.prepareStatement("SELECT code FROM module WHERE name = \"Dissertation\" AND code LIKE ?");
			deg.setString(1, getName());
			ResultSet res = deg.executeQuery();

			res.next();
			if (res.getInt(1) == 0) {
				newDeg.setString(1, getCode());
				newDeg.setString(2, getName());
				newDeg.setString(3, getMainDept().getCode());
				newDeg.setString(4, getType());
				newDeg.setBoolean(5, getPlacement());
				count = newDeg.executeUpdate();
				if (!this.seconDepts.isEmpty()) {
					newSeconDep.setString(1, getCode());
					for (Department str : getSeconDepts()) {
						newSeconDep.setString(2, str.getCode());
						count += newSeconDep.executeUpdate();
					}
				}

				if (getType().charAt(0) == 'M') {
					checkDeser.setString(1, getMainDept().getCode() + "4%");
					res = checkDeser.executeQuery();
					if (!res.next()) {
						String code = Module.generateCode(getMainDept(), 4);
						Module m = new Module("Dissertation", code, 60, "Summer");
						m.createModule();
						Module.assignModule(getCode(), m.getCode(), 4, true);
					} else {
						Module.assignModule(getCode(), res.getString(1), 4, true);
					}
					res.close();
				} else {
					checkDeser.setString(1, getMainDept().getCode() + "3%");
					res = checkDeser.executeQuery();
					Boolean empty = res.next();
					// System.out.println(empty + checkDeser.toString());
					if (!empty) {
						String code = Module.generateCode(getMainDept(), 3);
						Module m = new Module("Dissertation", code, 40, "Year");
						System.out.println("1");
						System.out.println(m.createModule());
						System.out.println("2");
						System.out.println(Module.assignModule(getCode(), m.getCode(), 3, false));
					} else {
						System.out.println("3");
						Module.assignModule(getCode(), res.getString(1), 3, false);
					}
					res.close();
				}

			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (newDeg != null)
				deg.close();
			newDeg.close();
			newSeconDep.close();
			checkDeser.close();
		}
		con.close();
		return count;
	}

	// Delete degree with code and name (just to be safe)
	public int deleteDegree() throws SQLException {
		int count = 0;
		connectToDB();
		PreparedStatement delDeg = null, delSDept = null, deg = null, delAssoMod = null, countAsso = null;

		try {
			deg = con.prepareStatement("SELECT COUNT(*) FROM degree WHERE code = ?");
			delDeg = con.prepareStatement("DELETE FROM degree WHERE code = ?");
			delSDept = con.prepareStatement("DELETE FROM seconDepts WHERE degreeCode = ?");
			countAsso = con.prepareStatement("SELECT COUNT(*) FROM assoModDeg WHERE degCode = ?");
			delAssoMod = con.prepareStatement("DELETE FROM assoModDeg WHERE degCode = ?");

			deg.setString(1, getCode());
			ResultSet res = deg.executeQuery();
			res.next();
			if (res.getInt(1) == 1) {
				delDeg.setString(1, getCode());
				count += delDeg.executeUpdate();

				delSDept.setString(1, getCode());
				count += delSDept.executeUpdate();
			}
			countAsso.setString(1, getCode());
			res = countAsso.executeQuery();
			res.next();
			if (res.getInt(1) >= 1) {
				delAssoMod.setString(1, getCode());
				count += delAssoMod.executeUpdate();

			}

			res.close();
		} catch (

		Exception ex) {
			ex.printStackTrace();
		} finally {
			if (delDeg != null)
				deg.close();
			delDeg.close();
			delSDept.close();
			countAsso.close();
			delAssoMod.close();
		}
		con.close();
		return count;
	}

	// Get a degree using the code (return a degree object)
	public static Degree getDegree(String c) throws SQLException {
		Degree degree = null;
		ArrayList<Department> deptList = new ArrayList<Department>();
		Department dep = null;
		// System.out.println(c);

		connectToDB();
		PreparedStatement deg = null, noDeg = null, secDep = null;

		try {
			noDeg = con.prepareStatement("SELECT COUNT(*) FROM degree WHERE code = ?");
			deg = con.prepareStatement(
					"SELECT * FROM degree JOIN department WHERE degree.mainDept=department.code AND degree.code =  ?");
			secDep = con.prepareStatement("SELECT dept FROM seconDepts WHERE degreeCode = ?");
			noDeg.setString(1, c);
			// System.out.println(noDeg);
			ResultSet res1 = noDeg.executeQuery();
			res1.next();

			if (res1.getInt(1) != 0) {
				deg.setString(1, c);
				ResultSet res = deg.executeQuery();
				res.next();
				String code = res.getString("code");
				String name = res.getString("degree.name");
				String mainDept = res.getString("mainDept");
				String type = res.getString("type");
				Boolean placement = res.getBoolean("placement");
				String depName = res.getString("department.name");
				dep = new Department(mainDept, depName);
				secDep.setString(1, c);
				ResultSet res2 = secDep.executeQuery();
				while (res2.next()) {
					String deptC = res2.getString("dept");
					deptList.add(dep.getDeptwCode(deptC));
				}

				degree = new Degree(name, dep, deptList, type, placement);
				degree.setCode(code);
				res.close();
				res1.close();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (deg != null) {
				noDeg.close();
				deg.close();
				secDep.close();
			}
		}
		con.close();
		return degree;
	}

	// Get all degrees
	public static ArrayList<String> getAllDegreeCodes() throws SQLException {
			ArrayList<String> degreeList = new ArrayList<String>();
			
			connectToDB();
			PreparedStatement deg = null;
			
			try {
				deg = con.prepareStatement("SELECT code FROM degree; " );
				ResultSet res  = deg.executeQuery();
				
				while(res.next()) {
					
					String dCode = res.getString("code");
					
		
					
					degreeList.add(dCode);
				}
				res.close();
				con.close();
			 }catch (SQLException ex) {
				 ex.printStackTrace();
			 }finally {
					if (deg != null)
						deg.close();
				}
			
			con.close();
			return degreeList;
			
		}

	public static ArrayList<String> getAllDegNames() throws SQLException {

			ArrayList<String> degreeList = new ArrayList<String>();
			
			connectToDB();
			PreparedStatement deg= null;
			
			try {
				deg = con.prepareStatement("SELECT name FROM degree; " );
				
				ResultSet res = deg.executeQuery();
				
				while(res.next()) {
					
					String dName = res.getString("name");
					
					degreeList.add(dName);
				}
				res.close();
			 }catch (Exception ex) {
				 ex.printStackTrace();
			 }finally {
					if (deg != null)
						deg.close();
				}
			
			con.close();
			
			return degreeList;
		}

	@SuppressWarnings("unchecked")
	public static ArrayList<ArrayList<String>> getDegList() throws SQLException {
		ArrayList<ArrayList<String>> degList = new ArrayList<ArrayList<String>>();
		ArrayList<String> deg = new ArrayList<String>();
		ResultSet res = null;
		PreparedStatement dept = null;
		connectToDB();
		try {
			dept = con.prepareStatement("SELECT * FROM degree;");
			res = dept.executeQuery();
			deg.add("Code");
			deg.add("Name");
			deg.add("Main Department");
			deg.add("Entry");
			deg.add("Placement");
			deg.add("Duration");
			degList.add((ArrayList<String>) deg.clone());
			while (res.next()) {
				deg.clear();
				deg.add(res.getString("code"));
				deg.add(res.getString("name"));
				deg.add(res.getString("mainDept"));
				if (res.getString("type").matches("MSc"))
					deg.add("Postgraduate");
				else
					deg.add("Undergraduate");
				if (res.getBoolean("placement") == true)
					deg.add("Yes");
				else
					deg.add("No");

				if (res.getString("type").matches("BSc") || res.getString("type").matches("BEng"))
					if (res.getBoolean("placement") == false)
						deg.add("3 Years");
					else
						deg.add("4 Years");
				else if (res.getString("type").matches("MSc"))
					deg.add("1 year");
				else if (res.getBoolean("placement") == false)
					deg.add("4 Years");
				else
					deg.add("5 Years");

				degList.add((ArrayList<String>) deg.clone());

			}
			res.close();

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (dept != null)
				dept.close();
		}
		con.close();
		return degList;
	}

	public ArrayList<ArrayList<String>> getDegModules() throws SQLException {
		ArrayList<ArrayList<String>> modList = new ArrayList<ArrayList<String>>();
		ArrayList<String> mod = new ArrayList<String>();
		ResultSet res = null;
		PreparedStatement getMod = null;
		connectToDB();
		try {
			getMod = con.prepareStatement(
					"SELECT modCode, name, mandatory,year,duration, credits  FROM assoModDeg JOIN module WHERE assoModDeg.modCode = module.code AND degCode = ?;");
			getMod.setString(1, getCode());
			res = getMod.executeQuery();
			mod.add("Code");
			mod.add("Name");
			mod.add("Type");
			mod.add("Year");
			mod.add("Duration");
			mod.add("Credits");
			modList.add((ArrayList<String>) mod.clone());
			while (res.next()) {
				mod.clear();
				mod.add(res.getString("modCode"));
				// System.out.println(res.getString("modCode") + " " +res.getString("name"));
				mod.add(res.getString("name"));
				if (res.getBoolean("mandatory") == true)
					mod.add("Core");
				else
					mod.add("Optional");
				mod.add(res.getString("year"));
				mod.add(res.getString("duration"));
				mod.add(res.getString("credits"));
				modList.add((ArrayList<String>) mod.clone());
			}

			res.close();

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (getMod != null)
				getMod.close();
		}
		con.close();
		return modList;
	}

	public static ArrayList<Module> getCoreModules(Degree d, int l) throws SQLException {
			ArrayList<Module> coreModules = new ArrayList<Module>();
			Module module = null;
			
			connectToDB();
			Statement stmt = con.createStatement();
			String degCode = d.getCode();
			//System.out.println(degCode);
			
			ResultSet res = stmt.executeQuery(String.format("SELECT modCode FROM assoModDeg WHERE degCode = \"%s\" AND year = %d AND mandatory = true;", degCode, l));
			
			while (res.next()) {
				module = Module.getModule(res.getString("modCode"));
				coreModules.add(module);
			}
			res.close();
			if (stmt != null) {
				stmt.close();
			}
			
			con.close();
			
			return coreModules;
		}

	public static ArrayList<Module> getOptionalModules(Degree d, int l) throws SQLException {
			ArrayList<Module> optionalModules = new ArrayList<Module>();
			Module module = null;
			
			connectToDB();
			Statement stmt = con.createStatement();
			String degCode = d.getCode();
			
			ResultSet res = stmt.executeQuery(String.format("SELECT modCode FROM assoModDeg WHERE degCode = \"%s\" AND year = %d AND mandatory = false;", degCode, l));
			
			while (res.next()) {
				module = Module.getModule(res.getString("modCode"));
				optionalModules.add(module);
			}
			
			res.close();
			if (stmt != null) {
				stmt.close();
			}
			
			con.close();

			return optionalModules;
		}

	public static int getCredits(String degreeCode, int level) throws SQLException {
		int totCredits = 0;
		connectToDB();
		PreparedStatement credSum = null;
		try {
			credSum = con.prepareStatement(
					"SELECT SUM(credits) FROM module JOIN assoModDeg WHERE degCode = ? AND year = ? AND mandatory = true;");
			credSum.setString(1, degreeCode);
			credSum.setInt(2, level);
			ResultSet res = credSum.executeQuery();
			res.next();
			if (res.getInt(1) != 0) {
				totCredits = res.getInt(1);
			}
			res.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (credSum != null)
				credSum.close();
		}

		con.close();

		return totCredits;
	}

	public static int getNoMod(String degreeCode, String moduleCode, int level) throws SQLException {
		int noMod = 0;
		connectToDB();
		PreparedStatement credSum = null;
		try {
			credSum = con.prepareStatement(
					"SELECT COUNT(*) FROM assoModDeg WHERE degCode = ? AND year = ? AND modCode = ?;");
			credSum.setString(1, degreeCode);
			credSum.setInt(2, level);
			credSum.setString(3, moduleCode);
			ResultSet res = credSum.executeQuery();
			res.next();
			if (res.getInt(1) != 0) {
				noMod = res.getInt(1);
			}
			res.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (credSum != null)
				credSum.close();
		}

		con.close();

		return noMod;
	}

}