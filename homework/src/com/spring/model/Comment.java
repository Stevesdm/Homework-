package com.spring.model;

import java.util.Date;

public class Comment {
	private int comment_id;
	private String sno;
	private int sctimes;
	private Date comment_time;
	private String comment_score;
	private String comment_content;

	public Comment() {
		super();
	}

	public int getComment_id() {
		return comment_id;
	}

	public void setComment_id(int comment_id) {
		this.comment_id = comment_id;
	}

	public String getSno() {
		return sno;
	}

	public void setSno(String sno) {
		this.sno = sno;
	}

	public int getSctimes() {
		return sctimes;
	}

	public void setSctimes(int sctimes) {
		this.sctimes = sctimes;
	}

	public Date getComment_time() {
		return comment_time;
	}

	public void setComment_time(Date comment_time) {
		this.comment_time = comment_time;
	}

	public String getComment_score() {
		return comment_score;
	}

	public void setComment_score(String comment_score) {
		this.comment_score = comment_score;
	}

	public String getComment_content() {
		return comment_content;
	}

	public void setComment_content(String comment_content) {
		this.comment_content = comment_content;
	}

}
