package university;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.sql.*;
import java.util.ArrayList;
import java.util.Objects;

import javax.swing.JPanel;

import university.UI.ProfileScreen;
import javax.swing.*;

public class Account {

	private String title;
	private String forename;
	private String surname;
	private String email;
	private String username;
	private String password;
	private Clearance clearance;
	private static Connection con;
    private ScreenManager screen;
    private JPanel loginScreen;
    private ProfileScreen profileScreen;

	public Account(String _title, String _forename, String _surname, String _password,
			String _clearance) {
		this.title = _title;
		this.forename = _forename;
		this.surname = _surname;
		this.clearance = this.toClearance(_clearance);
		
		try {
			this.username = this.generateUsername();
		} catch (Exception ex) {
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
	
	public Account() {}

	//Connect to the database
		public static void connectToDB() throws SQLException {
			   try {
				   con = DriverManager.getConnection("jdbc:mysql://stusql.dcs.shef.ac.uk/team002", "team002", "e8f208af");
			   }
			   catch(Exception ex) {
				   ex.printStackTrace();
			   }
		}
	public Account createAccount() throws SQLException {

		connectToDB();
		Statement stmt = null;

		try {
			stmt = con.createStatement();
			String query = String.format("INSERT INTO account (title, forename, surname, username, clearance, email, password) VALUES (\"%s\", \"%s\", \"%s\", \"%s\", \"%s\", \"%s\", \"%s\");",
					this.title, this.forename, this.surname, this.username, this.clearance.toInt(), this.email, this.password);
			int count = stmt.executeUpdate(query);
					

			System.out.println(count);
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (stmt != null)
				stmt.close();
		}
		con.close();
		
		con.close();
		return this;

	}

	public String getUsername() {
		return this.username;
	}

	public String getPassword() {
		return this.password;
	}

	private String generateUsername() throws SQLException {
		// generate username based on details

		connectToDB();
		Statement stmt = null;
		int count = 1;
		String initials = "";

		try {
			
			stmt = con.createStatement();
			String query = String.format("SELECT forename FROM account WHERE surname LIKE '%s';", this.surname);
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

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (stmt != null)
				stmt.close();
		}
		con.close();

		return initials + this.surname.replaceAll("\\s+","").toLowerCase() + count;
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

		connectToDB();
		Statement stmt = null;

		try {
			
			stmt = con.createStatement();
			int count = stmt.executeUpdate(
					String.format("UPDATE account SET title = %s WHERE username = %s;", t, this.username));

			System.out.println(count);
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (stmt != null)
				stmt.close();
		}
		con.close();

		this.title = t;

	}

	public String getForename() {
		return this.forename;
	}

	public void setForename(String f) throws SQLException {

		connectToDB();
		Statement stmt = null;

		try {
			stmt = con.createStatement();
			int count = stmt.executeUpdate(
					String.format("UPDATE account SET forename = %s WHERE username = %s;", f, this.username));

			System.out.println(count);
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (stmt != null)
				stmt.close();
		}
		con.close();

		this.forename = f;

	}

	public String getSurname() {
		return this.surname;
	}

	public void setSurname(String s) throws SQLException {

		connectToDB();
		Statement stmt = null;

		try {
			
			stmt = con.createStatement();
			int count = stmt.executeUpdate(
					String.format("UPDATE account SET surname = %s WHERE username = %s;", s, this.username));

			System.out.println(count);
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (stmt != null)
				stmt.close();
		}
		con.close();

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
		return result.toLowerCase();
	}
	
	public void deleteAccount() throws SQLException {
		
		connectToDB();
		Statement stmt = null;

		try {
			
			stmt = con.createStatement();
			int count = stmt.executeUpdate(
					String.format("DELETE FROM account WHERE username = \"%s\";", this.username));

			System.out.println(count);
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (stmt != null)
				stmt.close();
		}
		con.close();
	}
	

	
	public ArrayList<ArrayList<String>> getAcctList() throws SQLException  {
		ArrayList<ArrayList<String>> accList = new ArrayList<ArrayList<String>>();
		ArrayList<String> account = new ArrayList<String>();
		ResultSet res = null;
		PreparedStatement dept = null;
		connectToDB();
		dept = con.prepareStatement("SELECT Title, Forename, Surname, Username, Email, Clearance FROM account;");
		try {
			res  = dept.executeQuery();
			account.add("Username");
			account.add("Title");
			account.add("Forename");
			account.add("Surname");
			account.add("Email");
			account.add("Clearance");
			accList.add((ArrayList)account.clone());
			
			while (res.next()) {
				account.clear();
				account.add(res.getString("Username"));
				account.add(res.getString("Title"));
				account.add(res.getString("Forename"));
				account.add(res.getString("Surname"));
				account.add(res.getString("Email"));
				account.add(res.getString("Clearance"));
				accList.add((ArrayList) account.clone());
				
			}
			res.close();
			
		 }catch (Exception ex) {
			 ex.printStackTrace();
		 }finally {
				if (dept != null)
					dept.close();
			}
		con.close();
		return accList;
	} 
	
	static final String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
	static SecureRandom rnd = new SecureRandom();

	String randomString( int len ){
	   StringBuilder sb = new StringBuilder( len );
	   for( int i = 0; i < len; i++ ) 
	      sb.append( AB.charAt( rnd.nextInt(AB.length()) ) );
	   return sb.toString();
	}
	
	public static boolean delVerification(String email, String password, int clearance) throws SQLException {
		Boolean valid = false;
		connectToDB();
		PreparedStatement query = con.prepareStatement("SELECT COUNT(*) FROM account WHERE Email=? AND Password=? AND Clearance = ?");
		try {
			query.setString(1, email);
			query.setString(2,password);
			query.setInt(3, clearance);
			ResultSet res = query.executeQuery();
			res.next();
			if (res.getInt(1) == 1)
				valid = true;

		} catch (Exception ex) {
			ex.printStackTrace();
		}finally {
			if ( query != null)
				query.close();
		}
		
		con.close();
		return valid;
	}
	
    public Account login(String emailInput, String passwordInput) throws SQLException {
    	 
        connectToDB();
        PreparedStatement pst1 = null, pst2 = null;
        ResultSet res1, res2 = null;
        String sql = "select * from account where Email=? and Password=?";
        String clearLvl = "select Clearance from account where Email = ? and Password =?";
        Account a = null;
        try {
            pst1 = con.prepareStatement(sql);
            pst1.setString(1, emailInput);
            pst1.setString(2, Account.md5hash(passwordInput));
            res1 = pst1.executeQuery();

            pst2 = con.prepareStatement(clearLvl);
            pst2.setString(1, emailInput);
            pst2.setString(2, Account.md5hash(passwordInput));
            res2 = pst2.executeQuery();

            if (!ValidCheck.email(emailInput) || !ValidCheck.pass(passwordInput)) {
                JOptionPane.showMessageDialog(null, "Please do not enter illegal characters");
            } else if (emailInput.equals("") || passwordInput.equals("")) {
                JOptionPane.showMessageDialog(null, "Please enter an email and password");
            } else if (!res1.next()) {
                JOptionPane.showMessageDialog(null, "Account details not found");
            } else {
                res2.next();
                // get data
                String title = res1.getString("Title");
                String forename = res1.getString("Forename");
                String surname = res1.getString("Surname");
                String username = res1.getString("Username");
                String password = res1.getString("Password");
                Clearance clearance = null;

                switch (res2.getInt("Clearance")) {
                case 1:
                    clearance = Clearance.TEACHER;
                    break;
                case 2:
                    clearance = Clearance.REGISTRAR;
                    break;
                case 3:
                    clearance = Clearance.ADMIN;
                    break;
                default:
                    clearance = Clearance.STUDENT;
                    break;
                }
                
                a = new Account(title, forename, surname, username, null, clearance);
               
    		}
    		res1.close();
    		res2.close();
    	}
    	catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (pst1 != null)
	    		pst1.close();
	    		pst2.close();
		}
        con.close();
        return a;
		
    }
    
    public static String md5hash(String s) throws Exception {
    	byte[] bytesOfString = s.getBytes("UTF-8");
    	MessageDigest md = MessageDigest.getInstance("MD5");
    	byte[] thedigest = md.digest(bytesOfString);
    
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < thedigest.length; i++)
            sb.append(Integer.toString((thedigest[i] & 0xff) + 0x100, 16).substring(1));
        
        return sb.toString();
    }
    
    public void securePassword() throws SQLException {
		connectToDB();
		Statement stmt = null;

		try {
			String secure = Account.md5hash(this.password);
			stmt = con.createStatement();
			System.out.println(this.username);
			int count = stmt.executeUpdate(
					String.format("UPDATE account SET password = \"%s\" WHERE username = \"%s\";", secure, this.username));

			System.out.println(count);
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (stmt != null)
				stmt.close();
		}
		con.close();
    }

	
}