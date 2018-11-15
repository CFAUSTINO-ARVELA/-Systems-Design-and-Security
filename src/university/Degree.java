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
	public Boolean getPlacemente() {
		return placement;
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
	

	//Create Degrre with just one department VER SE VALE A PENA USAR OBJECTO + GERAR CÓDICO AQUI
	public void createDegree(String c, String n, Department dept, String tp,Boolean plc) throws Exception  {
		PreparedStatement newDept = null;
		String newDegString = "INSERT INTO degree VALUES (" + c + "," + n + ", " + dept.getCode() + ", " +
				tp + ", " + plc + ")";
		connectToDB();
		try {
			newDept = con.prepareStatement(newDegString);
		    con.close();
		}catch (SQLException ex) {
			ex.printStackTrace();
		}finally {
			if (newDept != null)
				newDept.close();
		}
	}
	
	
	//Create Department with secondary department VER SE VALE A PENA USAR OBJECTO
	public void createDegree(String c, String n, Department dept, ArrayList<Department> sDept, String tp,Boolean plc) throws Exception  {
		PreparedStatement newDept = null;
		String newDegString = "INSERT INTO degree VALUES (" + c + ","+ n + ", " + dept.getCode() + ", " +
				tp + ", " + plc + ")";
		String newDegSecString ="INSERT INTO seconDepts VALUES (" + c + ", ? )";
		connectToDB();
		try {
			newDept = con.prepareStatement(newDegString);
			
			for(int i = 0; i < sDept.size(); i++) {
				newDept = con.prepareStatement(newDegSecString);
				newDept.setString(1,sDept.get(i).getCode());
				newDept.executeQuery();
			}
		    con.close();
		}catch (SQLException ex) {
			ex.printStackTrace();
		}finally {
			if (newDept != null)
				newDept.close();
		}
	}
	
	//Delete degree with code and name (just to be safe)
	public void deleteDegree(String c, String n) throws Exception {
		PreparedStatement delDeg = null;
		String delDegtString = "DELETE FROM degree WHERE code = " + c + " AND name = " + n;
		String delSDeptString = "DELETE FROM seconDepts WHERE degreeCode = " + c;
		connectToDB();
		try {
			delDeg = con.prepareStatement(delDegtString);
			delDeg = con.prepareStatement(delSDeptString);
		    con.close();
		 }catch (SQLException ex) {
			 ex.printStackTrace();
		 }finally {
				if (delDeg != null)
					delDeg.close();
			}
	}

	
	//Get a degree using the code (return a degree object)
	public Degree getDegree(String c) throws Exception {
		connectToDB();
		Statement stmt = con.createStatement();
		Degree degree = null;
		ArrayList<Department> deptList = new ArrayList<Department>();
		Department department = null;
		
		try {
			ResultSet res  = stmt.executeQuery("SELECT * FROM degree WHERE code = " + c );
			String name = res.getString("name");
			String mainDept = res.getString("mainDept");
			String type = res.getString("type");
			Boolean placement = res.getBoolean("placement");
			ResultSet res2 = stmt.executeQuery("SELECT name FROM department JOIN seconDepts WHERE seconDepts.degreeCode = " + c );
			while (res2.next()) {
				String deptName = res.getString("name");
				department = new Department(code, deptName);
				deptList.add(department);
			}
			department = new Department(mainDept, department.getName(mainDept) );
			degree = new Degree(c, name, department,deptList,type,placement);
			res.close();
			con.close();
		 }catch (SQLException ex) {
			 ex.printStackTrace();
		 }finally {
				if (stmt != null)
					stmt.close();
			}
		
		return degree;
	}
	
	//Get all degrees
		public ArrayList<Degree> getAllDegree(String c) throws Exception {
			connectToDB();
			Statement stmt = con.createStatement();
			Degree degree = null;
			Department department = null;
			ArrayList<Department> deptList = new ArrayList<Department>();
			ArrayList<Degree> degreeList = new ArrayList<Degree>();
			
			try {
				ResultSet res  = stmt.executeQuery("SELECT * FROM degree " );
				
				while(res.next()) {
					ResultSet res2 = stmt.executeQuery("SELECT name FROM department JOIN seconDepts WHERE seconDepts.degreeCode = " + c );
					String name = res.getString("name");
					String mainDept = res.getString("mainDept");
					String type = res.getString("type");
					Boolean placement = res.getBoolean("placement");
					while (res2.next()) {
						String deptName = res.getString("name");
						department = new Department(code, deptName);
						deptList.add(department);
					}
					department = new Department(mainDept, department.getName(mainDept) );
					degree = new Degree(c, name, department,deptList,type,placement);
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
	
}