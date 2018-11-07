
class Student {

    private String degree; // needs to be converted to type Degree when created
    private int registrationNumber;
    private String tutor; // needs to be type Teacher
    private int level;
    private int period;

    Student(String d, int r, String t) {
      this.degree = d;
      this.registrationNumber = r;
      this.tutor = t;
    }

    public String getDegree() {
        return this.degree;
    }
    
    public int getRegistrationNumber() {
    	return this.registrationNumber;
    }
    
    public String getTutor() {
    	return this.tutor;
    }
    
    public int getLevel() {
    	return this.level;
    }
    
    public int getPeriod() {
    	return this.period;
    }
    
    
}