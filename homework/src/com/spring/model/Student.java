package com.spring.model;

public class Student {

	private int sid;
	private String sno;
	private String spassword;
	private String sname;
	private String smajor;
	private String sgrade;
	private String sclass;

	public Student() {
		super();
	}

	public Student(String sno, String spassword, String sname, String smajor,
			String sgrade, String sclass) {
		super();
		this.sno = sno;
		this.spassword = spassword;
		this.sname = sname;
		this.smajor = smajor;
		this.sgrade = sgrade;
		this.sclass = sclass;
	}

	public Student(String sno, String sname, String smajor, String sgrade,
			String sclass) {
		super();
		this.sno = sno;
		this.sname = sname;
		this.smajor = smajor;
		this.sgrade = sgrade;
		this.sclass = sclass;
	}

	public int getSid() {
		return sid;
	}

	public void setSid(int sid) {
		this.sid = sid;
	}

	public String getSno() {
		return sno;
	}

	public void setSno(String sno) {
		this.sno = sno == null ? null : sno.trim();
	}

	public String getSpassword() {
		return spassword;
	}

	public void setSpassword(String spassword) {
		this.spassword = spassword == null ? null : spassword.trim();
	}

	public String getSname() {
		return sname;
	}

	public void setSname(String sname) {
		this.sname = sname == null ? null : sname.trim();
	}

	public String getSmajor() {
		return smajor;
	}

	public void setSmajor(String smajor) {
		this.smajor = smajor == null ? null : smajor.trim();
	}

	public String getSgrade() {
		return sgrade;
	}

	public void setSgrade(String sgrade) {
		this.sgrade = sgrade == null ? null : sgrade.trim();
	}

	public String getSclass() {
		return sclass;
	}

	public void setSclass(String sclass) {
		this.sclass = sclass == null ? null : sclass.trim();
	}

	@Override
	public String toString() {
		return "Student [sid=" + sid + ", sno=" + sno + ", spassword="
				+ spassword + ", sname=" + sname + ", smajor=" + smajor
				+ ", sgrade=" + sgrade + ", sclass=" + sclass + "]";
	}

}
