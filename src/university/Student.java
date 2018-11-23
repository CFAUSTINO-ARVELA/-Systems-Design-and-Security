package university;

import java.sql.*;
import java.util.ArrayList;

public class Student {

    private Degree degree;
    private int registrationNumber;
    private String tutor; // needs to be type Teacher
    private Account accountDetails;
    private static Connection con;

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
    
    public Student(int r) {
    	this.registrationNumber = r;
    }
    
    public static void connectToDB() throws SQLException {
		   try {
			   con = DriverManager.getConnection("jdbc:mysql://stusql.dcs.shef.ac.uk/team002", "team002", "e8f208af");
		   }
		   catch(SQLException ex) {
			   ex.printStackTrace();
		   }
	}
	
	public Student createStudent() throws SQLException {

		connectToDB();
		Statement stmt = null;

		try {
			
			stmt = con.createStatement();
			String query = String.format("INSERT INTO student (registrationNumber, degree, tutor, username) VALUES (%d, \"%s\", \"%s\", \"%s\");",
					this.registrationNumber, this.degree.getName(), this.tutor, this.accountDetails.getUsername());
			query = String.format("INSERT INTO studentStatus (registrationNumber, level, period) VALUES (%d, \"%s\", \"%s\");", this.registrationNumber, '1', 'A');
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
	
	public void deleteStudent() throws SQLException {
		
		connectToDB();
		Statement stmt = null;

		try {
			
			stmt = con.createStatement();
			int count = stmt.executeUpdate(
					String.format("DELETE FROM student WHERE registrationNumber = %d;", this.registrationNumber));
			count = stmt.executeUpdate(String.format("DELETE FROM studentStatus WHERE registrationNumber = %d;", this.registrationNumber));

			System.out.println(count);
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			if (stmt != null)
				stmt.close();
		}
	}

    public Degree getDegree() {
        return this.degree;
    }
    
    public int generateRegistrationNumber() throws SQLException {
    	
    	connectToDB();
    	Statement stmt = null;
    	int count = 1;
    	
    	try {
			

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
    
	public String getLevel() throws SQLException {
		int regNum = this.getRegistrationNumber();
		
		connectToDB();
		Statement stmt = null;
		String result = null;
		
		try {
			connectToDB();
			stmt = con.createStatement();
			ResultSet res = stmt.executeQuery(String.format("SELECT level FROM studentStatus WHERE registrationNumber = %d", regNum));
			
			res.next();
			result = res.getString("level");
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			if (stmt != null) {
				stmt.close();
			}
		}
		
		return result;
	}
	
	public static ArrayList<ArrayList<String>> getStutList() throws SQLException  {
		ArrayList<ArrayList<String>> stuList = new ArrayList<ArrayList<String>>();
		ArrayList<String> st = new ArrayList<String>();
		ResultSet res = null;
		PreparedStatement dept = null;
		connectToDB();
		dept = con.prepareStatement("SELECT * FROM department;");
		try {
			res  = dept.executeQuery();
			st.add("Registration No");
			st.add("Degree");
			st.add("Tutor");
			st.add("Username");
			stuList.add((ArrayList) st.clone());
			while (res.next()) {
				st.clear();
				st.add(res.getString("RegistrationNumber"));
				st.add(res.getString("Degree"));
				st.add(res.getString("Tutor"));
				st.add(res.getString("Username"));
				//System.out.println("Depart" + depart.toString());
				stuList.add((ArrayList) st.clone());
				//for (int o = 0; o < deptList.size(); o++) {
				//	System.out.println("deptList" + deptList.get(o).toString());
				//	}
			}
			res.close();
			
		 }catch (SQLException ex) {
			 ex.printStackTrace();
		 }finally {
				if (dept != null)
					dept.close();
			}
		con.close();
		return stuList;
	}
	
	public void progress(ArrayList<ModuleGrades> grades) {
		
	}
	
}