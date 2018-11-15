package university;

public enum Clearance {
	STUDENT, TEACHER, REGISTRAR, ADMIN;
	
	public int toInt() {
		switch (this) {
		case STUDENT: return 0;
		case TEACHER: return 1;
		case REGISTRAR: return 2;
		case ADMIN: return 3;
		default: return 0;
		}
	}
	
}


