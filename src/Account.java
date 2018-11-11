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
    }

    private void createAccount() {
    	
    	Connection con = null;
		Statement stmt = null;

		try {
			con = DriverManager.getConnection("jdbc:mysql://stusql.dcs.shef.ac.uk/team002", "team002", "e8f208af");
			stmt = con.createStatement();
            int count = stmt.executeUpdate(String.format("INSERT INTO account (title, forename, surname) VALUES (%s, %s, %s);", this.title, this.forename, this.surname));

			System.out.println(count);
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			if (stmt != null)
				stmt.close();
		}
        
    }

    private String getUsername() {
        // generate username based on details
        //this needs to be split later into get and generate username so
        // we don't generate it everytime
        return "PlaceHolderUsername123";
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