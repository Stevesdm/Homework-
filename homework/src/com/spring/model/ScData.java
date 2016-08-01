package com.spring.model;

public class ScData {
	private int id;
	private String cno;
	private String smajor;
	private String sgrade;
	private String sclass;

	public ScData(String cno, String smajor,String sgrade, String sclass) {
		super();
		this.cno = cno;
		this.smajor = smajor;
		this.sgrade=sgrade;
		this.sclass = sclass;
	}

	public ScData() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCno() {
		return cno;
	}

	public void setCno(String cno) {
		this.cno = cno;
	}

	public String getSmajor() {
		return smajor;
	}

	public void setSmajor(String smajor) {
		this.smajor = smajor;
	}

	public String getSgrade() {
		return sgrade;
	}

	public void setSgrade(String sgrade) {
		this.sgrade = sgrade;
	}

	public String getSclass() {
		return sclass;
	}

	public void setSclass(String sclass) {
		this.sclass = sclass;
	}

	@Override
	public String toString() {
		return "ScData [id=" + id + ", cno=" + cno + ", smajor=" + smajor
				+ ", sgrade=" + sgrade + ", sclass=" + sclass + "]";
	}

}
