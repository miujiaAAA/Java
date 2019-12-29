package com.usermanager.model;

import java.io.Serializable;

public class Grade implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer id;
	private String name;
	private Integer score;
	private String coursename;

	public Grade(Integer id, String name, String coursename, Integer score) {
		this.id = id;
		this.name = name;
		this.coursename = coursename;
		this.score = score;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	public String getCourseName() {
		return coursename;
	}

	public void setCourseName(String coursename) {
		this.coursename = coursename;
	}

	@Override
	public String toString() {
		String str = "name:" + name + ",coursename:" + coursename + ",grade:" + score;
		return str;
	}
}
