package university;

public class PeriodResult {
	
	private int registrationNumber;
	private char level;
	private String period;
	private int grade;
	private boolean passed;
	
	public PeriodResult(int r, char l, String p, int g, boolean pass) {
		this.registrationNumber = r;
		this.level = l;
		this.period = p;
		this.grade = g;
		this.passed = pass;
	}
	
	public int getRegistrationNumber() {
		return registrationNumber;
	}
	
	public char getLevel() {
		return level;
	}
	
	public String getPeriod() {
		return period;
	}
	
	public int getGrade() {
		return grade;
	}
	
	public boolean isPassed() {
		return passed;
	}
	
	

}
