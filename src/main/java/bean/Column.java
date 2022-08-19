package bean;

import java.io.Serializable;

public class Column implements Serializable {
	private int no;
	private String date;
	private int user_id;
	private String author;
	private String role;
	private String contents;
	
	public Column() {
		
	}

	public Column(int no, String date, int user_id, String author, String role, String contents) {
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
	
	
}
