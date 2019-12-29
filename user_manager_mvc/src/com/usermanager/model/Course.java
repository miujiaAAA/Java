package com.usermanager.model;

import java.io.Serializable;

public class Course implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer courseid;
	private String coursename;

	public Course(Integer courseid, String coursename) {
		this.courseid = courseid;
		this.coursename = coursename;

	}

	public String getCourseName() {
		return coursename;
	}

	public void setCourseName(String coursename) {
		this.coursename = coursename;
	}

	public Integer getCourseId() {
		return courseid;
	}

	public void setCourseId(Integer courseid) {
		this.courseid = courseid;
	}

	@Override
	public String toString() {
		String str = "courseid:" + courseid + ",coursename:" + coursename;
		return str;
	}
}