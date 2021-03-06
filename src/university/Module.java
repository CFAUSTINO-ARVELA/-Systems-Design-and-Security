package university;
import java.sql.*;
import java.util.ArrayList;

public class Module {
	
	private String name;
	private String code;
	private int credits;
	private String duration;
	private static Connection con;
	
	//Constructor for module
	public Module(String name, String code, int credits, String duration) {
		this.name = name;
		this.code = code;
		this.credits = credits;
		this.duration = duration;
	}

	public Module(String code) {
		this.code = code;
	}
	
	//Get module code
	public String getCode() {
		return code;
	}
	
	//Get module name
		public String getName() {
			return name;
		}
	
	//Get module code
	public int getCredits() {
		return credits;
	}
	
	//Get module  duration
	public String getDuration() {
		return duration;
	}

	public static String generateCode(Department dep, int level) throws SQLException {
		connectToDB();
		int count = 001;
		Statement stmt = con.createStatement();
		String name = dep.getName();
		ResultSet res  = stmt.executeQuery(String.format("SELECT code FROM department WHERE name = \"%s\";", name));
		res.next();
		String deptCode = res.getString("code");
		
		String codeSoFar = deptCode + Integer.toString(level);
		
		res = stmt.executeQuery(String.format("SELECT MAX(Code) FROM module WHERE code LIKE \"%s%%\";", codeSoFar));
		
		
		
		if(res.next() && res.getString(1) != null ) {

			count = Integer.parseInt( res.getString(1).substring(4)) + 1;
		}
		
		
		String formatted = String.format("%03d", count);
		String finalCode = codeSoFar + formatted;
		
		res.close();
		if (stmt != null) {
			stmt.close();
		}
		con.close();
 		return finalCode;
	}
	
	//Connect to the database
	public static void connectToDB() throws SQLException {
		   try {
			   con = DriverManager.getConnection("jdbc:mysql://stusql.dcs.shef.ac.uk/team002", "team002", "e8f208af");
		   }
		   catch(Exception ex) {
			   ex.printStackTrace();
		   }
	}
	
	//Creates module
	public Module createModule() throws SQLException {
		connectToDB();
		PreparedStatement mod = null, newMod = null;
		int count = 0;
		
		try {
			mod = con.prepareStatement("SELECT COUNT(*) FROM module WHERE code = ?");
			newMod = con.prepareStatement("INSERT INTO module VALUES (?,?,?,?)");
			mod.setString(1, getName());
			ResultSet res = mod.executeQuery();
			
			res.next();
			if(res.getInt(1) == 0) {
				newMod.setString(1, getName());
				newMod.setString(2, getCode());
				newMod.setInt(3, getCredits());
				newMod.setString(4, getDuration());
				count += newMod.executeUpdate();
			}
			res.close();
		}
		catch (SQLException ex) {

			ex.printStackTrace();
		}
		finally {
			if (newMod != null) {
				mod.close();
				newMod.close();
			}
		}
		con.close();
		return this;
	}
	
	//Delete module
	public void deleteModule() throws SQLException {
		int count = 0;
		connectToDB();

		PreparedStatement modCount, delMod, delAssoMod,assoCount = null;
		modCount = con.prepareStatement("SELECT COUNT(*) FROM module WHERE code = ?");
		delMod = con.prepareStatement("DELETE FROM module WHERE code = ?");
		assoCount = con.prepareStatement("SELECT COUNT(*)FROM assoModDeg WHERE degCode = ?");
		delAssoMod = con.prepareStatement("DELET FROM assoModDeg WHERE degCode = ?");

		try {
			modCount = con.prepareStatement("SELECT COUNT(*) FROM module WHERE code = ?");
			delMod = con.prepareStatement("DELETE FROM module WHERE code = ?");
			modCount.setString(1, getCode());
			ResultSet res = modCount.executeQuery();
			res.next();
			if (res.getInt(1) == 1) {
				delMod.setString(1, getCode());
				count = delMod.executeUpdate();
				
				assoCount.setString(1, getCode());
				res = assoCount.executeQuery();
				res.next();
				if (res.getInt(1) >= 1) {
					delAssoMod.setString(1, getCode());
					count += delAssoMod.executeUpdate();
				}
			}
			res.close();
		}
		catch (SQLException ex) {
			ex.printStackTrace();
			}
		finally {
			if ( delMod != null) {
				modCount.close();
				delMod.close();
				assoCount.close();
				delAssoMod.close();
			}
		}
		con.close();
	}
	
