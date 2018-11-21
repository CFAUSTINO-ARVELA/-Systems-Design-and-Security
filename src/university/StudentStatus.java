package university;

import java.sql.*;
import java.util.ArrayList;

public class StudentStatus {
	
	private int registrationNumber;
	private char level;
	private char period;
	private Date startDate;
	private Date endDate;
	
	public StudentStatus createStudentStatus() throws SQLException {
		
		Connection con = null;
		Statement stmt = null;

		try {
			con = DriverManager.getConnection("jdbc:mysql://stusql.dcs.shef.ac.uk/team002", "team002", "e8f208af");
			stmt = con.createStatement();
			String query = String.format("INSERT INTO studentStatus (registrationNumber, level, period) VALUES (%d, \"%s\", \"%s\");",
					this.registrationNumber, this.level, this.period);
			int count = stmt.executeUpdate(query);
					

			System.out.println(count);
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			if (stmt != null)
				stmt.close();
		}
		
		return this;
	}
	
	public int getRegistrationNumber() {
		return registrationNumber;
	}
	public char getLevel() {
		return level;
	}
	public int getPeriod() {
		return period;
	}
	public Date getStartDate() {
		return startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	
	public ArrayList<Module> getCurrentModules() throws SQLException {
		Connection con = null;
		Statement stmt = null;
		String code = null;
		Module module = null;
		ArrayList<Module> modules = new ArrayList<Module>();
		
		try {
			con = DriverManager.getConnection("jdbc:mysql://stusql.dcs.shef.ac.uk/team002", "team002", "e8f208af");
			stmt = con.createStatement();
			ResultSet res = stmt.executeQuery(String.format("SELECT moduleCode FROM moduleChoice WHERE registrationNumber = %d;", this.registrationNumber));
			
			while (res.next()) {
				code = res.getString("moduleCode");
				module = Module.getModule(code);
				modules.add(module);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (stmt != null) {
				stmt.close();
			}
		}
		
		return modules;
	}
	
}
