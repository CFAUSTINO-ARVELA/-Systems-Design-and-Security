package university;

import java.sql.*;

public class StudentStatus {
	
	private int registrationNumber;
	private int level;
	private int period;
	private Date startDate;
	private Date endDate;
	
	public int getRegistrationNumber() {
		return registrationNumber;
	}
	public int getLevel() {
		return level;
	}
	public int getPeriod() {
		return period;
	}
	public Date getStartDate() {
		return startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	
}
