package university;
import java.sql.*;
import java.sql.DriverManager;
import java.sql.SQLException;

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
	
	public boolean checkApproval(String d, String l) {
		return true; // Write an actual function here
	}

}
