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
			
			/**
			int count = stmt.executeUpdate(
					"CREATE TABLE student (RegistrationNumber int NOT NULL PRIMARY KEY, Degree varchar(255), Tutor varchar(255), Username varchar(255) REFERENCES account(Username));");
			count += stmt.executeUpdate(
					"CREATE TABLE account (Title varchar(6), Forename varchar(255), Surname varchar(255), Username varchar(255) NOT NULL PRIMARY KEY, Password varchar(255), Email varchar(255), Clearance int);");

			count += stmt.executeUpdate(
					"CREATE TABLE studentStatus (RegistrationNumber int NOT NULL PRIMARY KEY, Level char, Period char, StartDate date, EndDate date, Registered boolean, Resitting boolean, Graduated boolean);");
			
			count+= stmt.executeUpdate("CREATE TABLE moduleChoice (RegistrationNumber int NOT NULL PRIMARY KEY, ModuleCode varchar(7) NOT NULL, Period char, Grade int);");
			count+= stmt.executeUpdate("CREATE TABLE periodResult (RegistrationNumber int, Period char, Level char, Grade int, Passed boolean);");
			count+= stmt.executeUpdate("CREATE TABLE degreeResult (RegistrationNumber int, Result varchar(255), Masters boolean);");
			
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
							+ "mainDept varchar(3) REFERENCES department(code), type varchar(5), placement BOOL);");
			
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
			
			// Create table for modules
			int modules = stmt.executeUpdate(
					"CREATE TABLE module (name varchar(255) NOT NULL, code varchar(7) PRIMARY KEY, credits int, duration varchar(255) );");
	**/
			// Create table for associated modules and degrees
			int assoModDeg = stmt.executeUpdate(
					"CREATE TABLE assoModDeg (modCode varchar(7) REFERENCES module(code), degCode varchar(6) REFERENCES degree(code), mandatory BOOL, year varchar(1) );");
		
			//System.out.println(count + depart + degree + secondDep); 
			con.commit(); // commit manually
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			if (stmt != null)
				stmt.close();
		}

	}

}
