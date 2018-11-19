package university;
import java.security.SecureRandom;
import java.sql.*;

public class Account {

	private String title;
	private String forename;
	private String surname;
	private String email;
	private String username;
	private String password;
	private Clearance clearance;

	public Account(String _title, String _forename, String _surname, String _password,
			String _clearance) {
		this.title = _title;
		this.forename = _forename;
		this.surname = _surname;
		this.clearance = this.toClearance(_clearance);
		
		try {
			this.username = this.generateUsername();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}

		this.email = this.generateEmail();
		this.password = this.generatePassword();
	}
	
	public Account(String _title, String _forename, String _surname, String _username, String _password,
			Clearance _clearance) {
		this.title = _title;
		this.forename = _forename;
		this.surname = _surname;
		this.username = _username;
		this.clearance = _clearance;

		this.email = this.generateEmail();
		this.password = this.generatePassword();
	}
	
	public Account(String _username) {
		this.username = _username;
	}

	public Account createAccount() throws SQLException {

		Connection con = null;
		Statement stmt = null;

		try {
			con = DriverManager.getConnection("jdbc:mysql://stusql.dcs.shef.ac.uk/team002", "team002", "e8f208af");
			stmt = con.createStatement();
			String query = String.format("INSERT INTO account (title, forename, surname, username, clearance, email, password) VALUES (\"%s\", \"%s\", \"%s\", \"%s\", \"%s\", \"%s\", \"%s\");",
					this.title, this.forename, this.surname, this.username, this.clearance.toInt(), this.email, this.password);
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

	public String getUsername() {
		return this.username;
	}

	private String generateUsername() throws SQLException {
		// generate username based on details

		Connection con = null;
		Statement stmt = null;
		int count = 1;
		String initials = "";

		try {
			con = DriverManager.getConnection("jdbc:mysql://stusql.dcs.shef.ac.uk/team002", "team002", "e8f208af");
			stmt = con.createStatement();
			String query = String.format("SELECT forename FROM account WHERE surname LIKE '%s';",
					this.forename.charAt(0), this.surname);
			System.out.println(query);
			ResultSet res = stmt.executeQuery(query);
			initials = this.splitForename(this.forename);
			String name = "";
			
			while (res.next()) {
				name = res.getString(1);
				System.out.println(this.splitForename(name));
				System.out.println(initials);
				if (this.splitForename(name).equals(initials)) {
					count += 1;
				}
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			if (stmt != null)
				stmt.close();
		}

		return initials + this.surname + count;
	}
	
	private String generatePassword() {
		return randomString(12);
	}
	
	public String getEmail() {
		return this.email;
	}

	private String generateEmail() {
		// generate email based on name and number
		return this.username + "@sheffield.ac.uk";
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String t) throws SQLException {

		Connection con = null;
		Statement stmt = null;

		try {
			con = DriverManager.getConnection("jdbc:mysql://stusql.dcs.shef.ac.uk/team002", "team002", "e8f208af");
			stmt = con.createStatement();
			int count = stmt.executeUpdate(
					String.format("UPDATE account SET title = %s WHERE username = %s;", t, this.username));

			System.out.println(count);
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			if (stmt != null)
				stmt.close();
		}

		this.title = t;

	}

	public String getForename() {
		return this.forename;
	}

	public void setForename(String f) throws SQLException {

		Connection con = null;
		Statement stmt = null;

		try {
			con = DriverManager.getConnection("jdbc:mysql://stusql.dcs.shef.ac.uk/team002", "team002", "e8f208af");
			stmt = con.createStatement();
			int count = stmt.executeUpdate(
					String.format("UPDATE account SET forename = %s WHERE username = %s;", f, this.username));

			System.out.println(count);
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			if (stmt != null)
				stmt.close();
		}

		this.forename = f;

	}

	public String getSurname() {
		return this.surname;
	}

	public void setSurname(String s) throws SQLException {

		Connection con = null;
		Statement stmt = null;

		try {
			con = DriverManager.getConnection("jdbc:mysql://stusql.dcs.shef.ac.uk/team002", "team002", "e8f208af");
			stmt = con.createStatement();
			int count = stmt.executeUpdate(
					String.format("UPDATE account SET surname = %s WHERE username = %s;", s, this.username));

			System.out.println(count);
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			if (stmt != null)
				stmt.close();
		}

		this.surname = s;

	}

	public Clearance getClearance() {
		return this.clearance;
	}
	
	public Clearance toClearance(String s) {
		switch (s) {
		case "Student": return Clearance.STUDENT;
		case "Teacher": return Clearance.TEACHER;
		case "Registrar": return Clearance.REGISTRAR;
		case "Admin": return Clearance.ADMIN;
		default: return Clearance.STUDENT;
		}
	}
	
	public String splitForename(String f) {
		
		String[] names = f.split(" ");
		String result = "";
		for (int i = 0; i < names.length; i++) {
			result += names[i].charAt(0);
		}
		return result;
	}
	
	public void deleteAccount() throws SQLException {
		
		Connection con = null;
		Statement stmt = null;

		try {
			con = DriverManager.getConnection("jdbc:mysql://stusql.dcs.shef.ac.uk/team002", "team002", "e8f208af");
			stmt = con.createStatement();
			int count = stmt.executeUpdate(
					String.format("DELETE FROM account WHERE username = %s;", this.username));

			System.out.println(count);
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			if (stmt != null)
				stmt.close();
		}
	}
	
	static final String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~";
	static SecureRandom rnd = new SecureRandom();

	String randomString( int len ){
	   StringBuilder sb = new StringBuilder( len );
	   for( int i = 0; i < len; i++ ) 
	      sb.append( AB.charAt( rnd.nextInt(AB.length()) ) );
	   return sb.toString();
	}

}