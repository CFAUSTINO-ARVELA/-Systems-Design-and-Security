package university;
import java.sql.*;
import java.util.ArrayList;

class Degree{
	private String code;
	private String name;
	private Department mainDept;
	private ArrayList<Department> seconDepts; 
	private String type;
	private Boolean placement;
	private Connection con;
	
	//Constructor for degree with just one department
	Degree(String code, String name, Department mainDept,
			String type,Boolean placement){
		this.code = code;
		this.name = name;
		this.mainDept = mainDept;
		this.type = type;
		this.placement = placement;
	}
	
	//Constructor for degree with several departments
	Degree(String code, String name, Department mainDept,
			 ArrayList<Department> seconDepts, String type,Boolean placement){
		this.code = code;
		this.name = name;
		this.mainDept = mainDept;
		this.seconDepts = seconDepts;
		this.type = type;
		this.placement = placement;
	}
	
	//get code of Degree
	public String getCode() {
		return code;
	}
	//get name of Degree
	public String getName() {
		return name;
	}
	//get main department of Degree
	public Department getMainDept() {
		return mainDept;
	}
	//get list of secondary department of Degree
	public ArrayList<Department> getSeconDepts() {
		return seconDepts;
	}
	//get type of Degree (Undergraduate or Postgraduate
	public String getType() {
		return type;
	}
	//get if Degree has placement year
	public Boolean getPlacement() {
		return placement;
	}
	//Connect to the database
	public void connectToDB() throws Exception {
		   try {
			   con = DriverManager.getConnection("jdbc:mysql://stusql.dcs.shef.ac.uk/team002", "team002", "e8f208af");
		   }
		   catch(SQLException ex) {
			   ex.printStackTrace();
		   }
	}
	

	//Create DegrEe with just one department
	public int createDegree() throws Exception  {
		connectToDB();
		PreparedStatement newDeg,deg, newSeconDep= null;
		int count = 0;
		deg = con.prepareStatement("SELECT COUNT(*) FROM degree WHERE name = ?");
		newDeg = con.prepareStatement("INSERT INTO degree VALUES (?,?,?,?,?)");
		newSeconDep = con.prepareStatement("INSERT INTO seconDepts VALUES(?,?)");
	
		try {
			deg.setString(1, getName());
			ResultSet res = deg.executeQuery();
			
			res.next();
			System.out.println(res.getInt(1));
			if(res.getInt(1) == 0) {
				newDeg.setString(1,getCode());
				newDeg.setString(2,getName());
				newDeg.setString(3,getMainDept().getCode());
				newDeg.setString(4, getType());
				newDeg.setBoolean(5,getPlacement());
				count = newDeg.executeUpdate();
				
				newSeconDep.setString(1, getCode());
				for (Department str:getSeconDepts()) {
					newSeconDep.setString(2, str.getCode());
					count += newSeconDep.executeUpdate();
				}
					
		}
		}catch (SQLException ex) {
			ex.printStackTrace();
		}finally {
			if (newDeg != null)
				newDeg.close();
		}
		con.close();
		return count;
	}
	
	
	//Delete degree with code and name (just to be safe)
	public int deleteDegree() throws Exception {
		int count = 0;
		PreparedStatement delDeg,delSDept,deg = null;
		deg = con.prepareStatement("SELECT COUNT(*) FROM degree WHERE code = ?");
		delDeg = con.prepareStatement("DELETE FROM degree WHERE code = ?");
		delSDept = con.prepareStatement("DELETE FROM seconDepts WHERE degreeCode = ?");
		connectToDB();
		try {
			deg.setString(1, getCode());
			ResultSet res = deg.executeQuery();
			res.next();
			
			if(res.getInt(1) == 1){
				delDeg.setString(1, getCode());
				count += delDeg.executeUpdate();
				
				delSDept.setString(1, getCode());
				count += delSDept.executeUpdate();
			}

		    
		 }catch (SQLException ex) {
			 ex.printStackTrace();
		 }finally {
				if (delDeg != null)
					delDeg.close();
			}
		con.close();
		return count;
	}

	
	//Get a degree using the code (return a degree object)
	public Degree getDegree(String c) throws Exception {
		Degree degree = null;
		ArrayList<Department> deptList = new ArrayList<Department>();
		Department dep = null;
		
		connectToDB();
		PreparedStatement deg,noDeg,secDep = null;
		noDeg = con.prepareStatement("SELECT COUNT(*) FROM degree WHERE code = ?");
		deg = con.prepareStatement("SELECT * FROM degree JOIN department WHERE degree.mainDept=department.code AND degree.code =  ?");
		secDep = con.prepareStatement("SELECT dept FROM seconDepts WHERE degreeCode = ?");
		Statement stmt = con.createStatement();
		//JOIN WITH THE DEPARTMENTS TABLES
		try {
			noDeg.setString(1, c);
			ResultSet res1 = noDeg.executeQuery();
			res1.next();
			System.out.println(res1.getInt(1));
			if(res1.getInt(1) != 0) {
				deg.setString(1, c);
				ResultSet res = deg.executeQuery();
				res.next();
				String name = res.getString("degree.name");
				String mainDept = res.getString("mainDept");
				String type = res.getString("type");
				Boolean placement = res.getBoolean("placement");
				String depName = res.getString("department.name");
				dep = new Department(mainDept,depName);
				secDep.setString(1, c);
				ResultSet res2 = secDep.executeQuery();
				while (res2.next()) {
					String deptC = res2.getString("dept");
					deptList.add(dep.getDept(deptC));
				}
				
				degree = new Degree(c, name, dep,deptList,type,placement);
				res.close();
				res1.close();
			}
			
			
		 }catch (SQLException ex) {
			 ex.printStackTrace();
		 }finally {
				if (stmt != null)
					stmt.close();
			}
		con.close();
		return degree;
	}
	
