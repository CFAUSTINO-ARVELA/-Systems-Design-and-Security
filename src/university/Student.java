package university;

import java.sql.*;
import java.util.ArrayList;

public class Student {

    private Degree degree;
    private int registrationNumber;
    private String tutor; // needs to be type Teacher
    private Account accountDetails;
    private static Connection con;

    public Student(Degree deg, String tut, Account acc) {  
      this.degree = deg;
      
      try {
    	  this.registrationNumber = this.generateRegistrationNumber();
      } catch (SQLException ex) {
    	  ex.printStackTrace();
      }
      
      this.tutor = tut;
      this.accountDetails = acc;
    }
    
    public Student(int r) {
    	this.registrationNumber = r;
    }
    
    public Student(int reg, Degree deg, String tut, Account acc) {  
    	this.registrationNumber = reg;
        this.degree = deg;
        this.tutor = tut;
        this.accountDetails = acc;
      }
    
    public static void connectToDB() throws SQLException {
		   try {
			   con = DriverManager.getConnection("jdbc:mysql://stusql.dcs.shef.ac.uk/team002", "team002", "e8f208af");
		   }
		   catch(SQLException ex) {
			   ex.printStackTrace();
		   }
	}
	
	public Student createStudent() throws SQLException {

		connectToDB();
		Statement stmt = null;

		try {
			stmt = con.createStatement();
			String query = String.format("INSERT INTO student (registrationNumber, degree, tutor, username) VALUES (%d, \"%s\", \"%s\", \"%s\");",
					this.registrationNumber, this.degree.getName(), this.tutor, this.accountDetails.getUsername());
			int count = stmt.executeUpdate(query);
			query = String.format("INSERT INTO studentStatus (registrationNumber, level, period) VALUES (%d, \"%s\", \"%s\");", this.registrationNumber, '1', 'A');
			System.out.println(query);
			count = stmt.executeUpdate(query);
					

			System.out.println(count);
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			if (stmt != null)
				stmt.close();
		}
		
		return this;

	}
	
	public void deleteStudent() throws SQLException {
		
		connectToDB();
		Statement stmt = null;

		try {
			
			stmt = con.createStatement();
			int count = stmt.executeUpdate(
					String.format("DELETE FROM student WHERE registrationNumber = %d;", this.registrationNumber));
			count = stmt.executeUpdate(String.format("DELETE FROM studentStatus WHERE registrationNumber = %d;", this.registrationNumber));

			System.out.println(count);
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			if (stmt != null)
				stmt.close();
		}
	}

    public Degree getDegree() {
        return this.degree;
    }
    
    public int generateRegistrationNumber() throws SQLException {
    	
    	connectToDB();
    	Statement stmt = null;
    	int count = 1;
    	
    	try {
			

			stmt = con.createStatement();
			String query = "SELECT COUNT(*) FROM student;";
			
			ResultSet res = stmt.executeQuery(query);
			
			res.next();
			count = res.getInt(1);
    	} catch (SQLException ex) {
    		ex.printStackTrace();
    	} finally {
    		if (stmt != null)
    			stmt.close();
    	}
    	
    	return (count + 1);
    }
    
    public int getRegistrationNumber() {
    	return this.registrationNumber;
    }
    
    public String getTutor() {
    	return this.tutor;
    }
    
	public String getLevel() throws SQLException {
		int regNum = this.getRegistrationNumber();
		
		connectToDB();
		Statement stmt = null;
		String result = null;
		
		try {
			connectToDB();
			stmt = con.createStatement();
			ResultSet res = stmt.executeQuery(String.format("SELECT level FROM studentStatus WHERE registrationNumber = %d", regNum));
			
			res.next();
			result = res.getString("level");
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			if (stmt != null) {
				stmt.close();
			}
		}
		
		return result;
	}
	
