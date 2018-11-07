
class Module {
	
	private String name;
	private String code;
	private int credits;
	private String duration;
	
	Module(String n, String c, int cr, String d) {
		this.name = n;
		this.code = c;
		this.credits = cr;
		this.duration = d;
	}
	
	public void addModule() {
		// Add module to database
	}
	
	public void deleteModule() {
		// Delete module from database
	}
	
	public boolean checkApproval(String d, String l) {
		return true; // Write an actual function here
	}

}
