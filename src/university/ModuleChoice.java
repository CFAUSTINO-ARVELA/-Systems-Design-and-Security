package university;

public class ModuleChoice {
	
	private int registrationNumber;
	private String moduleCode;
	private String period;
	private int grade;
	private boolean complete;
	
	public ModuleChoice(int r, String m, String p, int g) {
		this.registrationNumber = r;
		this.moduleCode = m;
		this.period = p;
		this.grade = g;
		this.complete = true;
	}
	
	public ModuleChoice(int r, String m, String p) {
		this.registrationNumber = r;
		this.moduleCode = m;
		this.period = p;
		this.complete = false;
	}
	
	public int getRegistrationNumber() {
		return registrationNumber;
	}
	public String getModuleCode() {
		return moduleCode;
	}
	public String getPeriod() {
		return period;
	}
	public int getGrade() {
		return grade;
	}

}
