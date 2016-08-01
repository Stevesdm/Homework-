package com.spring.model;

import java.util.Date;

public class Task {
	private int task_id;
	private String cno;
	private int sctimes;
	private Date stoptime;
	private String task_title;
	private String task_content;
	private String task_file;

	public Task() {
		super();
	}

	public String getCno() {
		return cno;
	}

	public void setCno(String cno) {
		this.cno = cno;
	}

	public int getTask_id() {
		return task_id;
	}

	public void setTask_id(int task_id) {
		this.task_id = task_id;
	}

	public int getSctimes() {
		return sctimes;
	}

	public void setSctimes(int sctimes) {
		this.sctimes = sctimes;
	}

	public Date getStoptime() {
		return stoptime;
	}

	public void setStoptime(Date stoptime) {
		this.stoptime = stoptime;
	}

	public String getTask_title() {
		return task_title;
	}

	public void setTask_title(String task_title) {
		this.task_title = task_title;
	}

	public String getTask_content() {
		return task_content;
	}

	public void setTask_content(String task_content) {
		this.task_content = task_content;
	}

	public String getTask_file() {
		return task_file;
	}

	public void setTask_file(String task_file) {
		this.task_file = task_file;
	}

	@Override
	public String toString() {
		return "Task [task_id=" + task_id + ", cno=" + cno + ", sctimes="
				+ sctimes + ", stoptime=" + stoptime + ", task_title="
				+ task_title + ", task_content=" + task_content
				+ ", task_file=" + task_file + "]";
	}

}
