package university;

import java.sql.*;

public class Student {

    private Degree degree;
    private int registrationNumber;
    private String tutor; // needs to be type Teacher
    private Account accountDetails;

    public Student(Degree deg, String tut, Account acc) {  
      this.degree = deg;
      
      try {
    	  this.registrationNumber = this.generateRegistrationNumber();
      } catch (SQLException ex) {
    	  ex.printStackTrace();
      }
      
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
					this.registrationNumber, this.degree.getCode(), this.tutor, this.accountDetails.getUsername());
			System.out.println(query);
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

    public Degree getDegree() {
        return this.degree;
    }
    
    public int generateRegistrationNumber() throws SQLException {
    	
    	Connection con = null;
    	Statement stmt = null;
    	int count = 1;
    	
    	try {
			con = DriverManager.getConnection("jdbc:mysql://stusql.dcs.shef.ac.uk/team002", "team002", "e8f208af");

			stmt = con.createStatement();
			String query = "SELECT COUNT(*) FROM student;";
			
			ResultSet res = stmt.executeQuery(query);
			
			res.next();
			count = res.getInt(1);
    	} catch (SQLException ex) {
    		ex.printStackTrace();
    	} finally {
    		if (stmt != null)
    			stmt.close();
    	}
    	
    	return (count + 1);
    }
    
    public int getRegistrationNumber() {
    	return this.registrationNumber;
    }
    
    public String getTutor() {
    	return this.tutor;
    }
}