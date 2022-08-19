package bean;

import java.io.Serializable;

public class Voucher implements Serializable{
	
	private int id;
	private String number;
	private double discount;
	private int life;
	
	public Voucher() {}

	public Voucher(int id, String number, double discount, int life) {
		this.id = id;
		this.number = number;
		this.discount = discount;
		this.life = life;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public double getDiscount() {
		return discount;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}

	public int getLife() {
		return life;
	}

	public void setLife(int life) {
		this.life = life;
	}

	@Override
	public String toString() {
		return "Voucher [id=" + id + ", number=" + number + ", discount=" + discount + ", life=" + life + "]";
	}
	
	
}