	public static ArrayList<ArrayList<String>> getStutList() throws SQLException  {
		ArrayList<ArrayList<String>> stuList = new ArrayList<ArrayList<String>>();
		ArrayList<String> st = new ArrayList<String>();
		ResultSet res = null;
		PreparedStatement dept = null;
		connectToDB();
		dept = con.prepareStatement("SELECT * FROM student;");
		try {
			res  = dept.executeQuery();
			st.add("Registration No");
			st.add("Degree");
			st.add("Tutor");
			st.add("Username");
			stuList.add((ArrayList) st.clone());
			while (res.next()) {
				st.clear();
				st.add(res.getString("RegistrationNumber"));
				st.add(res.getString("Degree"));
				st.add(res.getString("Tutor"));
				st.add(res.getString("Username"));
				//System.out.println("Depart" + depart.toString());
				stuList.add((ArrayList) st.clone());
				//for (int o = 0; o < deptList.size(); o++) {
				//	System.out.println("deptList" + deptList.get(o).toString());
				//	}
			}
			res.close();
			
		 }catch (SQLException ex) {
			 ex.printStackTrace();
		 }finally {
				if (dept != null)
					dept.close();
			}
		con.close();
		return stuList;
	}
	
	public StudentStatus getStudentStatus() throws SQLException {
		
		int regNum = this.getRegistrationNumber();
		StudentStatus status = null;
		Statement stmt = null;
		char level;
		String period = null;
		boolean registered;
		
		
		try {
			connectToDB();
			stmt = con.createStatement();
			ResultSet res = stmt.executeQuery(String.format("SELECT * FROM studentStatus WHERE registrationNumber = %d", regNum));
			
			res.next();
			level = res.getString("level").charAt(0);
			period = res.getString("period");
			registered = res.getBoolean("registered");
			
			status = new StudentStatus(regNum, level, period, registered);
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			if (stmt != null) {
				stmt.close();
			}
		}
		
		return status;
	}
	
