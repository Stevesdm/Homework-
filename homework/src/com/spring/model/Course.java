package com.spring.model;

public class Course {
	private int cid;
	private String cno;
	private String cname;
	private int ctimes;
	private String cimage;
	private String cmajor;
	private String cgrade;
	private String tno;

	public Course(String cno, String cname, String cimage, String tno) {
		super();
		this.cno = cno;
		this.cname = cname;
		this.cimage = cimage;
		this.tno = tno;
	}
	

	public Course(String cno, String cmajor, String cgrade) {
		super();
		this.cno = cno;
		this.cmajor = cmajor;
		this.cgrade = cgrade;
	}



	public Course() {
		super();
	}

	public int getCid() {
		return cid;
	}

	public void setCid(int cid) {
		this.cid = cid;
	}

	public String getCno() {
		return cno;
	}

	public void setCno(String cno) {
		this.cno = cno == null ? null : cno.trim();
	}

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname == null ? null : cname.trim();
	}

	public int getCtimes() {
		return ctimes;
	}

	public void setCtimes(int ctimes) {
		this.ctimes = ctimes;
	}

	public String getCimage() {
		return cimage;
	}

	public void setCimage(String cimage) {
		this.cimage = cimage;
	}

	public String getCmajor() {
		return cmajor;
	}

	public void setCmajor(String cmajor) {
		this.cmajor = cmajor;
	}

	public String getCgrade() {
		return cgrade;
	}

	public void setCgrade(String cgrade) {
		this.cgrade = cgrade;
	}

	public String getTno() {
		return tno;
	}

	public void setTno(String tno) {
		this.tno = tno == null ? null : tno.trim();
	}

}
