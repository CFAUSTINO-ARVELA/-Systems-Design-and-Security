package university;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ModuleChoice {
	
	private int registrationNumber;
	private String moduleCode;
	private String period;
	private int grade;
	private boolean complete;
    private static Connection con;
    
    public static void connectToDB() throws SQLException {
		   try {
			   con = DriverManager.getConnection("jdbc:mysql://stusql.dcs.shef.ac.uk/team002", "team002", "e8f208af");
		   }
		   catch(Exception ex) {
			   ex.printStackTrace();
		   }
	}
	
	public ModuleChoice(int r, String m, String p, int g) {
		this.registrationNumber = r;
		this.moduleCode = m;
		this.period = p;
		this.grade = g;
		this.complete = true;
	}
	
	public ModuleChoice(int r, String m, String p) {
		this.registrationNumber = r;
		this.moduleCode = m;
		this.period = p;
		this.complete = false;
	}
	
	public int getRegistrationNumber() {
		return registrationNumber;
	}
	public String getModuleCode() {
		return moduleCode;
	}
	public String getPeriod() {
		return period;
	}
	public int getGrade() {
		return grade;
	}
	
	public void createModuleChoice() throws SQLException {
		connectToDB();
		Statement stmt = null;

		try {
			stmt = con.createStatement();
			String query = String.format("INSERT INTO moduleChoice (registrationNumber, moduleCode, period, grade) VALUES (%d, \"%s\", \"%s\", %d);",
					this.registrationNumber, this.moduleCode, this.period, this.grade);
			int count = stmt.executeUpdate(query);

			System.out.println(count);
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (stmt != null)
				stmt.close();
		}
		con.close();
	}
	
	public static ArrayList<ModuleGrades> getPastChoices(int r, String p) throws Exception {
		ArrayList<ModuleGrades> choices = new ArrayList<ModuleGrades>();
		
		connectToDB();
		Statement stmt = null;
		try {
			stmt = con.createStatement();
			ResultSet res  = stmt.executeQuery(String.format("SELECT * FROM moduleChoice WHERE registrationNumber = %d AND period = \"%s\";", r, p));
			while (res.next()) {
				Module modCode = Module.getModule(res.getString("moduleCode"));
				int grade = res.getInt("grade");
				
				ModuleGrades thischoice = new ModuleGrades(modCode, grade);
				choices.add(thischoice);
				
			}
			res.close();
		 }catch (Exception ex) {
			 ex.printStackTrace();
		 }finally {
				if (stmt != null)
					stmt.close();
			}
		con.close();
		return choices;
	}

}
