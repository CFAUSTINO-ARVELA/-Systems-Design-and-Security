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
	private static Connection con;
	
	//Constructor for degree with just one department
	public Degree(String name, Department mainDept,
			String type,Boolean placement) throws Exception{
		this.name = name;
		this.mainDept = mainDept;
		this.type = type;
		this.placement = placement;
		this.seconDepts = new ArrayList<Department>();
	}
	
	//Constructor for degree with several departments
	public Degree(String name, Department mainDept,
			 ArrayList<Department> seconDepts, String type,Boolean placement) throws Exception{
		this.name = name;
		this.mainDept = mainDept;
		this.seconDepts = seconDepts;
		this.type = type;
		this.placement = placement;
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
		
		String degCode = getMainDept().getCode();
		if (getType() == "MSc")
			degCode += "P";
		else
			degCode += "U";
		//System.out.println(degCode+"%");
		connectToDB();
		PreparedStatement noDeg = null;
		noDeg = con.prepareStatement("SELECT MAX(code) FROM degree WHERE mainDept =  ? AND code LIKE ?");
		try {
			noDeg.setString(1, getMainDept().getCode());
			noDeg.setString(2, degCode+"%");
			//System.out.println(noDeg.toString());
			ResultSet res = noDeg.executeQuery();
			res.next();
			//System.out.println(res.getString("MAX(code)"));
			//System.out.println(res.getString("MAX(code)") == null);
			//System.out.println(res.getString(0).isEmpty()+ "Aqui");
			if(res.getString("MAX(code)") != null)
				no = Integer.parseInt(res.getString("MAX(code)").substring(4)) + 1;
		 }catch (SQLException ex) {
			 ex.printStackTrace();
		 }finally {
				if (noDeg != null)
					noDeg.close();
			}
		
		degCode += String.format("%02d", no); 
		//System.out.println(degCode);
		con.close();
		setCode(degCode);
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
	public int createDegree() throws Exception  {
		connectToDB();
		PreparedStatement newDeg,deg, newSeconDep= null;
		int count = 0;
		deg = con.prepareStatement("SELECT COUNT(*) FROM degree WHERE name = ?");
		newDeg = con.prepareStatement("INSERT INTO degree (code, name, mainDept, type, placement)  VALUES (?,?,?,?,?)");
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
				deg.close();
				newDeg.close();
				newSeconDep.close();
		}
		con.close();
		return count;
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
					deg.close();
					delDeg.close();
					delSDept.close();
			}
		con.close();
		return count;
	}

	
	//Get a degree using the code (return a degree object)
	public static Degree getDegree(String c) throws Exception {
		Degree degree = null;
		ArrayList<Department> deptList = new ArrayList<Department>();
		Department dep = null;
		//System.out.println(c);
		
		connectToDB();
		PreparedStatement deg,noDeg,secDep = null;
		noDeg = con.prepareStatement("SELECT COUNT(*) FROM degree WHERE code = ?");
		deg = con.prepareStatement("SELECT * FROM degree JOIN department WHERE degree.mainDept=department.code AND degree.code =  ?");
		secDep = con.prepareStatement("SELECT dept FROM seconDepts WHERE degreeCode = ?");
		
		try {
			noDeg.setString(1, c);
			//System.out.println(noDeg);
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
				String depName = res.getString("department.name");
				dep = new Department(mainDept,depName);
				secDep.setString(1, c);
				ResultSet res2 = secDep.executeQuery();
				while (res2.next()) {
					String deptC = res2.getString("dept");
					deptList.add(dep.getDeptwCode(deptC));
				}
				
				degree = new Degree(name, dep,deptList,type,placement);
				degree.setCode(code);
				res.close();
				res1.close();
			}
			
			
		 }catch (SQLException ex) {
			 ex.printStackTrace();
		 }finally {
				if (deg != null)
					noDeg.close();
					deg.close();
					secDep.close();
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
					
		
					
					degreeList.add(dCode);
				}
				res.close();
				con.close();
			 }catch (SQLException ex) {
				 ex.printStackTrace();
			 }finally {
					if (stmt != null)
						deg.close();
						stmt.close();
				}
			
			return degreeList;
		}
		
		public static ArrayList<String> getAllDegNames() throws Exception {
			Degree degree = null;
			ArrayList<String> degreeList = new ArrayList<String>();
			
			connectToDB();
			Statement stmt = con.createStatement();
			PreparedStatement deg= null;
			deg = con.prepareStatement("SELECT name FROM degree; " );
			
			
			try {
				ResultSet res = deg.executeQuery();
				
				while(res.next()) {
					
					String dName = res.getString("name");
					
					degreeList.add(dName);
				}
				res.close();
				con.close();
			 }catch (SQLException ex) {
				 ex.printStackTrace();
			 }finally {
					if (stmt != null)
						deg.close();
						stmt.close();
				}
			
			return degreeList;
		}

		public static ArrayList<ArrayList<String>> getDegList() throws Exception  {
			ArrayList<ArrayList<String>> degList = new ArrayList<ArrayList<String>>();
			ArrayList<String> deg = new ArrayList<String>();
			ResultSet res = null;
			PreparedStatement dept = null;
			connectToDB();
			dept = con.prepareStatement("SELECT * FROM degree;");
			try {
				res  = dept.executeQuery();
				deg.add("Code");
				deg.add("Name");
				deg.add("Main Department");
				deg.add("Entry");
				deg.add("Placement");
				deg.add("Duration");
				degList.add((ArrayList) deg.clone());
				while (res.next()) {
					deg.clear();
					deg.add(res.getString("code"));
					deg.add(res.getString("name"));
					deg.add(res.getString("mainDept"));
					if (res.getString("type").matches("MSc"))
						deg.add("Postgraduate");
					else 
						deg.add("Undergraduate");
					if(res.getBoolean("placement") == true)
						deg.add("Yes");
					else
						deg.add("No");
					
					if (res.getString("type").matches("BSc") || res.getString("type").matches("BEng") )
						if (res.getBoolean("placement") == false)
							deg.add("3 Years");
						else 
							deg.add("4 Years");
					else if (res.getString("type").matches("MSc"))
							deg.add("1 year");
					else
						if (res.getBoolean("placement") == false)
							deg.add("4 Years");
						else 
							deg.add("5 Years");
							
					degList.add((ArrayList) deg.clone());
					
				}
				res.close();
				
			 }catch (SQLException ex) {
				 ex.printStackTrace();
			 }finally {
					if (dept != null)
						dept.close();
				}
			con.close();
			return degList;
		} 
		public ArrayList<ArrayList<String>> getDegModules() throws Exception  {
			ArrayList<ArrayList<String>> modList = new ArrayList<ArrayList<String>>();
			ArrayList<String> mod = new ArrayList<String>();
			ResultSet res = null;
			PreparedStatement getMod = null;
			connectToDB();
			getMod = con.prepareStatement("SELECT modCode, name, mandatory,year,duration, credits  FROM assoModDeg JOIN module WHERE assoModDeg.modCode = module.code AND degCode = ?;");
			try {
				getMod.setString(1, getCode());
				res  = getMod.executeQuery();
				mod.add("Code");
				mod.add("Name");
				mod.add("Type");
				mod.add("Year");
				mod.add("Duration");
				mod.add("Credits");
				modList.add((ArrayList) mod.clone());
				while (res.next()) {
					mod.clear();
					mod.add(res.getString("modCode"));
					System.out.println(res.getString("modCode") + "   " +res.getString("name"));
					mod.add(res.getString("name"));
					if (res.getBoolean("mandatory") == true)
						mod.add("Core");
					else
						mod.add("Optional");
					mod.add(res.getString("year"));
					mod.add(res.getString("duration"));
					mod.add(res.getString("credits"));
					modList.add((ArrayList) mod.clone());
				}
			
				res.close();
				
				
			 }catch (SQLException ex) {
				 ex.printStackTrace();
			 }finally {
					if (getMod != null)
						getMod.close();
				}
			con.close();
			return modList;
		} 
		
		
		public static ArrayList<Module> getCoreModules(Degree d, int l) throws Exception {
			ArrayList<Module> coreModules = new ArrayList<Module>();
			Module module = null;
			
			connectToDB();
			Statement stmt = con.createStatement();
			String degCode = d.getCode();
			//System.out.println(degCode);
			
			ResultSet res = stmt.executeQuery(String.format("SELECT modCode FROM assoModDeg WHERE degCode = \"%s\" AND year = %d AND mandatory = true;", degCode, l));
			
			while (res.next()) {
				module = Module.getModule(res.getString("modCode"));
				coreModules.add(module);
			}
			
			if (stmt != null) {
				stmt.close();
			}
			
			
			return coreModules;
		}
		
		public static ArrayList<Module> getOptionalModules(Degree d, int l) throws Exception {
			ArrayList<Module> optionalModules = new ArrayList<Module>();
			Module module = null;
			
			connectToDB();
			Statement stmt = con.createStatement();
			String degCode = d.getCode();
			
			ResultSet res = stmt.executeQuery(String.format("SELECT modCode FROM assoModDeg WHERE degCode = \"%s\" AND year = %d AND mandatory = false;", degCode, l));
			
			while (res.next()) {
				module = Module.getModule(res.getString("modCode"));
				optionalModules.add(module);
			}
			
			if (stmt != null) {
				stmt.close();
			}
			
			
			return optionalModules;
		}
		
		public static int getCredits(String degreeCode, int level) throws Exception {
			int totCredits = 0;
			connectToDB();
			PreparedStatement credSum = con.prepareStatement("SELECT SUM(credits) FROM module JOIN assoModDeg WHERE degCode = ? AND year = ? AND mandatory = true;");
			try {
				credSum.setString(1, degreeCode);
				credSum.setInt(2, level);
				ResultSet res = credSum.executeQuery();
				res.next();
				if (res.getInt(1) != 0) {
					totCredits = res.getInt(1);
				}
				res.close();
			 }catch (SQLException ex) {
				 ex.printStackTrace();
			 }finally {
					if (credSum != null)
						credSum.close();
						con.close();
				}
			
			return totCredits;
		}
		
		public static int getNoMod (String degreeCode, String moduleCode, int level) throws Exception{
			int noMod = 0;
			connectToDB();
			PreparedStatement credSum = con.prepareStatement("SELECT COUNT(*) FROM assoModDeg WHERE degCode = ? AND year = ? AND modCode = ?;");
			try {
				credSum.setString(1, degreeCode);
				credSum.setInt(2, level);
				credSum.setString(3, moduleCode);
				ResultSet res = credSum.executeQuery();
				res.next();
				if (res.getInt(1) != 0) {
					noMod = res.getInt(1);
				}
				res.close();
			 }catch (SQLException ex) {
				 ex.printStackTrace();
			 }finally {
					if (credSum != null)
						credSum.close();
						con.close();
				}
			
			return noMod;
		}
		/**
		public static void main(String[] args){
			ArrayList<Department> deptList = new ArrayList<Department>() ;
			try {
				Department c = new Department("cid", "dijfdiof");
				Department l,b;
				b = c.getDeptwCode("BUS");
				l = c.getDeptwCode("LAN");
				deptList.add(b);
				deptList.add(l);
				Degree t = new Degree ("BSc Information System4",c,deptList,"Undergraduate",false);
				t.setCode();
				//System.out.println(t.getCode());
			/**	
			System.out.println(t.getName());
				System.out.println(t.getMainDept().getName());
				System.out.println(t.getSeconDepts().size());
				System.out.println(t.getType());
			System.out.println(t.getPlacement());
			t.createDegree();
			System.out.println(t.getSeconDepts().size());
			t.deleteDegree();**/
		/**
				Degree v = t.getDegree("COMP01");
				System.out.println(v.getDegModules(v).size());
				System.out.println(v.getDegModules(v).get(0));
				System.out.println(v.getDegModules(v).get(1));
				/**
				System.out.println("Tudo bem");
				degreeList = t.getAllDegree();
				//System.out.println("Tudo bem2");
				for(Degree str:degreeList)  
			        System.out.println(str.getName()+ str.getCode() + "What1");
				int y =  t.createDegree();
				System.out.println(y + "Funcionou");
				//t.deleteDegree();
				degreeList = t.getAllDegree();
				for(Degree str:degreeList)  
			        System.out.println(str.getName()+ str.getCode() + "  What4");
				t.deleteDegree();
				
				
				degreeList = v.getAllDegree();
				//System.out.println(degreeList.isEmpty());
				for(Degree str:degreeList)  
			        System.out.println(str.getName()+ str.getCode()+ "What2sy");
				
				t.deleteDegree();
				
				degreeList = t.getAllDegree();
				for(Degree str:degreeList)  
			        System.out.println(str.getName()+ str.getCode() +"Apagado");
				
		**/
		/**
			} catch(Exception ex) {
				ex.printStackTrace();
			} 
		}*/
		
		
	
}