	public static Module getModule(String c) throws SQLException {
		Module module = null;
		
		connectToDB();
		PreparedStatement mod = null, noMod = null;
		
		
		try {
			noMod = con.prepareStatement("SELECT COUNT(*) FROM module WHERE code = ?");
			mod = con.prepareStatement("SELECT * FROM module WHERE code =  ?");
			noMod.setString(1, c);
			ResultSet res1 = noMod.executeQuery();
			res1.next();
			
			if(res1.getInt(1) != 0) {
				mod.setString(1, c);
				ResultSet res = mod.executeQuery();
				res.next();
				String code = res.getString("code");
				String name = res.getString("name");
				String duration = res.getString("duration");
				int credits = res.getInt("credits");
				
				module = new Module(name, code, credits, duration);
				res.close();
				res1.close();
			}

			res1.close();
		 }catch (SQLException ex) {

			 ex.printStackTrace();
		 }finally {
				if (noMod != null) {
					noMod.close();
					mod.close();
					}
					
			}
		con.close();
		return module;
	}
	
	public static int assignModule(String d, String m, int l, boolean c) throws SQLException {
		connectToDB();
		
		Statement stmt = con.createStatement();
		String query = String.format("INSERT INTO assoModDeg (degCode, modCode, year, mandatory) VALUES (\"%s\", \"%s\", %d, %b);", d, m, l, c);
		int count = stmt.executeUpdate(query);
		if (stmt != null) {
			stmt.close();
		}
		
		con.close();
		return count;
	}
	
	public static void remAssoModDeg(String modCode,String degCode, int year) throws SQLException {
		connectToDB();
		
		PreparedStatement query = null;
		try {
			query = con.prepareStatement("DELETE FROM assoModDeg WHERE modCode = ? AND degCode = ? AND year = ? ");
			query.setString(1, modCode);
			query.setString(2, degCode);
			query.setInt(3, year);
			query.executeUpdate();
		} catch (Exception ex) {
			ex.printStackTrace();
		}finally {
			if ( query != null)
				query.close();
		}

		con.close();
				

		
	}
	
	public boolean checkApproval(String d, String l) {
		return true; // Write an actual function here
	}
	
	public static boolean checkCredits(ArrayList<Module> list, boolean postgrad) {
		int creditTotal = 0;
		int credits = 0;

		for (Module module : list) {
			credits = module.getCredits();
			creditTotal += credits;
		}
		
		if (postgrad && creditTotal == 180) {
			return true;
		} else if (!postgrad && creditTotal == 120) {
			return true;
		} else {
			return false;
		}
	}
	
	public static ArrayList<ArrayList<String>> getModList() throws SQLException  {
		ArrayList<ArrayList<String>> modList = new ArrayList<ArrayList<String>>();
		ArrayList<String> mod = new ArrayList<String>();
		ResultSet res = null;
		PreparedStatement dept = null;
		connectToDB();
		try {
			dept = con.prepareStatement("SELECT * FROM module;");
			res  = dept.executeQuery();
			mod.add("Name");
			mod.add("Code");
			mod.add("Credits");
			mod.add("Duration");
			modList.add((ArrayList) mod.clone());
			while (res.next()) {
				mod.clear();
				mod.add(res.getString("name"));
				mod.add(res.getString("code"));
				mod.add(res.getString("credits"));
				mod.add(res.getString("duration"));
				modList.add((ArrayList) mod.clone());
				
			}
			res.close();
			
		 }catch (SQLException ex) {
			 ex.printStackTrace();
		 }finally {
				if (dept != null)
					dept.close();
			}
		con.close();
		return modList;
	} 

}
