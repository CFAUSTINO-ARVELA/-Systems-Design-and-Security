package university;

public class ModuleGrades {

	private Module module;
	private int grade;
	private int resitGrade;
	private boolean resit;
	
	public Module getModule() {
		return module;
	}
	
	public int getGrade() {
		return grade;
	}
	
	public int getResitGrade() {
		return resitGrade;
	}
	
	public boolean getResit() {
		return resit;
	}
	
	public ModuleGrades(Module m, int g, int r) {
		this.module = m;
		this.grade = g;
		this.resitGrade = r;
		this.resit = true;
	}
	
	public ModuleGrades(Module m, int g) {
		this.module = m;
		this.grade = g;
		this.resit = false;
	}
	
}
