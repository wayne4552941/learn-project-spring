package bean;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name="examination")
public class ExamBean implements Serializable
{		

	@Id
	@Column(name = "examid")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer examID;
	
	@ManyToOne
	@JoinColumn(name="subjectname")
    private SubBean subject;
	
	@ManyToOne
	@JoinColumn(name="educationlevel")
    private EduBean education;
	
	@Column(name = "examname")
    private String examName;

	@Column(name = "examdate")	
    private Date examdate;
	
	
	@Override
	public String toString() {
		
		return " subject=" + subject + ", education=" + education + ", examName="
				+ examName + ", examdate=" + examdate;
	}
	
	
	public ExamBean(){
    }

	
	
	public ExamBean(SubBean subject, EduBean education, String examName, Date examdate) {
		super();
		this.subject = subject;
		this.education = education;
		this.examName = examName;
		this.examdate = examdate;
	}


	public ExamBean(int examID, SubBean subject, EduBean education, String examName, Date examdate) {
		super();
		this.examID = examID;
		this.subject = subject;
		this.education = education;
		this.examName = examName;
		this.examdate = examdate;
	}


	public int getExamID() {
		return examID;
	}


	public void setExamID(int examID) {
		this.examID = examID;
	}


	public SubBean getSubject() {
		return subject;
	}


	public void setSubject(SubBean subject) {
		this.subject = subject;
	}


	public EduBean getEducation() {
		return education;
	}


	public void setEducation(EduBean education) {
		this.education = education;
	}


	public String getExamName() {
		return examName;
	}


	public void setExamName(String examName) {
		this.examName = examName;
	}


	public Date getExamdate() {
		return examdate;
	}


	public void setExamdate(Date examdate) {
		this.examdate = examdate;
	}
	
	
	

  

}