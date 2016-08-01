package com.spring.model;

import java.util.Date;

public class Sc {
	private int sc_id;
	private String sno;
	private String sname;
	private String cno;
	private int sctimes;
	private String scfilename;
	private Date uploadtime;
	private int allowlook;
	private int downloaded;

	public Sc() {
		super();
	}

	public int getSc_id() {
		return sc_id;
	}

	public void setSc_id(int sc_id) {
		this.sc_id = sc_id;
	}

	public String getSno() {
		return sno;
	}

	public void setSno(String sno) {
		this.sno = sno;
	}

	public String getSname() {
		return sname;
	}

	public void setSname(String sname) {
		this.sname = sname;
	}

	public String getCno() {
		return cno;
	}

	public void setCno(String cno) {
		this.cno = cno;
	}

	public int getSctimes() {
		return sctimes;
	}

	public void setSctimes(int sctimes) {
		this.sctimes = sctimes;
	}

	public String getScfilename() {
		return scfilename;
	}

	public void setScfilename(String scfilename) {
		this.scfilename = scfilename;
	}

	public Date getUploadtime() {
		return uploadtime;
	}

	public void setUploadtime(Date uploadtime) {
		this.uploadtime = uploadtime;
	}

	public int getAllowlook() {
		return allowlook;
	}

	public void setAllowlook(int allowlook) {
		this.allowlook = allowlook;
	}

	public int getDownloaded() {
		return downloaded;
	}

	public void setDownloaded(int downloaded) {
		this.downloaded = downloaded;
	}

	public Sc(String sno, String sname, String cno, int sctimes,
			String scfilename, Date uploadtime) {
		super();
		this.sno = sno;
		this.sname = sname;
		this.cno = cno;
		this.sctimes = sctimes;
		this.scfilename = scfilename;
		this.uploadtime = uploadtime;
	}

	@Override
	public String toString() {
		return "Sc [sc_id=" + sc_id + ", sno=" + sno + ", sname=" + sname
				+ ", cno=" + cno + ", sctimes=" + sctimes + ", scfilename="
				+ scfilename + ", uploadtime=" + uploadtime + ", allowlook="
				+ allowlook + ", downloaded=" + downloaded + "]";
	}

}
