package university;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DegreeResult {
	
	private int registrationNumber;
	private boolean masters;
	private String result;
	private static Connection con = null;
	
	public static void connectToDB() throws SQLException {
		   try {
			   con = DriverManager.getConnection("jdbc:mysql://stusql.dcs.shef.ac.uk/team002", "team002", "e8f208af");
		   }
		   catch(Exception ex) {
			   ex.printStackTrace();
		   }
	}
	
	public DegreeResult(int r, boolean m, String res) {
		this.registrationNumber = r;
		this.masters = m;
		this.result = res;
	}
	
	public int createDegreeResult() throws SQLException {
		connectToDB();
		int count = 0;
		PreparedStatement newresult = null, result = null;
		try {
			result = con.prepareStatement("SELECT COUNT(*) FROM degreeResult WHERE registrationNumber = ?");
			newresult = con.prepareStatement( "INSERT INTO degreeResult VALUES (?, ?, ?)");
			result.setInt(1, this.registrationNumber);
			ResultSet res = result.executeQuery();
			res.next();
			
			if (res.getInt(1) == 0) {
				newresult.setInt(1, this.registrationNumber);
				newresult.setBoolean(3, this.masters);
				newresult.setString(2, this.result);
				count = newresult.executeUpdate();
			}	
		    
		 }catch (Exception ex) {
			 ex.printStackTrace();
		 }finally {
				if (newresult != null)
					newresult.close();
					result.close();
		}
		con.close();
		return count;
	}
	
	public static DegreeResult getDegreeResult(int r) throws SQLException {
		DegreeResult d = null;
		boolean masters;
		String dbresult = null;
		PreparedStatement result = null;
		connectToDB();
		try {
			result = con.prepareStatement("SELECT * FROM degreeResult WHERE registrationNumber = ?");
			result.setInt(1, r);
			ResultSet res = result.executeQuery();
			res.next();
			masters = res.getBoolean("masters");
			dbresult = res.getString("result");
			d = new DegreeResult(r, masters, dbresult);
			res.close();
			
		 }catch (SQLException ex) {
			 ex.printStackTrace();
		 }finally {
				if (result != null)
					result.close();
			}
		con.close();
		return d;
	}

}
