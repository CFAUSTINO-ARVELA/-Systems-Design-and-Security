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

	public static String generateCode(Department dep, int level) throws Exception {
		connectToDB();
		Statement stmt = con.createStatement();
		String name = dep.getName();
		ResultSet res  = stmt.executeQuery(String.format("SELECT code FROM department WHERE name = \"%s\";", name));
		res.next();
		String deptCode = res.getString("code");
		
		String codeSoFar = deptCode + Integer.toString(level);
		
		res = stmt.executeQuery(String.format("SELECT COUNT(*) FROM module WHERE code LIKE \"%s%%\";", codeSoFar));
		res.next();
		int count = res.getInt(1) + 1;
		
		String formatted = String.format("%03d", count);
		String finalCode = codeSoFar + formatted;
		
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
	public Module createModule() throws Exception {
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
			
			}
		catch (Exception ex) {
			ex.printStackTrace();
		}
		finally {
			if (newMod != null)
				mod.close();
				newMod.close();
		}
		con.close();
		return this;
	}
	
	//Delete module
	public void deleteModule() throws Exception {
		int count = 0;
		connectToDB();
		PreparedStatement modCount = null, delMod = null;
		
		try {
			modCount = con.prepareStatement("SELECT COUNT(*) FROM module WHERE code = ?");
			delMod = con.prepareStatement("DELETE FROM module WHERE code = ?");
			modCount.setString(1, getCode());
			ResultSet res = modCount.executeQuery();
			res.next();
			if (res.getInt(1) == 1) {
				delMod.setString(1, getCode());
				count = delMod.executeUpdate();
			}
			}
		catch (SQLException ex) {
			ex.printStackTrace();
			}
		finally {
			if ( delMod != null)
				modCount.close();
				delMod.close();
		}
		con.close();
	}
	
	public static Module getModule(String c) throws Exception {
		System.out.println(c);
		Module module = null;
		
		connectToDB();
		PreparedStatement mod = null, noMod = null;
		Statement stmt = con.createStatement();
		
		try {
			noMod = con.prepareStatement("SELECT COUNT(*) FROM module WHERE code = ?");
			mod = con.prepareStatement("SELECT * FROM module WHERE code =  ?");
			noMod.setString(1, c);
			System.out.println(noMod);
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
			
			
		 }catch (Exception ex) {
			 ex.printStackTrace();
		 }finally {
				if (stmt != null)
					noMod.close();
					mod.close();
					stmt.close();
			}
		con.close();
		return module;
	}
	
	public static int assignModule(String d, String m, int l, boolean c) throws Exception {
		connectToDB();
		
		Statement stmt = con.createStatement();
		System.out.println(d + m + l + c);
		String query = String.format("INSERT INTO assoModDeg (degCode, modCode, year, mandatory) VALUES (\"%s\", \"%s\", %d, %b);", d, m, l, c);
		//System.out.println(query);
		int count = stmt.executeUpdate(query);
		System.out.println("AssignModule " + count);
		if (stmt != null) {
			stmt.close();
		}
		
		con.close();
		return count;
	}
	
	public static void remAssoModDeg(String modCode,String degCode, int year) throws Exception {
		connectToDB();
		
		PreparedStatement query = null;
		try {
			query = con.prepareStatement("DELETE FROM assoModDeg WHERE modCode = ? AND degCode = ? AND year = ? ");
			query.setString(1, modCode);
			query.setString(2, degCode);
			query.setInt(3, year);
			System.out.println(query.toString());
			System.out.println(query.executeUpdate());
			
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
