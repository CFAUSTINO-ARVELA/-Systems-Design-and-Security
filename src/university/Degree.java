package university;
import java.sql.*;
import java.util.ArrayList;

public class Degree{
	private String code;
	private String name;
	private Department mainDept;
	private ArrayList<Department> seconDepts; 
	private String type;
	private Boolean placement;
	private Boolean fouryears;
	private static Connection con;
	
	//Constructor for degree with just one department
	public Degree(String name, Department mainDept,
			String type,Boolean placement, Boolean fouryears) throws Exception{
		this.name = name;
		this.mainDept = mainDept;
		this.type = type;
		this.placement = placement;
		this.seconDepts = new ArrayList<Department>();
		this.fouryears = fouryears;
	}
	
	//Constructor for degree with several departments
	public Degree(String name, Department mainDept,
			 ArrayList<Department> seconDepts, String type,Boolean placement, Boolean fouryears) throws Exception{
		this.name = name;
		this.mainDept = mainDept;
		this.seconDepts = seconDepts;
		this.type = type;
		this.placement = placement;
		this.fouryears = fouryears;
	}

	public Degree(String code) {
		this.code = code;
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
		
	public void setCode() throws Exception{
		int no = 1;
		connectToDB();
		PreparedStatement noDeg = null;
		noDeg = con.prepareStatement("SELECT COUNT(*) FROM degree WHERE mainDept =  ?");
		try {
			noDeg.setString(1, getMainDept().getCode());
			ResultSet res = noDeg.executeQuery();
			res.next();
			no = res.getInt(1) + 1;
		 }catch (SQLException ex) {
			 ex.printStackTrace();
		 }finally {
				if (noDeg != null)
					noDeg.close();
			}
		String code = getMainDept().getCode() + getType().charAt(0) + String.format("%02d", no); 
		con.close();
		this.code = code;
	}
	
	public void setCode(String c) {
		this.code = c;
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
	

	//Create DegrEe with just one department
	public Degree createDegree() throws Exception  {
		connectToDB();
		PreparedStatement newDeg,deg, newSeconDep= null;
		int count = 0;
		deg = con.prepareStatement("SELECT COUNT(*) FROM degree WHERE name = ?");
		newDeg = con.prepareStatement("INSERT INTO degree VALUES (?,?,?,?,?,?)");
		newSeconDep = con.prepareStatement("INSERT INTO seconDepts VALUES(?,?)");
	
		try {
			deg.setString(1, getName());
			ResultSet res = deg.executeQuery();
			
			res.next();
			if(res.getInt(1) == 0) {
				newDeg.setString(1,getCode());
				newDeg.setString(2,getName());
				newDeg.setString(3,getMainDept().getCode());
				newDeg.setString(4, getType());
				newDeg.setBoolean(5,getPlacement());
				newDeg.setBoolean(6, getFourYears());
				count = newDeg.executeUpdate();
				
				if (!this.seconDepts.isEmpty()) {
				
					newSeconDep.setString(1, getCode());
					for (Department str:getSeconDepts()) {
						newSeconDep.setString(2, str.getCode());
						count += newSeconDep.executeUpdate();
					}
			}
					
		}
		}catch (SQLException ex) {
			ex.printStackTrace();
		}finally {
			if (newDeg != null)
				newDeg.close();
		}
		con.close();
		return this;
	}
	
	
	public boolean getFourYears() {
		// TODO Auto-generated method stub
		return this.fouryears;
	}

	//Delete degree with code and name (just to be safe)
	public int deleteDegree() throws Exception {
		int count = 0;
		connectToDB();
		PreparedStatement delDeg,delSDept,deg = null;
		deg = con.prepareStatement("SELECT COUNT(*) FROM degree WHERE code = ?");
		delDeg = con.prepareStatement("DELETE FROM degree WHERE code = ?");
		delSDept = con.prepareStatement("DELETE FROM seconDepts WHERE degreeCode = ?");
		
		try {
			deg.setString(1, getCode());
			//System.out.println(getCode()+"code" + getCode()=="COMU02");
			ResultSet res = deg.executeQuery();
			res.next();
			//System.out.println("Não Entre?"+res.getInt(1));
			if(res.getInt(1) == 1){
				//System.out.println(getCode() + "mklf");
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

	
	//Get a degree using the name (return a degree object)
	public static Degree getDegree(String c) throws Exception {
		System.out.println(c);
		Degree degree = null;
		ArrayList<Department> deptList = new ArrayList<Department>();
		Department dep = null;
		
		connectToDB();
		PreparedStatement deg,noDeg,secDep = null;
		noDeg = con.prepareStatement("SELECT COUNT(*) FROM degree WHERE code = ?");
		deg = con.prepareStatement("SELECT * FROM degree JOIN department WHERE degree.mainDept=department.code AND degree.code =  ?");
		secDep = con.prepareStatement("SELECT dept FROM seconDepts WHERE degreeCode = ?");
		Statement stmt = con.createStatement();
		
		try {
			noDeg.setString(1, c);
			System.out.println(noDeg);
			ResultSet res1 = noDeg.executeQuery();
			res1.next();
			
			if(res1.getInt(1) != 0) {
				deg.setString(1, c);
				ResultSet res = deg.executeQuery();
				res.next();
				String code = res.getString("code");
				String name = res.getString("degree.name");
				String mainDept = res.getString("mainDept");
				String type = res.getString("type");
				Boolean placement = res.getBoolean("placement");
				Boolean fouryears = res.getBoolean("fouryears");
				String depName = res.getString("department.name");
				dep = new Department(mainDept,depName);
				secDep.setString(1, c);
				ResultSet res2 = secDep.executeQuery();
				while (res2.next()) {
					String deptC = res2.getString("dept");
					deptList.add(dep.getDept(deptC));
				}
				
				degree = new Degree(name, dep,deptList,type,placement,fouryears);
				degree.setCode(code);
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
		public static ArrayList<String> getAllDegreeCodes() throws Exception {
			Degree degree = null;
			ArrayList<String> degreeList = new ArrayList<String>();
			
			connectToDB();
			Statement stmt = con.createStatement();
			PreparedStatement deg= null;
			deg = con.prepareStatement("SELECT code FROM degree; " );
			
			
			try {
				ResultSet res  = deg.executeQuery();
				
				while(res.next()) {
					
					String dCode = res.getString("code");
					System.out.println(dCode + "   oklfm");
					
					
					degreeList.add(dCode);
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
			// Degree v = new Degree();
			
//			try {
//				c.createDept();
//				b = c.getDept("BUS");
//				l = c.getDept("LAN");
//				deptList.add(b);
//				deptList.add(l);
//				Degree t = new Degree ("BSc Information Systems",c,deptList,"Undergraduate",false);
//				t.setCode();
//				System.out.println(t.getCode());
//				/*
//				System.out.println(t.getName());
//				System.out.println(t.getMainDept().getName());
//				System.out.println(t.getSeconDepts().toString());
//				System.out.println(t.getType());
//				System.out.println(t.getPlacement());
//				
//				System.out.println("Tudo bem");*/
//				degreeList = t.getAllDegree();
//				//System.out.println("Tudo bem2");
//				for(Degree str:degreeList)  
//			        System.out.println(str.getName()+ str.getCode() + "What1");
//				t.createDegree();
//				//t.deleteDegree();
//				degreeList = t.getAllDegree();
//				for(Degree str:degreeList)  
//			        System.out.println(str.getName()+ str.getCode() + "  What4");
//				t.deleteDegree();
//				
//				
//				degreeList = v.getAllDegree();
//				//System.out.println(degreeList.isEmpty());
//				for(Degree str:degreeList)  
//			        System.out.println(str.getName()+ str.getCode()+ "What2sy");
//				/*
//				t.deleteDegree();
//				
//				degreeList = t.getAllDegree();
//				for(Degree str:degreeList)  
//			        System.out.println(str.getName()+ str.getCode() +"Apagado");
//				*/
//			} catch(Exception ex) {
//				ex.printStackTrace();
//			} 
		}
		
		
	
}