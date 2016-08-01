package com.spring.model;

public class Teacher {
	private int tid;
	private String tno;
	private String tname;
	private String tpassword;

	public Teacher() {
		super();
	}

	public int getTid() {
		return tid;
	}

	public void setTid(int tid) {
		this.tid = tid;
	}

	public String getTno() {
		return tno;
	}

	public void setTno(String tno) {
		this.tno = tno == null ? null : tno.trim();
	}

	public String getTname() {
		return tname;
	}

	public void setTname(String tname) {
		this.tname = tname == null ? null : tname.trim();
	}

	public String getTpassword() {
		return tpassword;
	}

	public void setTpassword(String tpassword) {
		this.tpassword = tpassword == null ? null : tpassword.trim();
	}

}
