package university;

class Student {

    private String degree; // needs to be converted to type Degree when created
    private int registrationNumber;
    private String tutor; // needs to be type Teacher
    private int level;
    private int period;
    private Account accountDetails;

    Student(String deg, int reg, String tut, Account acc) {  
      this.degree = deg;
      this.registrationNumber = reg;
      this.tutor = tut;
      this.accountDetails = acc;
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