	public static Student getStudent(String u) throws Exception {
		System.out.println(u);
		Student student = null;
		
		connectToDB();
		PreparedStatement stu,noStu = null;
		noStu = con.prepareStatement("SELECT COUNT(*) FROM student WHERE username = ?");
		stu = con.prepareStatement("SELECT * FROM student WHERE username = ?");
		Statement stmt = con.createStatement();
		
		try {
			noStu.setString(1, u);
			System.out.println(noStu);
			ResultSet res1 = noStu.executeQuery();
			res1.next();
			
			if(res1.getInt(1) != 0) {
				stu.setString(1, u);
				ResultSet res = stu.executeQuery();
				res.next();
				int registrationNumber = res.getInt("registrationNumber");
				String degreeCode = res.getString("degree");
				Degree degree = Degree.getDegree(degreeCode);
				String tutor = res.getString("tutor");
				String username = res.getString("username");
				Account acc = new Account(username);
				
				student = new Student(registrationNumber, degree, tutor, acc);
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
		return student;
	}
	
	public PeriodResult getLastResult() throws SQLException {
		
		connectToDB();
		Statement stmt = null;
		PeriodResult result = null;
		
		StudentStatus status = null;
		try {
			status = this.getStudentStatus();
			
			String currentperiod = status.getPeriod();
			int periodvalue = currentperiod.charAt(0);
			String prev = String.valueOf((char)(periodvalue - 1));
			
			if (currentperiod.equals("A")) {
				
			} else {
				stmt = con.createStatement();
				ResultSet res = stmt.executeQuery(String.format("SELECT * FROM periodResult WHERE registrationNumber = %d AND period = \"%s\";", this.registrationNumber, prev));
				
				res.next();
				char level = res.getString("level").charAt(0);
				int grade = res.getInt("grade");
				boolean passed = res.getBoolean("passed");
				
				result = new PeriodResult(this.registrationNumber, level, prev, grade, passed);
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			if (stmt != null) {
				stmt.close();
			}
		}
		
		return result;
	}
	
	public ArrayList<PeriodResult> getPrevResults() throws SQLException {
		connectToDB();
		Statement stmt = null;
		ArrayList<PeriodResult> results = new ArrayList<PeriodResult>();
		PeriodResult result = null;
		
		StudentStatus status = null;
		try {
			status = this.getStudentStatus();
				
				stmt = con.createStatement();
				ResultSet res = stmt.executeQuery(String.format("SELECT * FROM periodResult WHERE registrationNumber = %d AND passed = true;", this.registrationNumber));
				
				while(res.next()) {
					char level = res.getString("level").charAt(0);
					String period = res.getString("period");
					int grade = res.getInt("grade");
				
					result = new PeriodResult(this.registrationNumber, level, period, grade, true);
					results.add(result);
				}
				
				
			
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			if (stmt != null) {
				stmt.close();
			}
		}
		
		return results;
	}
	
	
	public void logResult(char l, String p, int g, boolean pass) throws SQLException {
		connectToDB();
		Statement stmt = null;

		try {
			
			stmt = con.createStatement();
			int count = stmt.executeUpdate(
					String.format("INSERT INTO periodResult (registrationNumber, level, period, grade, passed) VALUES (%d, \"%s\", \"%s\", %d, %b);", this.registrationNumber, l, p, g, pass));
			
			System.out.println(count);
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			if (stmt != null)
				stmt.close();
		}
	}
	
	public boolean progress(Student student, ArrayList<ModuleGrades> grades) throws Exception {
		
		
		StudentStatus status = null;
		boolean conceded = false;
		boolean failed = false;
		PeriodResult currentResult = null;
		PeriodResult prevResult = null;
		ArrayList<PeriodResult> pastResults = null;
		
		String finalresult = null;
		DegreeResult degreeResult = null;
		
		status = student.getStudentStatus();
		prevResult = student.getLastResult();
		
		char level = status.getLevel();
		String period = status.getPeriod();
		int periodvalue = period.charAt(0);
		String prevPeriod = String.valueOf((char)(periodvalue - 1));
		String nextPeriod = String.valueOf((char)(periodvalue + 1));
		int credittotal = 0;
		
		if (level == 'P' && student.getDegree().getType().equals("Undergraduate")) {
			status.updateStatus('4', nextPeriod);
			return true;
		} else if (level == 'P') {
			status.updateStatus('3', nextPeriod);
			return true;
		}
	
		int credits, grade, resit, modulegrade, weightedgrade;
		
		ArrayList<Integer> weightedGrades = new ArrayList<Integer>();
		
		if (status.isResitting()) {
			grades.addAll(ModuleChoice.getPastChoices(student.getRegistrationNumber(), prevPeriod));
		}
		
		
		for (ModuleGrades module : grades) {
			

			credits = module.getModule().getCredits();
			credittotal += credits;
				
				
			if (module.getResit()) {
				grade = module.getGrade();
				resit = module.getResitGrade();
				
				if (resit > grade && resit > 50 && level == '4') {
					modulegrade = 50;
				} else if (resit > grade && resit > 40 && level != '4') {
					modulegrade = 40;
				} else if (resit > grade) {
					modulegrade = resit;
				} else {
					modulegrade = grade;
				}
			} else {
				modulegrade = module.getGrade();
			}
				
			weightedgrade = (modulegrade * credits);
			
			weightedGrades.add(weightedgrade);
			
			if (level == '4' && modulegrade < 40 && conceded == false && credits == 15) {
				conceded = true;
			} else if (level == '4' && modulegrade < 50) {
				failed = true;
			} else if (modulegrade < 30 && conceded == false && credits == 20) {
				conceded = true;
			} else if (modulegrade < 40) {
				failed = true;
			} else {
				ModuleChoice newmodule = new ModuleChoice(student.getRegistrationNumber(), module.getModule().getCode(), period, modulegrade);
				
				newmodule.createModuleChoice();
			}
		}
		
		int weightedmean = 0;
		
		for (int wg : weightedGrades) {
			weightedmean += wg;
		}
		
		weightedmean = weightedmean / credittotal; 
		
		if (failed && status.isResitting()) {
			if (level == '4') {
				pastResults = this.getPrevResults();
				float finalgrade = 0;
				
				for (PeriodResult result : pastResults) {
					if (result.getPeriod().equals("1")) {
					} else if (result.getPeriod().equals("2")) {
						finalgrade += result.getGrade();
					} else {
						finalgrade += result.getGrade() * 2;
					}
				}
				
				finalgrade = finalgrade / 3;
				
				if (finalgrade < 49.5) {
					finalresult = "fail";
				} else if (finalgrade < 59.5) {
					finalresult = "lower second";
				} else if (finalgrade < 69.5) {
					finalresult = "upper second";
				} else {
					finalresult = "first class";
				}
				
				degreeResult = new DegreeResult(student.getRegistrationNumber(), false, finalresult);
				degreeResult.createDegreeResult();
				status.setGraduated();
				return true;
				
			} else {
				degreeResult = new DegreeResult(student.getRegistrationNumber(), false, "fail");
				degreeResult.createDegreeResult();
				status.setGraduated();
				return true;
			}
		} else if (failed) {
				status.updateStatus(level, nextPeriod);
				status.setResitting(true);
				return true;
		} else {
			if (level == '4') {
				pastResults = this.getPrevResults();
				float finalgrade = 0;
				
				for (PeriodResult result : pastResults) {
					if (result.getPeriod().equals("1")) {
					} else if (result.getPeriod().equals("2")) {
						finalgrade += result.getGrade();
					} else {
						finalgrade += result.getGrade() * 2;
					}
				}
				
				finalgrade += weightedmean * 2;
				
				finalgrade = finalgrade / 5;
				
				if (finalgrade < 49.5) {
					finalresult = "fail";
				} else if (finalgrade < 59.5) {
					finalresult = "lower second";
				} else if (finalgrade < 69.5) {
					finalresult = "upper second";
				} else {
					finalresult = "first class";
				}
				
				degreeResult = new DegreeResult(student.getRegistrationNumber(), true, finalresult);
				degreeResult.createDegreeResult();
				status.setGraduated();
				return true;
					
				} else if (level == '3' && student.getDegree().getType().equals("Undergraduate")) {
					pastResults = this.getPrevResults();
					float finalgrade = 0;
					
					for (PeriodResult result : pastResults) {
						if (result.getPeriod().equals("1")) {
						} else if (result.getPeriod().equals("2")) {
							finalgrade += result.getGrade();
						} else {
							finalgrade += result.getGrade() * 2;
						}
					}
					
					finalgrade += weightedmean * 2;
					
					finalgrade = finalgrade / 3;
					
					if (finalgrade < 39.5) {
						finalresult = "fail";
					} else if (finalgrade < 44.5) {
						finalresult = "pass (non-honours)";
					} else if (finalgrade < 49.5) {
						finalresult = "third class";
					} else if (finalgrade < 59.5) {
						finalresult = "lower second";
					} else if (finalgrade < 69.5) {
						finalresult = "upper second";
					} else {
						finalresult = "first class";
					}
					
					if (status.isResitting() && finalgrade >= 39.5) {
						degreeResult = new DegreeResult(student.getRegistrationNumber(), false, "pass (non-honours)");
						degreeResult.createDegreeResult();
						status.setGraduated();
						return true;
					} else {
						degreeResult = new DegreeResult(student.getRegistrationNumber(), false, finalresult);
						degreeResult.createDegreeResult();
						status.setGraduated();
						return true;
					}
			} else {
				currentResult = new PeriodResult(student.getRegistrationNumber(), level, period, weightedmean, true);
				currentResult.createPeriodResult();
				
				int nextLevel = 0;
				
				if (student.getDegree().getPlacement()) {
					if (level == '3' && !student.getDegree().getType().equals("Undergraduate")) {
						status.updateStatus('P', nextPeriod);
						return true;
					} else if (level == '4' && student.getDegree().getType().equals("Undergraduate")) {
						status.updateStatus('P', nextPeriod);
						return true;
					}
				}
				
				nextLevel = Integer.parseInt(Character.toString(level)) + 1;
				char nextLevelC = Integer.toString(nextLevel).charAt(0);
				status.updateStatus(nextLevelC, nextPeriod);
				return true;
			}
		}
		
		return false;	
	}
	
	} 
