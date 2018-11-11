import java.sql.*;

public class CreateTables {

	public static void main(String[] args) throws Exception {

		Connection con = null;
		Statement stmt = null;

		try {
			con = DriverManager.getConnection("jdbc:mysql://stusql.dcs.shef.ac.uk/team002", "team002", "e8f208af");
			stmt = con.createStatement();
			int count = stmt.executeUpdate(
					"CREATE TABLE student (RegistrationNumber int, Degree varchar(255), Tutor varchar(255));");

			System.out.println(count);
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			if (stmt != null)
				stmt.close();
		}

	}

}