	//Get all degrees
		public ArrayList<Degree> getAllDegree() throws Exception {
			Degree degree = null;
			Department department = null;
			ArrayList<Department> deptList = new ArrayList<Department>();
			ArrayList<Degree> degreeList = new ArrayList<Degree>();
			
			connectToDB();
			Statement stmt = con.createStatement();
			PreparedStatement deg,depName = null;
			deg = con.prepareStatement("SELECT * FROM degree " );
			depName = con.prepareStatement("SELECT name FROM department JOIN seconDepts WHERE seconDepts.degreeCode = ?" );
			
			try {
				ResultSet res  = deg.executeQuery();
				
				while(res.next()) {
					
					String dCode = res.getString("code");
					depName.setString(1, dCode);
					ResultSet res2 = depName.executeQuery();
					String name = res.getString("name");
					String mainDept = res.getString("mainDept");
					String type = res.getString("type");
					Boolean placement = res.getBoolean("placement");
					while (res2.next()) {
						String deptName = res.getString("name");
						department = new Department(code, deptName);
						deptList.add(department);
					}
					department = department.getDept(mainDept);
					degree = new Degree(dCode, name, department,deptList,type,placement);
					degreeList.add(degree);
				}
				res.close();
				con.close();
			 }catch (SQLException ex) {
				 ex.printStackTrace();
			 }finally {
					if (stmt != null)
						stmt.close();
				}
			
			return degreeList;
		}
		
		public static void main(String[] args){
			ArrayList<Degree> degreeList;
			ArrayList<Department> deptList = new ArrayList<Department>();
			Department c = new Department ("COM","Computer Science");
			Department b,l;
			
			try {
				b = c.getDept("BUS");
				l = c.getDept("LAN");
				deptList.add(b);
				deptList.add(l);
				Degree t = new Degree ("COMU23","BSc Information Systems",c,deptList,"undergraduate",false);
				System.out.println(t.getName());
				t.createDegree();
				
				degreeList = t.getAllDegree();
				
				for(Degree str:degreeList)  
			        System.out.println(str.getName()+ str.getCode());
				
				System.out.println(t.getDegree("COMU23").getCode());
				
			} catch(Exception ex) {
				ex.printStackTrace();
			}
		}
		
		
	
}