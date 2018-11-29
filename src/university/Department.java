package university;
import java.sql.*;
import java.util.ArrayList;


public class Department{
	private String code;
	private String name;
	private static Connection con;
	
	public Department(){}
	
	public Department(String code,String name){
		this.code = code;
		this.name = name;
	}
	
	public Department(String code) {
		this.code = code;
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
	public static void connectToDB() throws SQLException {
		   try {
			   con = DriverManager.getConnection("jdbc:mysql://stusql.dcs.shef.ac.uk/team002", "team002", "e8f208af");
		   }
		   catch(Exception ex) {
			   ex.printStackTrace();
		   }
	}
	
	//Create Department
	public int createDept() throws SQLException  {
		connectToDB();
		int count = 0;
		PreparedStatement newDept = null, dept = null;
		try {
			dept = con.prepareStatement("SELECT COUNT(*) FROM department WHERE code = ?");
			newDept = con.prepareStatement( "INSERT INTO department VALUES (? , ? )");
			dept.setString(1, getCode());
			ResultSet res = dept.executeQuery();
			res.next();
			
			if (res.getInt(1) == 0) {
				newDept.setString(1, getCode());
				newDept.setString(2, getName());
				count = newDept.executeUpdate();
			}	

		    res.close();
		 }catch (SQLException ex) {
			 ex.printStackTrace();
		 }finally {
				if (newDept != null) {
					dept.close();
					newDept.close();
				}
		}
		con.close();
		return count;
	}
	
	//Delete Department
	public int deleteDep() throws Exception  {
		connectToDB();
		int count = 0;
		Degree c = null;
		PreparedStatement dept = null, delDept = null, deg= null;
		
		try {
			dept = con.prepareStatement("SELECT COUNT(*) FROM department WHERE code = ?");
			delDept = con.prepareStatement( "DELETE FROM department WHERE code = ?");
			deg = con.prepareStatement("SELECT code FROM degree WHERE mainDept = ?");
			dept.setString(1, getCode());
			ResultSet res = dept.executeQuery();
			res.next();
			
			
			if (res.getInt(1) == 1) {
			
				deg.setString(1, getCode());
				ResultSet res2 = deg.executeQuery();
				while(res2.next()) {
					String degCode = res2.getString(1);
					c = Degree.getDegree(degCode);
					c.deleteDegree();
					
				}
				
				delDept.setString(1, getCode());
				count = delDept.executeUpdate();
				
			res.close();
				
			}
		 }catch (Exception ex) {
			 ex.printStackTrace();
		 }finally {
				if (delDept != null) {
					dept.close();
					delDept.close();
					deg.close();
				}
			}
		con.close();
		return count;
	}
	
	//Get all Departments
	public static ArrayList<String> getAllDepNames() throws SQLException  {
		ArrayList<String> deptList = new ArrayList<String>();
		connectToDB();
		Statement stmt = null;
		try {
			stmt = con.createStatement();
			ResultSet res  = stmt.executeQuery("SELECT name FROM department");
			while (res.next()) {
				String name = res.getString("name");
				deptList.add(name);
			}
			res.close();
		 }catch (Exception ex) {
			 ex.printStackTrace();
		 }finally {
				if (stmt != null)
					stmt.close();
			}
		con.close();
		return deptList;
	}
	public ArrayList<Department> getAllDep() throws SQLException  {
		Department department = new Department();
		ArrayList<Department> deptList = new ArrayList<Department>();
		connectToDB();
		Statement stmt = null;
		try {
			stmt = con.createStatement();
			ResultSet res  = stmt.executeQuery("SELECT code FROM department");
			while (res.next()) {
				String code = res.getString("code");
				deptList.add(department.getDept(code));
			}
			res.close();
		 }catch (Exception ex) {
			 ex.printStackTrace();
		 }finally {
				if (stmt != null)
					stmt.close();
			}
		con.close();
		return deptList;
	}
	//get department using name
	public static Department getDept (String n) throws SQLException  {
		Department d = new Department();
		String dcode = null;
		PreparedStatement dept = null;
		connectToDB();
		try {
			dept = con.prepareStatement("SELECT code FROM department WHERE name = ?");
			dept.setString(1, n);
			ResultSet res  = dept.executeQuery();
			res.next();
			dcode = res.getString("code");
			d = new Department(dcode,n);
			res.close();
			
		 }catch (Exception ex) {
			 ex.printStackTrace();
		 }finally {
				if (dept != null)
					dept.close();
			}
		con.close();
		return d;
	}
	
	public Department getDeptwCode (String c) throws SQLException  {
		Department d = new Department();
		PreparedStatement dept = null;
		connectToDB();
		try {
			dept = con.prepareStatement("SELECT name FROM department WHERE code = ?");
			dept.setString(1, c);
			ResultSet res  = dept.executeQuery();
			res.next();
			name = res.getString("name");
			d = new Department(c,name);
			res.close();
			
		 }catch (Exception ex) {
			 ex.printStackTrace();
		 }finally {
				if (dept != null)
					dept.close();
			}
		con.close();
		return d;
	}
	
	public ArrayList<ArrayList<String>> getDeptList() throws SQLException  {
		ArrayList<ArrayList<String>> deptList = new ArrayList<ArrayList<String>>();
		ArrayList<String> depart = new ArrayList<String>();
		ResultSet res = null;
		PreparedStatement dept = null;
		connectToDB();
		try {
			dept = con.prepareStatement("SELECT * FROM department;");
			res  = dept.executeQuery();
			depart.add("Code");
			depart.add("Name");
			deptList.add((ArrayList) depart.clone());
			while (res.next()) {
				depart.clear();
				depart.add(res.getString("code"));
				depart.add(res.getString("name"));
				deptList.add((ArrayList) depart.clone());
			}
			res.close();
			
		 }catch (Exception ex) {
			 ex.printStackTrace();
		 }finally {
				if (dept != null)
					dept.close();
			}
		con.close();
		return deptList;
	} 
	
	
}