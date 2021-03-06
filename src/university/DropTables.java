package university;
import java.sql.*;

public class DropTables {

	public static void main(String[] args) throws Exception {

		Connection con = null;
		Statement stmt = null;

		try {
			con = DriverManager.getConnection("jdbc:mysql://stusql.dcs.shef.ac.uk/team002", "team002", "e8f208af");
			stmt = con.createStatement();
			
			int count = stmt.executeUpdate("DROP TABLE student;");
			count += stmt.executeUpdate("DROP TABLE account;");
			count += stmt.executeUpdate("DROP TABLE studentStatus;");
			count += stmt.executeUpdate("DROP TABLE moduleChoice;");
			count += stmt.executeUpdate("DROP TABLE periodResult;");
			count += stmt.executeUpdate("DROP TABLE degreeResult;");

			int dep = stmt.executeUpdate("DROP TABLE department");
			int degree = stmt.executeUpdate("DROP TABLE degree");
			int module = stmt.executeUpdate("DROP TABLE module");
			int secDep = stmt.executeUpdate("DROP TABLE seconDepts");
			int levels = stmt.executeUpdate("DROP TABLE studyLevels"); 
			stmt.executeUpdate("DROP TABLE  assoModDeg");
		
//+ dep + degree
		
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			if (stmt != null)
				stmt.close();
				con.close();
		}
		
	}

}

