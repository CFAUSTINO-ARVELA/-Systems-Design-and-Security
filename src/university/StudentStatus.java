package university;

import java.sql.*;

public class StudentStatus {
	
	private int registrationNumber;
	private int level;
	private int period;
	private Date startDate;
	private Date endDate;
	
	public StudentStatus createStudentStatus() throws SQLException {
		
		Connection con = null;
		Statement stmt = null;

		try {
			con = DriverManager.getConnection("jdbc:mysql://stusql.dcs.shef.ac.uk/team002", "team002", "e8f208af");
			stmt = con.createStatement();
			String query = String.format("INSERT INTO studentStatus (registrationNumber, level, period) VALUES (\"%d\", \"%d\", \"%d\");",
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
	public int getLevel() {
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
	
}
