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
      } catch (Exception ex) {
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
    
    public Student(int reg, Degree deg, String tut) {
    	this.registrationNumber = reg;
    	this.degree = deg;
    	this.tutor = tut;
    	this.accountDetails = null;
    }
    
    public static Student getStudentReg(int r) throws Exception {
		Student s = null;
		String dcode = null;
		PreparedStatement stu = null;
		connectToDB();
		try {
			stu = con.prepareStatement("SELECT * FROM student WHERE registrationNumber = ?");
			stu.setInt(1, r);
			ResultSet res = stu.executeQuery();
			res.next();
			dcode = res.getString("degree");
			Degree deg = Degree.getDegree(dcode);
			String tutor = res.getString("tutor");
			
			s = new Student(r, deg, tutor);
			res.close();
			
		 }catch (Exception ex) {
			 ex.printStackTrace();
		 }finally {
				if (stu != null)
					stu.close();
			}
		con.close();
		return s;
    }
    
    public static void connectToDB() throws SQLException {
		   try {
			   con = DriverManager.getConnection("jdbc:mysql://stusql.dcs.shef.ac.uk/team002", "team002", "e8f208af");
		   }
		   catch(Exception ex) {
			   ex.printStackTrace();
		   }
	}
	
	public Student createStudent() throws SQLException {

		connectToDB();
		Statement stmt = null;

		try {
			stmt = con.createStatement();
			String query = String.format("INSERT INTO student (registrationNumber, degree, tutor, username) VALUES (%d, \"%s\", \"%s\", \"%s\");",
					this.registrationNumber, this.degree.getCode(), this.tutor, this.accountDetails.getUsername());
			int count = stmt.executeUpdate(query);
			query = String.format("INSERT INTO studentStatus (registrationNumber, level, period) VALUES (%d, \"%s\", \"%s\");", this.registrationNumber, '1', 'A');
			System.out.println(query);
			count = stmt.executeUpdate(query);
					

			System.out.println(count);
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (stmt != null)
				stmt.close();
		}
		con.close();

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
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (stmt != null)
				stmt.close();
		}
		con.close();
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
    	con.close();

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
			stmt = con.createStatement();
			ResultSet res = stmt.executeQuery(String.format("SELECT level FROM studentStatus WHERE registrationNumber = %d", regNum));
			
			res.next();
			result = res.getString("level");
			res.close();
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
				stuList.add((ArrayList) st.clone());	
			}
			res.close();
			
		 }catch (Exception ex) {
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
		connectToDB();
		
		try {

			stmt = con.createStatement();
			ResultSet res = stmt.executeQuery(String.format("SELECT * FROM studentStatus WHERE registrationNumber = %d", regNum));
			
			res.next();
			level = res.getString("level").charAt(0);
			period = res.getString("period");
			registered = res.getBoolean("registered");
			boolean graduated = res.getBoolean("graduated");
			boolean resitting = res.getBoolean("resitting");
			
			status = new StudentStatus(regNum, level, period, registered, graduated, resitting);
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (stmt != null) {
				stmt.close();
			}
		}
		con.close();

		return status;
	}
	
	public static Student getStudent(String u) throws Exception {
		System.out.println(u);
		Student student = null;
		
		connectToDB();
		PreparedStatement stu = null, noStu = null;
		Statement stmt = con.createStatement();
		
		try {
			noStu = con.prepareStatement("SELECT COUNT(*) FROM student WHERE username = ?");
			stu = con.prepareStatement("SELECT * FROM student WHERE username = ?");
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
			
			
		 }catch (Exception ex) {
			 ex.printStackTrace();
		 }finally {
				if (stmt != null)
					stmt.close();
					noStu.close();
					stu.close();
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
				res.close();
			}

		} catch (Exception ex) {

			ex.printStackTrace();
		} finally {
			if (stmt != null) {
				stmt.close();
			}
		}
		con.close();

		return result;
	}
	
	public ArrayList<PeriodResult> getPrevResults() throws SQLException {
		Statement stmt = null;
		Connection con = null;
		ArrayList<PeriodResult> results = new ArrayList<PeriodResult>();
		PeriodResult result = null;
		
		StudentStatus status = null;
		try {
			con = DriverManager.getConnection("jdbc:mysql://stusql.dcs.shef.ac.uk/team002", "team002", "e8f208af");

			status = this.getStudentStatus();
				
			stmt = con.createStatement();
			String query = String.format("SELECT * FROM periodResult WHERE registrationNumber = %d AND passed = true;", this.registrationNumber);
			System.out.println(query);
			ResultSet res = stmt.executeQuery(query);
				
			while(res.next()) {
				char level = res.getString("level").charAt(0);
				String period = res.getString("period");
				int grade = res.getInt("grade");
					
				System.out.println("Past result, level = " + level + "period = " + period + "grade = " + grade);
				
				if (level != 'P') {
					result = new PeriodResult(this.registrationNumber, level, period, grade, true);
					results.add(result);
				}
			}
			res.close();

		} catch (Exception ex) {

			ex.printStackTrace();
		} finally {
			if (stmt != null) {
				stmt.close();
			}
		}
		con.close();
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
		con.close();
	}
	
	public boolean progress(Student student, ArrayList<ModuleGrades> grades) throws Exception {
		
		StudentStatus status = null;
		Degree degree = null;
		boolean conceded = false;
		boolean failed = false;
		PeriodResult currentResult = null;
		PeriodResult prevResult = null;
		ArrayList<PeriodResult> pastResults = null;
		
		String finalresult = null;
		DegreeResult degreeResult = null;
		
		status = student.getStudentStatus();
		degree = student.getDegree();
		prevResult = student.getLastResult();
		
		char level = status.getLevel();
		System.out.println("level = " + level);
		String period = status.getPeriod();
		System.out.println("period = " + period);
		int periodvalue = period.charAt(0);
		String prevPeriod = String.valueOf((char)(periodvalue - 1));
		System.out.println("prevperiod = " + prevPeriod);
		String nextPeriod = String.valueOf((char)(periodvalue + 1));
		System.out.println("nextperiod = " + nextPeriod);
		int credittotal = 0;
		
		if (level == 'P' && !(student.getDegree().getType().equals("BSc") || student.getDegree().getType().equals("BEng"))) {
			currentResult = new PeriodResult(student.getRegistrationNumber(), level, period, -1, true);
			currentResult.createPeriodResult();
			System.out.println("Year in industry to 4th year - pass");
			status.updateStatus('4', nextPeriod);
			return true;
		} else if (level == 'P') {
			currentResult = new PeriodResult(student.getRegistrationNumber(), level, period, -1, true);
			currentResult.createPeriodResult();
			System.out.println("Year in industry to 3rd year - pass");
			status.updateStatus('3', nextPeriod);
			return true;
		}
	
		int credits, grade, resit, modulegrade, weightedgrade;
		
		ArrayList<Integer> weightedGrades = new ArrayList<Integer>();
		
		if (status.isResitting()) {
			System.out.println("resitting");
			grades.addAll(ModuleChoice.getPastChoices(student.getRegistrationNumber(), prevPeriod));
		}
		
		
		for (ModuleGrades module : grades) {
			
			Module mod = module.getModule();
			System.out.println(mod.getCode() + mod.getCredits());
			credits = mod.getCredits();
			credittotal += credits;
			System.out.println("credittotal so far = " + credittotal);
				
				
			if (module.getResit()) {

				grade = module.getGrade();
				resit = module.getResitGrade();
				System.out.println("resit module, resit grade = " + resit + "first grade = " + grade);
				
				if (resit > grade && resit > 50 && level == '4') {
					System.out.println("4th year, mark reduced to 50");
					modulegrade = 50;
				} else if (resit > grade && resit > 40 && level != '4') {
					System.out.println("Mark reduced to 40");
					modulegrade = 40;
				} else if (resit > grade) {
					System.out.println("Resit grade used");
					modulegrade = resit;
				} else {
					System.out.println("First grade used");
					modulegrade = grade;
				}
			} else {
				modulegrade = module.getGrade();
				System.out.println("no resit, grade = " + modulegrade);
			}
				
			weightedgrade = (modulegrade * credits);
			
			weightedGrades.add(weightedgrade);
			
			if (level == '4' && modulegrade < 40 && conceded == false && credits == 15) {
				System.out.println("4th year, first module failed, conceded pass so far");
				conceded = true;
			} else if (level == '4' && modulegrade < 50) {
				System.out.println("4th year, too many failed, year failed");
				failed = true;
			} else if (modulegrade < 30 && conceded == false && credits == 20) {
				System.out.println("First module failed, conceded pass so far");
				conceded = true;
			} else if (modulegrade < 40) {
				System.out.println("Too many failed, year failed");
				failed = true;
			} else {
				System.out.println("Module passed, grade recorded");
				ModuleChoice newmodule = new ModuleChoice(student.getRegistrationNumber(), module.getModule().getCode(), period, modulegrade);
				
				newmodule.createModuleChoice();
			}
		}
		
		int weightedmean = 0;
		
		for (int wg : weightedGrades) {
			weightedmean += wg;
		}
		
		weightedmean = weightedmean / credittotal; 
		
		System.out.println("Weighted mean grade = " + weightedmean);
		
		if (degree.getType().equals("MSc")) {
			System.out.println("1 year masters degree");
			float finalgrade = weightedmean;
			
			if (failed & status.isResitting()) {
				System.out.println("Degree failed, final grade = " + finalgrade);
				currentResult = new PeriodResult(student.getRegistrationNumber(), level, period, weightedmean, false);
				currentResult.createPeriodResult();
				degreeResult = new DegreeResult(student.getRegistrationNumber(), true, "fail");
				degreeResult.createDegreeResult();
			} else if (failed) {
				System.out.println("Year failed, student will resit next period");
				currentResult = new PeriodResult(student.getRegistrationNumber(), level, period, weightedmean, false);
				currentResult.createPeriodResult();
				status.updateStatus(level, nextPeriod);
				status.setRegistered(true);
				status.setResitting(true);
			} else {
				System.out.println("Final grade = " + weightedmean);
				
				if (weightedmean < 49.5) {
					finalresult = "fail";
					currentResult = new PeriodResult(student.getRegistrationNumber(), level, period, weightedmean, false);
				} else if (weightedmean < 59.5) {
					finalresult = "pass";
					currentResult = new PeriodResult(student.getRegistrationNumber(), level, period, weightedmean, true);
				} else if (weightedmean < 69.5) {
					finalresult = "merit";
					currentResult = new PeriodResult(student.getRegistrationNumber(), level, period, weightedmean, true);
				} else {
					finalresult = "distinction";
					currentResult = new PeriodResult(student.getRegistrationNumber(), level, period, weightedmean, true);
				}
				
				System.out.println("finalresult = " + finalresult);
				
				currentResult.createPeriodResult();
				degreeResult = new DegreeResult(student.getRegistrationNumber(), true, finalresult);
				degreeResult.createDegreeResult();
			}
		}
		
		if (failed && status.isResitting()) {
			if (level == '4') {
				pastResults = this.getPrevResults();
				float finalgrade = 0;
				System.out.println("4th year failed twice, getting past results");
				
				for (PeriodResult result : pastResults) {
					if (result.getLevel() == '2') {
						System.out.println("2nd year grade = " + result.getGrade());
						finalgrade += result.getGrade();
					} else {
						System.out.println("3rd year grade = " + result.getGrade());
						finalgrade += result.getGrade() * 2;
					}
				}
				
				finalgrade = finalgrade / 3;
				System.out.println("Final grade = " + finalgrade);
				
				if (finalgrade < 49.5) {
					finalresult = "fail";
				} else if (finalgrade < 59.5) {
					finalresult = "lower second";
				} else if (finalgrade < 69.5) {
					finalresult = "upper second";
				} else {
					finalresult = "first class";
				}
				System.out.println("Final result = " + finalresult);
				
				currentResult = new PeriodResult(student.getRegistrationNumber(), level, period, weightedmean, true);
				currentResult.createPeriodResult();
				degreeResult = new DegreeResult(student.getRegistrationNumber(), false, finalresult);
				degreeResult.createDegreeResult();
				status.setGraduated();
				System.out.println("Student graduated with bachelors, degree result recorded");
				return true;
				
			} else {
				System.out.println("Year failed twice, degree failed");
				currentResult = new PeriodResult(student.getRegistrationNumber(), level, period, weightedmean, false);
				currentResult.createPeriodResult();
				degreeResult = new DegreeResult(student.getRegistrationNumber(), false, "fail");
				degreeResult.createDegreeResult();
				status.setGraduated();
				return true;
			}
		} else if (failed) {
				System.out.println("Year failed, student will resit next period");
				currentResult = new PeriodResult(student.getRegistrationNumber(), level, period, weightedmean, false);
				currentResult.createPeriodResult();
				status.updateStatus(level, nextPeriod);
				status.setResitting(true);
				status.setRegistered(true);
				return true;
		} else {
			if (level == '4') {
				System.out.println("4th year passed, getting past results");
				pastResults = this.getPrevResults();
				float finalgrade = 0;
				
				for (PeriodResult result : pastResults) {
					if (result.getLevel() == '1') {
						System.out.println("1st year doesn't count");
					} else if (result.getLevel() == '2') {
						System.out.println("2nd year grade = " + result.getGrade());
						finalgrade += result.getGrade();
					} else {
						System.out.println("3rd year grade = " + result.getGrade());
						finalgrade += result.getGrade() * 2;
					}
				}
				
				System.out.println("4th year grade = " + weightedmean);
				finalgrade += weightedmean * 2;
				
				finalgrade = finalgrade / 5;
				System.out.println("Final grade = " + finalgrade);
				
				if (finalgrade < 49.5) {
					finalresult = "fail";
					currentResult = new PeriodResult(student.getRegistrationNumber(), level, period, weightedmean, false);
				} else if (finalgrade < 59.5) {
					finalresult = "lower second";
					currentResult = new PeriodResult(student.getRegistrationNumber(), level, period, weightedmean, true);
				} else if (finalgrade < 69.5) {
					finalresult = "upper second";
					currentResult = new PeriodResult(student.getRegistrationNumber(), level, period, weightedmean, true);
				} else {
					finalresult = "first class";
					currentResult = new PeriodResult(student.getRegistrationNumber(), level, period, weightedmean, true);
				}
				
				System.out.println("Final result = " + finalresult);
				
				currentResult.createPeriodResult();
				degreeResult = new DegreeResult(student.getRegistrationNumber(), true, finalresult);
				degreeResult.createDegreeResult();
				status.setGraduated();
				System.out.println("Student graduated with masters, degree result recorded");
				return true;
					
				} else if (level == '3' && (student.getDegree().getType().equals("BSc") || student.getDegree().getType().equals("BEng"))) {
					System.out.println("3rd year of bachelors passed, getting past results");
					pastResults = this.getPrevResults();
					float finalgrade = 0;
					
					for (PeriodResult result : pastResults) {
						if (result.getLevel() == '1') {
							System.out.println("1st year doesn't count");
						} else {
							System.out.println("2nd year grade = " + result.getGrade());
							finalgrade += result.getGrade();
						}
					}
					
					System.out.println("3rd year grade = " + weightedmean);
					finalgrade += weightedmean * 2;
					
					finalgrade = finalgrade / 3;
					System.out.println("Final grade = " + finalgrade);
					
					if (finalgrade < 39.5) {
						finalresult = "fail";
						currentResult = new PeriodResult(student.getRegistrationNumber(), level, period, weightedmean, false);
					} else if (finalgrade < 44.5) {
						finalresult = "pass (non-honours)";
						currentResult = new PeriodResult(student.getRegistrationNumber(), level, period, weightedmean, true);
					} else if (finalgrade < 49.5) {
						finalresult = "third class";
						currentResult = new PeriodResult(student.getRegistrationNumber(), level, period, weightedmean, true);
					} else if (finalgrade < 59.5) {
						finalresult = "lower second";
						currentResult = new PeriodResult(student.getRegistrationNumber(), level, period, weightedmean, true);
					} else if (finalgrade < 69.5) {
						finalresult = "upper second";
						currentResult = new PeriodResult(student.getRegistrationNumber(), level, period, weightedmean, true);
					} else {
						finalresult = "first class";
						currentResult = new PeriodResult(student.getRegistrationNumber(), level, period, weightedmean, true);
					}
					
					System.out.println("Final result = " + finalresult);
					
					if (status.isResitting() && finalgrade >= 39.5) {
						System.out.println("Student resat so can't pass with honours");
						currentResult.createPeriodResult();
						degreeResult = new DegreeResult(student.getRegistrationNumber(), false, "pass (non-honours)");
						degreeResult.createDegreeResult();
						status.setGraduated();
						System.out.println("Student graduated with bachelors, degree result recorded");
						return true;
					} else {
						currentResult.createPeriodResult();
						degreeResult = new DegreeResult(student.getRegistrationNumber(), false, finalresult);
						degreeResult.createDegreeResult();
						status.setGraduated();
						System.out.println("Student graduated with bachelors, degree result recorded");
						return true;
					}
			} else {
				System.out.println("Not the last year, recording result for period");
				currentResult = new PeriodResult(student.getRegistrationNumber(), level, period, weightedmean, true);
				currentResult.createPeriodResult();
				
				int nextLevel = 0;
				
				if (student.getDegree().getPlacement()) {
					if (level == '3' && !(student.getDegree().getType().equals("BSc") || student.getDegree().getType().equals("BEng"))) {
						System.out.println("Student going on placement, next level is P, next period is " + nextPeriod);
						status.updateStatus('P', nextPeriod);
						status.setRegistered(true);
						return true;
					} else if (level == '2' && (student.getDegree().getType().equals("BSc") || student.getDegree().getType().equals("BEng"))) {
						System.out.println("Student going on placement, next level is P, next period is " + nextPeriod);
						status.updateStatus('P', nextPeriod);
						status.setRegistered(true);
						return true;
					}
				}
				
				nextLevel = Integer.parseInt(Character.toString(level)) + 1;
				char nextLevelC = Integer.toString(nextLevel).charAt(0);
				System.out.println("Next level is " + nextLevelC + "next period is " + nextPeriod);
				status.updateStatus(nextLevelC, nextPeriod);
				return true;
			}
		}
	}
	
	public void setModuleChoices(ArrayList<Module> modules) throws SQLException {
		StudentStatus status = this.getStudentStatus();
		String period = status.getPeriod();
		int reg = this.registrationNumber;
		
		connectToDB();
		Statement stmt = null;
		String result = null;
		
		try {
			stmt = con.createStatement();
			int count = stmt.executeUpdate(String.format("DELETE FROM moduleChoice WHERE registrationNumber = %d", reg));
			
			for (Module module : modules) {
				String moduleCode = module.getCode();
				count += stmt.executeUpdate(String.format("INSERT INTO moduleChoice (registrationNumber, moduleCode, period) VALUES (%d, \"%s\", \"%s\");", reg, moduleCode, period));
				count += stmt.executeUpdate(String.format("UPDATE studentStatus SET registered = 1 WHERE registrationNumber = %d;", reg));
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (stmt != null) {
				stmt.close();
			}
		}
		con.close();
	}
}

