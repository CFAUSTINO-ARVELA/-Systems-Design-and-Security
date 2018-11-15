package university;
import java.sql.*;
import java.util.ArrayList;

class Department{
	private String code;
	private String name;
	private Connection con;
	
	Department(String code,String name){
		this.code = code;
		this.name = name;
	}
	
	//get code of Department
	public String getCode() {
		return code;
	}
	//get name of Department
	public String getName() {
		return name;
	}
	//Connect to the database
	public void connectToDB() throws Exception {
		   try {
			   con = DriverManager.getConnection("jdbc:mysql://stusql.dcs.shef.ac.uk/team002", "team002", "e8f208af");
			   con.setAutoCommit(false); // turn off auto-commit
		   }
		   catch(SQLException ex) {
			   ex.printStackTrace();
		   }
	}
	
	//Create Department
	public void createDept(String c, String n) throws Exception  {
		PreparedStatement newDept = null;
		String newDeptString = "INSERT INTO department VALUES (" + c + ", " + n + ")";
		connectToDB();
		try {
			newDept = con.prepareStatement(newDeptString);
		    con.close();
		 }catch (SQLException ex) {
			 ex.printStackTrace();
		 }finally {
				if (newDept != null)
					newDept.close();
			}
	}
	
	//Delete Department
	public void deleteDep(String c,String n) throws Exception  {
		PreparedStatement delDept = null;
		String newDeptString = "DELETE FROM department WHERE code = " + c + " AND name = " + n ;
		connectToDB();
		try {
			delDept = con.prepareStatement(newDeptString);
		    con.close();
		 }catch (SQLException ex) {
			 ex.printStackTrace();
		 }finally {
				if (delDept != null)
					delDept.close();
			}
	}
	
	//Get all Departments
	public ArrayList<Department> getAllDep() throws Exception  {
		connectToDB();
		Statement stmt = con.createStatement();
		ArrayList<Department> deptList = new ArrayList<Department>();
		try {
			ResultSet res  = stmt.executeQuery("SELECT * FROM department");
			while (res.next()) {
				String code = res.getString("code");
				String name = res.getString("name");
				Department department = new Department(code, name);
				deptList.add(department);
			}
			res.close();
			con.close();
		 }catch (SQLException ex) {
			 ex.printStackTrace();
		 }finally {
				if (stmt != null)
					stmt.close();
			}
		return deptList;
	}
	
	//get name of department using code
	public String getName (String code) throws Exception  {
		connectToDB();
		Statement stmt = con.createStatement();
		String name = null;
		try {
			ResultSet res  = stmt.executeQuery("SELECT name FROM department WHERE code = " +code );
			name = res.getString("name");
			res.close();
			con.close();
		 }catch (SQLException ex) {
			 ex.printStackTrace();
		 }finally {
				if (stmt != null)
					stmt.close();
			}
		return name;
	}
	
	
	public static void main(String[] args){
		
		//VER PORQUE PRECISO DESTA EXCEPÇÃO
		Department t = new Department("Computer Science","COM");
		try {
		t.createDept("Computer Science","COM");
		} catch(Exception ex) {
			 ex.printStackTrace();
		}
		System.out.println("REsultou?");
	}
}