package com.dbs.team3.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Receiver {
	@Id
	String bic;
	String bank;
	public String getBic() {
		return bic;
	}
	public void setBic(String bic) {
		this.bic = bic;
	}
	public String getBank() {
		return bank;
	}
	public void setBank(String bank) {
		this.bank = bank;
	}
	@Override
	public String toString() {
		return "Receiver [bic=" + bic + ", bank=" + bank + "]";
	}
	public Receiver(String bic, String bank) {
		super();
		this.bic = bic;
		this.bank = bank;
	}
	public Receiver() {
		super();
		// TODO Auto-generated constructor stub
	}
}
