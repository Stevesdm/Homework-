package com.spring.model;

public class Admin {
	private int aid;
	private String ano;
	private String aname;
	private String apassword;

	public Admin() {
		super();
	}

	public int getAid() {
		return aid;
	}

	public void setAid(int aid) {
		this.aid = aid;
	}

	public String getAno() {
		return ano;
	}

	public void setAno(String ano) {
		this.ano = ano == null ? null : ano.trim();
	}

	public String getAname() {
		return aname;
	}

	public void setAname(String aname) {
		this.aname = aname == null ? null : aname.trim();
	}

	public String getApassword() {
		return apassword;
	}

	public void setApassword(String apassword) {
		this.apassword = apassword == null ? null : apassword.trim();
	}

}
