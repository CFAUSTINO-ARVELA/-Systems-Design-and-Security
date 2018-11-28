package university;

import java.sql.*;
import java.util.ArrayList;

public class StudentStatus {
	
	private int registrationNumber;
	private char level;
	private String period;
	private Date startDate;
	private Date endDate;
	private boolean registered;
	private boolean resitting = false;
	private boolean graduated = false;
	private static Connection con = null;
	
    public static void connectToDB() throws SQLException {
		   try {
			   con = DriverManager.getConnection("jdbc:mysql://stusql.dcs.shef.ac.uk/team002", "team002", "e8f208af");
		   }
		   catch(Exception ex) {
			   ex.printStackTrace();
		   }
	}
	
	public StudentStatus(int r, char l, String p, boolean reg, boolean g) {
		this.registrationNumber = r;
		this.level = l;
		this.period = p;
		this.registered = reg;
		this.graduated = g;
	}
	
	public boolean isRegistered() {
		return registered;
	}

	public boolean isResitting() {
		return resitting;
	}

	public boolean isGraduated() {
		return graduated;
	}
	
	public StudentStatus createStudentStatus() throws SQLException {
		
		Connection con = null;
		Statement stmt = null;

		try {
			con = DriverManager.getConnection("jdbc:mysql://stusql.dcs.shef.ac.uk/team002", "team002", "e8f208af");
			stmt = con.createStatement();
			String query = String.format("INSERT INTO studentStatus (registrationNumber, level, period, registered) VALUES (%d, \"%s\", \"%s\", %b);",
					this.registrationNumber, this.level, this.period, false);
			int count = stmt.executeUpdate(query);
					

			System.out.println(count);
		} catch (Exception ex) {
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
	public String getPeriod() {
		return period;
	}
	public Date getStartDate() {
		return startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	
	public void setGraduated() throws SQLException {
		connectToDB();
		Statement stmt = null;

		try {
			
			stmt = con.createStatement();
			int count = stmt.executeUpdate(
					String.format("UPDATE studentStatus SET graduated = true WHERE registrationNumber = %d;", this.registrationNumber));
			
			System.out.println(count);
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (stmt != null)
				stmt.close();
		}
		con.close();
	}
	
	public void setResitting(boolean b) throws SQLException {
		connectToDB();
		Statement stmt = null;

		try {
			
			stmt = con.createStatement();
			int count = stmt.executeUpdate(
					String.format("UPDATE studentStatus SET resitting = %b WHERE registrationNumber = %d;", b, this.registrationNumber));
			
			System.out.println(count);
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (stmt != null)
				stmt.close();
		}
		con.close();
	}
	
	public ArrayList<ModuleChoice> getCurrentModules() throws SQLException {
		Connection con = null;
		Statement stmt = null;
		String code = null;
		String period = null;
		ModuleChoice module = null;
		ArrayList<ModuleChoice> modules = new ArrayList<ModuleChoice>();
		
		int periodValue = this.period.charAt(0);
		String prevPeriod = String.valueOf((char)(periodValue - 1));
		
		try {
			con = DriverManager.getConnection("jdbc:mysql://stusql.dcs.shef.ac.uk/team002", "team002", "e8f208af");
			stmt = con.createStatement();
			ResultSet res = stmt.executeQuery(String.format("SELECT moduleCode, period FROM moduleChoice WHERE registrationNumber = %d;", this.registrationNumber));
			
			while (res.next()) {
				code = res.getString("moduleCode");
				period = res.getString("period");
				
				if (period.equals(this.period)) {
					
					if (!this.resitting || !period.equals(prevPeriod)) {
						module = new ModuleChoice(this.registrationNumber, code, period);
						modules.add(module);
					}
				}
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
	
	public ArrayList<ModuleChoice> getAllModules() throws SQLException {
		Connection con = null;
		Statement stmt = null;
		String code = null;
		int grade;
		ModuleChoice module = null;
		ArrayList<ModuleChoice> modules = new ArrayList<ModuleChoice>();
		
		try {
			con = DriverManager.getConnection("jdbc:mysql://stusql.dcs.shef.ac.uk/team002", "team002", "e8f208af");
			stmt = con.createStatement();
			ResultSet res = stmt.executeQuery(String.format("SELECT moduleCode, period, grade FROM moduleChoice WHERE registrationNumber = %d;", this.registrationNumber));
			
			while (res.next()) {
				code = res.getString("moduleCode");
				period = res.getString("period");
				
				if (!period.equals(this.period)) {
					grade = res.getInt("grade");
					module = new ModuleChoice(this.registrationNumber, period, code, grade);
					modules.add(module);
				} else {
					module = new ModuleChoice(this.registrationNumber, period, code);
					modules.add(module);
				}
				
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
	
	public void updateStatus(char l, String p) throws SQLException {
		connectToDB();
		Statement stmt = null;

		try {
			
			stmt = con.createStatement();
			int count = stmt.executeUpdate(
					String.format("UPDATE studentStatus SET level = \"%s\", period = \"%s\", registered = 0 WHERE registrationNumber = %d;", l, p, this.registrationNumber));
			
			System.out.println(count);
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			if (stmt != null)
				stmt.close();
		}
		con.close();
	}
	
	public void setRegistered(boolean r) throws SQLException {
		
		connectToDB();
		Statement stmt = null;

		try {
			
			stmt = con.createStatement();
			int count = stmt.executeUpdate(
					String.format("UPDATE studentStatus SET registered = %b WHERE registrationNumber = %d;", r, this.registrationNumber));
			
			System.out.println(count);
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			if (stmt != null)
				stmt.close();
		}	
		con.close();
	}
}
