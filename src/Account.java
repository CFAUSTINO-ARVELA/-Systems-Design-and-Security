import java.sql.*;

class Account {

    private String title;
    private String forename;
    private String surname;
    private String email;
    private String username;
    private String password;
    private Clearance clearance;

    Account(String _title, String _forename, String _password, String _surname, Clearance _clearance) {
        this.title = _title;
        this.forename = _forename;
        this.surname = _surname;
        this.clearance = _clearance;
        
        this.username = this.generateUsername();
        this.createAccount();
    }

    private void createAccount() {
    	
    	Connection con = null;
		Statement stmt = null;

		try {
			con = DriverManager.getConnection("jdbc:mysql://stusql.dcs.shef.ac.uk/team002", "team002", "e8f208af");
			stmt = con.createStatement();
            int count = stmt.executeUpdate(String.format("INSERT INTO account (title, forename, surname, username) VALUES (%s, %s, %s, %s);", this.title, this.forename, this.surname, this.username));

			System.out.println(count);
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			if (stmt != null)
				stmt.close();
		}
        
    }
    
    public String getUsername() {
    	return this.username;
    }

    private String generateUsername() {
        // generate username based on details
    	
    	Connection con = null;
		Statement stmt = null;
		int count = 1;

		try {
			con = DriverManager.getConnection("jdbc:mysql://stusql.dcs.shef.ac.uk/team002", "team002", "e8f208af");
			stmt = con.createStatement();
            ResultSet res = stmt.executeQuery(String.format("SELECT COUNT(*) FROM account WHERE forename LIKE '%s%' AND surname LIKE '%s';", this.forename.charAt(0), this.surname));
            
            count = res.getInt(1);
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			if (stmt != null)
				stmt.close();
		}
		
		return this.forename.charAt(0) + this.surname + count;
    }

    private void generateEmail() {
        // generate email based on name and number
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle() {

    }

    public String getForename() {
        return this.forename;
    }

    public void setForename() {

    }

    public String getSurname() {
        return this.surname;
    }

    public void setSurname() {

    }

    public Clearance getClearance() {
        return this.clearance;
    }

    
}