package university;
import java.sql.*;
import java.util.ArrayList;


class Department{
	private String code;
	private String name;
	private static Connection con;
	
	Department(){}
	
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
	public static void connectToDB() throws Exception {
		   try {
			   con = DriverManager.getConnection("jdbc:mysql://stusql.dcs.shef.ac.uk/team002", "team002", "e8f208af");
		   }
		   catch(SQLException ex) {
			   ex.printStackTrace();
		   }
	}
	
	//Create Department
	public int createDept() throws Exception  {
		connectToDB();
		int count = 0;
		PreparedStatement newDept, dept = null;
		dept = con.prepareStatement("SELECT COUNT(*) FROM department WHERE code = ?");
		newDept = con.prepareStatement( "INSERT INTO department VALUES (? , ? )");
		try {
			dept.setString(1, getCode());
			ResultSet res = dept.executeQuery();
			res.next();
			
			if (res.getInt(1) == 0) {
				newDept.setString(1, getCode());
				newDept.setString(2, getName());
				count = newDept.executeUpdate();
			}	
		    
		 }catch (SQLException ex) {
			 ex.printStackTrace();
		 }finally {
				if (newDept != null)
					newDept.close();
		}
		con.close();
		return count;
	}
	
	//Delete Department
	//ADICIONAR APAGAR MÓDULOS
	//TESTAR APAGAR DEGREES E MÓDULOS
	public int deleteDep() throws Exception  {
		connectToDB();
		int count = 0;
		Degree c = null;
		PreparedStatement dept, delDept, deg= null;
		dept = con.prepareStatement("SELECT COUNT(*) FROM department WHERE code = ?");
		delDept = con.prepareStatement( "DELETE FROM department WHERE code = ?");
		deg = con.prepareStatement("SELECT code FROM degree WHERE mainDept = ?");
		
		try {
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
				
				
				
			}
		 }catch (SQLException ex) {
			 ex.printStackTrace();
		 }finally {
				if (delDept != null)
					delDept.close();
			}
		con.close();
		return count;
	}
	
	//Get all Departments
	public ArrayList<Department> getAllDep() throws Exception  {
		Department department = new Department();
		ArrayList<Department> deptList = new ArrayList<Department>();
		connectToDB();
		Statement stmt = con.createStatement();
		try {
			ResultSet res  = stmt.executeQuery("SELECT code FROM department");
			while (res.next()) {
				String code = res.getString("code");
				deptList.add(department.getDept(code));
			}
			res.close();
		 }catch (SQLException ex) {
			 ex.printStackTrace();
		 }finally {
				if (stmt != null)
					stmt.close();
			}
		con.close();
		return deptList;
	}
	
	//get department using code
	public Department getDept (String c) throws Exception  {
		Department d = new Department();
		PreparedStatement dept = null;
		connectToDB();
		dept = con.prepareStatement("SELECT name FROM department WHERE code = ?");
		try {
			dept.setString(1, c);
			ResultSet res  = dept.executeQuery();
			res.next();
			name = res.getString("name");
			d = new Department(c,name);
			res.close();
			
		 }catch (SQLException ex) {
			 ex.printStackTrace();
		 }finally {
				if (dept != null)
					dept.close();
			}
		con.close();
		return d;
	}
	
	
	public static void main(String[] args){
		
		ArrayList<Department> deptList;
		Department v = new Department("dos","dflv");
		Department c = new Department ("COM","Computer Science");
		try {
			//System.out.println(v.getCode()+ " - " + v.getName());
			/*deptList = v.getAllDep();
			for(Department str:deptList)  
		        System.out.println(str.getCode() + " - " + str.getName());  
			c.createDept();
			v.createDept();
			deptList = v.getAllDep();
			for(Department str:deptList)  
		        System.out.println(str.getCode() + " - " + str.getName());  
			v.deleteDep();*/
			deptList = v.getAllDep();
			for(Department str:deptList)  
		        System.out.println(str.getCode() + " - " + str.getName());  
			
			//System.out.println(v.getDept("LAN").getName());
		 
			
		 //System.out.println(t.getName("COM"));
		} catch(Exception ex) {
			 ex.printStackTrace();
		}
		
		
		/**
		//VER PORQUE PRECISO DESTA EXCEPÇÃO
		Department t = new Department("Computer Science","COM");
		try {
		t.createDept("Computer Science","COM");
		} catch(Exception ex) {
			 ex.printStackTrace();
		}
		System.out.println("REsultou?"); */
	} 
}