package bean;

import java.io.Serializable;
import java.util.*;

public class ExamBean implements Serializable
{	
	private int examID;
    private String subject;
    private String education;
    private String examName;
    private Date Date;



	public ExamBean()
    {
    }
	
    public ExamBean(int examID, String subject, String education,String examName, Date date) {
		super();
		this.examID = examID;
		this.subject = subject;
		this.education = education;
		this.examName = examName;
		this.Date = date;
	}
    
    
    
    public int getExamID() {
		return examID;
	}

	public void setExamID(int examID) {
		this.examID = examID;
	}

	public String getExamName() {
    	return examName;
    }
    
    public void setExamName(String examName) {
    	this.examName = examName;
    }
	
	public String getSubject() {
		return subject;
	}
	
	public void setSubject(String subject) {
		this.subject = subject;
	}
	
	public String getEducation() {
		return education;
	}
	
	public void setEducation(String education) {
		this.education = education;
	}
	
	
	public Date getDate() {
		return Date;
	}
	
	public void setDate(Date date) {
		Date = date;
	}
  

}