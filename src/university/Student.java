package university;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

class Student {

    private String degree; // needs to be converted to type Degree when created
    private int registrationNumber;
    private String tutor; // needs to be type Teacher
    private int level;
    private int period;
    private Account accountDetails;

    Student(String deg, int reg, String tut, Account acc) {  
      this.degree = deg;
      this.registrationNumber = reg;
      this.tutor = tut;
      this.accountDetails = acc;
    }
    
	public Student createStudent() throws SQLException {

		Connection con = null;
		Statement stmt = null;

		try {
			con = DriverManager.getConnection("jdbc:mysql://stusql.dcs.shef.ac.uk/team002", "team002", "e8f208af");
			stmt = con.createStatement();
			String query = String.format("INSERT INTO student (registrationNumber, degree, tutor, username) VALUES (\"%d\", \"%s\", \"%s\", \"%s\");",
					this.registrationNumber, this.degree, this.tutor, this.accountDetails.getUsername());
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

    public String getDegree() {
        return this.degree;
    }
    
    public int getRegistrationNumber() {
    	return this.registrationNumber;
    }
    
    public String getTutor() {
    	return this.tutor;
    }
    
    public int getLevel() {
    	return this.level;
    }
    
    public int getPeriod() {
    	return this.period;
    }
}