package university;

import java.sql.*;

public class CreateTables {

	public static void main(String[] args) throws Exception {

		Connection con = null;
		Statement stmt = null;

		try {
			con = DriverManager.getConnection("jdbc:mysql://stusql.dcs.shef.ac.uk/team002", "team002", "e8f208af");
			con.setAutoCommit(false); // turn off auto-commit
			stmt = con.createStatement();
			int count = stmt.executeUpdate(
					"CREATE TABLE student (RegistrationNumber int NOT NULL PRIMARY KEY, Degree varchar(255), Tutor varchar(255), Username varchar(255) REFERENCES account(Username));");
			count += stmt.executeUpdate(
					"CREATE TABLE account (Title varchar(6), Forename varchar(255), Surname varchar(255), Username varchar(255) NOT NULL PRIMARY KEY, Password varchar(255), Email varchar(255), Clearance int);");

			count += stmt.executeUpdate(
					("CREATE TABLE studentStatus (RegistrationNumber int NOT NULL PRIMARY KEY, Level int, Period int, StartDate date, EndDate date);"));

			System.out.println(count);

			// Create table for department
			int depart = stmt.executeUpdate(
					"CREATE TABLE department (code varchar(3) NOT NULL PRIMARY KEY, name varchar(255) NOT NULL);");
			stmt.executeUpdate("INSERT INTO department VALUES (\"COM\", \"Department of Computer Science \") ");
			stmt.executeUpdate("INSERT INTO department VALUES (\"BUS\", \"Bussiness School \") ");
			stmt.executeUpdate("INSERT INTO department VALUES (\"LAN\", \"Modern Languages\") ");

			// Create table for degree
			int degree = stmt.executeUpdate(
					"CREATE TABLE degree (code varchar(6) NOT NULL PRIMARY KEY, name varchar(255) NOT NULL, "
							+ "mainDept varchar(3) REFERENCES department(code), type varchar(13), placement BOOL);");

			// Create table for the secondary departments of interdisciplinary degrees
			int secondDep = stmt
					.executeUpdate("CREATE TABLE seconDepts ( degreeCode varchar(6) NOT NULL REFERENCES degree(code),"
							+ "dept varchar(3) NOT NULL REFERENCES department(code),"
							+ "PRIMARY KEY (degreeCode,dept) );");

			// Create table for level of study and their name
			int levels = stmt.executeUpdate(
					"CREATE TABLE studyLevels (level varchar(1) NOT NULL PRIMARY KEY, name varchar(11) NOT NULL );");

			// Add levels of study and their names
			stmt.addBatch("INSERT INTO studyLevels VALUES (1, \"Certificate\")");
			stmt.addBatch("INSERT INTO studyLevels VALUES (2,\"diploma\")");
			stmt.addBatch("INSERT INTO studyLevels VALUES (3,\"bachelors\")");
			stmt.addBatch("INSERT INTO studyLevels VALUES (4,\"masters\")");
			stmt.addBatch("INSERT INTO studyLevels VALUES (\"P\",\"placement\")");
			int[] updateCounts = stmt.executeBatch();

			System.out.println(count + depart + degree + secondDep);
			con.commit(); // commit manually
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			if (stmt != null)
				stmt.close();
		}

	}

}
