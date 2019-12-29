package com.usermanager.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import com.usermanager.model.Course;
import com.usermanager.model.Grade;
import com.usermanager.model.User;
import com.usermanager.service.UserManager;

public class MemoryUserManager implements UserManager {
	private List<User> userList;
	private List<Course> courseList;
	private List<Grade> gradeList;

	public MemoryUserManager() {
		userList = new ArrayList<User>();
		courseList = new ArrayList<Course>();
		gradeList = new ArrayList<Grade>();
	}

	// 信息查找
	@Override
	public User getUserById(Integer id) {
		User result = userList.stream().filter(user -> user.getId().equals(id)).findFirst().orElse(null);
		return result;
	}

	@Override
	public Course getCourseById(Integer courseid) {
		Course result = courseList.stream().filter(course -> course.getCourseId().equals(courseid)).findFirst()
				.orElse(null);
		return result;
	}

	@Override
	public Grade getGradeById(Integer id) {
		Grade result = gradeList.stream().filter(grade -> grade.getId().equals(id)).findFirst().orElse(null);
		return result;
	}

	@Override
	public List<User> findUserByName(String name) {
		List<User> result = userList.stream().filter(user -> user.getName().contains(name))
				.collect(Collectors.toList());
		return result;

	}

	@Override
	public List<Grade> findGradeByName(String name) {
		List<Grade> result = gradeList.stream().filter(grade -> grade.getName().contains(name))
				.collect(Collectors.toList());
		return result;
	}

	@Override
	public List<Course> findCourseByCourseName(String coursename) {
		// TODO Auto-generated method stub
		return null;
	}

	// 添加信息
	@Override
	public boolean addUser(User user) {
		User exist = getUserById(user.getId());
		if (exist == null) {
			userList.add(user);
			return true;
		}
		return false;
	}

	@Override
	public boolean addCourse(Course course) {
		Course exist = getCourseById(course.getCourseId());
		if (exist == null) {
			courseList.add(course);
			return true;
		}
		return false;
	}

	@Override
	public boolean addGrade(Grade grade) {
		Grade exist = getGradeById(grade.getId());
		if (exist == null) {
			gradeList.add(grade);
			return true;

		}
		return false;
	}

	// 删除信息
	@Override
	public void deleteUser(Integer id) {
		Iterator<User> userIt = userList.iterator();
		while (userIt.hasNext()) {
			User user = userIt.next();
			if (user.getId().equals(id)) {
				userIt.remove();
			}
		}
	}

	@Override
	public void deleteCourse(Integer courseid) {

	}

	@Override
	public void deleteGrade(Integer id) {
		Iterator<Grade> gradeIt = gradeList.iterator();
		while (gradeIt.hasNext()) {
			Grade grade = gradeIt.next();
			if (grade.getId().equals(id)) {
				gradeIt.remove();
			}
		}

	}

//
	@Override
	public String userListToString(List<User> userList) {
		StringBuffer userBuffer = new StringBuffer();
		userList.forEach(user -> userBuffer.append(user + "\n"));
		return userBuffer.toString();
	}

	@Override
	public String toString() {
		return userListToString(userList);
	}

	@Override
	public String courseListToString(List<Course> courseList) {
		StringBuffer courseBuffer = new StringBuffer();
		courseList.forEach(course -> courseBuffer.append(course + "\n"));
		return courseBuffer.toString();
	}

	@Override
	public String toStringCourse() {
		return courseListToString(courseList);
	}

	public String gradeListToString(List<Grade> gradeList) {
		StringBuffer gradeBuffer = new StringBuffer();
		gradeList.forEach(grade -> gradeBuffer.append(grade + "\n"));
		return gradeBuffer.toString();
	}

	@Override
	public String toStringGrade() {

		return gradeListToString(gradeList);
	}

	// 保存信息
	@Override
	public void saveUserList() {

	}

	@Override
	public void saveCourseList() {
		// TODO Auto-generated method stub

	}

	@Override
	public void saveGradeList() {
		// TODO Auto-generated method stub

	}

	// 修改信息

	// 数据操作
	@Override
	public float avreageGrade(String coursename) {
		float average = 0;
		int num = 0;
		int x = 0;
		for (Grade grade : gradeList) {

			if (grade.getCourseName().equals(coursename)) {
				num += grade.getScore();
				x++;
			}

		}
		average = num / x++;
		return average;

	}

	@Override
	public int maxGrade(String coursename) {
		int max = 0;

		for (Grade grade : gradeList) {
			if (grade.getCourseName().equals(coursename)) {
				if (max < grade.getScore()) {
					max = grade.getScore();

				}
			}
		}
		return max;
	}

	@Override
	public int minGrade(String coursename) {
		int min = 150;

		for (Grade grade : gradeList) {
			if (grade.getCourseName().equals(coursename)) {
				if (min > grade.getScore()) {
					min = grade.getScore();
				}
			}
		}
		return min;

	}

	@Override
	public void updateUser(Integer id) {
		// TODO Auto-generated method 

	}

	@Override
	public void sort() {
		Collections.sort(gradeList,new Comparator<Grade>()
	       {
			public int compare(Grade stu1, Grade stu2)
	            {
	 
	               return (int) (stu2.getScore()-stu1.getScore());
	 
	            }      
	 
	       }
	       );

		

for(Grade grade1 : gradeList) {
		System.out.println("姓名:"+grade1.getName()+","+"成绩:"+grade1.getScore());
	}
	}
}


