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

	public static String generateCode(Department dep) throws Exception {
		connectToDB();
		Statement stmt = con.createStatement();
		String name = dep.getName();
		ResultSet res  = stmt.executeQuery(String.format("SELECT code FROM department WHERE name = \"%s\"", name));
		res.next();
		
		if (stmt != null) {
			stmt.close();
		}
 		return res.getString("code");
	}
	
	//Connect to the database
	public static void connectToDB() throws Exception {
		   try {
			   con = DriverManager.getConnection("jdbc:mysql://stusql.dcs.shef.ac.uk/team002", "team002", "e8f208af");
		   }
		   catch(SQLException ex) {
			   ex.printStackTrace();
		   }
	}
	
	//Creates module
	public Module createModule() throws Exception {
		connectToDB();
		PreparedStatement mod, newMod = null;
		int count = 0;
		mod = con.prepareStatement("SELECT COUNT(*) FROM module WHERE code = ?");
		newMod = con.prepareStatement("INSERT INTO module VALUES (?,?,?,?)");
		
		try {
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
		catch (SQLException ex) {
			ex.printStackTrace();
		}
		finally {
			if (newMod != null)
				newMod.close();
		}
		con.close();
		return this;
	}
	
	//Delete module
	public void deleteModule() throws Exception {
		int count = 0;
		connectToDB();
		PreparedStatement modCount, delMod = null;
		modCount = con.prepareStatement("SELECT COUNT(*) FROM module WHERE code = ?");
		delMod = con.prepareStatement("DELETE FROM module WHERE code = ?");
		
		try {
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
				delMod.close();
		}
		con.close();
	}
	
	public static Module getModule(String c) throws Exception {
		System.out.println(c);
		Module module = null;
		
		connectToDB();
		PreparedStatement mod,noMod = null;
		noMod = con.prepareStatement("SELECT COUNT(*) FROM module WHERE code = ?");
		mod = con.prepareStatement("SELECT * FROM module WHERE code =  ?");
		Statement stmt = con.createStatement();
		
		try {
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
			
			
		 }catch (SQLException ex) {
			 ex.printStackTrace();
		 }finally {
				if (stmt != null)
					stmt.close();
			}
		con.close();
		return module;
	}
	
	public boolean checkApproval(String d, String l) {
		return true; // Write an actual function here
	}

}
