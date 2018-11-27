package university;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PeriodResult {
	
	private int registrationNumber;
	private char level;
	private String period;
	private int grade;
	private boolean passed;
	
	private static Connection con = null;
	
	public static void connectToDB() throws SQLException {
		   try {
			   con = DriverManager.getConnection("jdbc:mysql://stusql.dcs.shef.ac.uk/team002", "team002", "e8f208af");
		   }
		   catch(SQLException ex) {
			   ex.printStackTrace();
		   }
	}
	
	public int createPeriodResult() throws SQLException {
		connectToDB();
		int count = 0;
		PreparedStatement newresult, result = null;
		result = con.prepareStatement("SELECT COUNT(*) FROM periodResult WHERE registrationNumber = ?");
		newresult = con.prepareStatement( "INSERT INTO periodResult VALUES (?, ?, ?, ?, ?)");
		try {
			result.setInt(1, this.registrationNumber);
			ResultSet res = result.executeQuery();
			res.next();
			
			if (res.getInt(1) == 0) {
				newresult.setInt(1, this.registrationNumber);
				newresult.setString(3, Character.toString(this.level));
				newresult.setString(2, this.period);
				newresult.setInt(4, this.grade);
				newresult.setBoolean(5, this.passed);
				count = newresult.executeUpdate();
			}	
		    
		 }catch (SQLException ex) {
			 ex.printStackTrace();
		 }finally {
				if (newresult != null)
					newresult.close();
					result.close();
		}
		con.close();
		return count;
	}
	
	public PeriodResult(int r, char l, String p, int g, boolean pass) {
		this.registrationNumber = r;
		this.level = l;
		this.period = p;
		this.grade = g;
		this.passed = pass;
	}
	
	public int getRegistrationNumber() {
		return registrationNumber;
	}
	
	public char getLevel() {
		return level;
	}
	
	public String getPeriod() {
		return period;
	}
	
	public int getGrade() {
		return grade;
	}
	
	public boolean isPassed() {
		return passed;
	}
	
	

}
