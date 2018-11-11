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

			System.out.println(count);
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			if (stmt != null)
				stmt.close();
		}

	}

}
