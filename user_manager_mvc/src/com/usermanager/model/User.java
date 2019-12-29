package com.usermanager.model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;
	private String name;
	private Integer sex;
	private Date birthday;

	public User(Integer id, String name, Integer sex, Date birthday) {
		this.id = id;
		this.name = name;
		this.sex = sex;
		this.birthday = birthday;
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

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	@Override
	public String toString() {
		String str = "id: " + id + ", name: " + name + ", sex: " + (sex == 1 ? "ÄÐ" : "Å®") + ", birthday: "
				+ (new SimpleDateFormat("yyyy-MM-dd").format(birthday));
		return str;
	}
}
