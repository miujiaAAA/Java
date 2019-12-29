package com.usermanager.service;

import java.util.List;

import com.usermanager.model.Course;
import com.usermanager.model.Grade;
import com.usermanager.model.User;

public interface UserManager {
	public User getUserById(Integer id);
	public boolean addUser(User user);
	public void deleteUser(Integer id);
	public void updateUser(Integer id);
	public List<User> findUserByName(String name);
	public String userListToString(List<User> userList);
	public String toString();
	public void saveUserList();
	
	public Course getCourseById(Integer courseid);
	public boolean addCourse(Course course);
	public void deleteCourse(Integer courseid);
	public List<Course> findCourseByCourseName(String coursename);
	public String courseListToString(List<Course> courseList);
	public String toStringCourse();
	public void saveCourseList();
	public Grade getGradeById(Integer id);

	
	public boolean addGrade(Grade grade);
	public void deleteGrade(Integer id);
	public float avreageGrade(String coursename);
	public int maxGrade(String coursename);
	public int minGrade(String coursename);

	public List<Grade> findGradeByName(String name);
	public String gradeListToString(List<Grade> gradeList);
	public String toStringGrade();
	public void saveGradeList();
	public void sort();
}
