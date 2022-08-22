package bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name="article")
public class ColumnBean implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="article_no")
	private int no;
	@Column(name="publish_time")
	private String date;
	@Column(name="user_id")
	private int user_id;
	@Column(name="author")
	private String author;
	@Column(name="role")
	private String role;
	@Column(name="contents")
	private String contents;
	
	public ColumnBean() {
		
	}

	
	
	public ColumnBean(int no, String date, int user_id, String author, String role, String contents) {
		super();
		this.no = no;
		this.date = date;
		this.user_id = user_id;
		this.author = author;
		this.role = role;
		this.contents = contents;
	}



	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}



	@Override
	public String toString() {
		return "Column [no=" + no + ", date=" + date + ", user_id=" + user_id + ", author=" + author + ", role=" + role
				+ ", contents=" + contents + "]";
	}
	
	
}
