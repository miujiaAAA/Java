package com.usermanager.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

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

public class DataFileUserManager implements UserManager {

	private static final File DATA_FILE = new File("E:/userman/data.obj");
	private static final File DATA_FILE1 = new File("E:/userman/data1.obj");
	private static final File DATA_FILE2 = new File("E:/userman/data2.obj");

	private List<User> userList;
	private List<Grade> gradeList;
	private List<Course> courseList;

	public DataFileUserManager() {
		try {
			loadUserList();
			loadGradeList();
			loadCourseList();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	private void loadUserList() throws IOException, ClassNotFoundException {
		File fileFolder = DATA_FILE.getParentFile();
		if (!fileFolder.exists()) {
			fileFolder.mkdirs();
		}
		if (!DATA_FILE.exists()) {
			DATA_FILE.createNewFile();
			userList = new ArrayList<User>();
			return;
		}
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(DATA_FILE))) {
			userList = (List<User>) ois.readObject();
			if (userList == null) {
				userList = new ArrayList<User>();
			}
		} catch (IOException e) {
			userList = new ArrayList<User>();
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	private void loadGradeList() throws IOException, ClassNotFoundException {
		File filefolder = DATA_FILE1.getParentFile();
		if (!filefolder.exists()) {
			filefolder.mkdirs();
		}
		if (!DATA_FILE1.exists()) {
			DATA_FILE1.createNewFile();
			gradeList = new ArrayList<Grade>();
			return;
		}
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(DATA_FILE1))) {
			gradeList = (List<Grade>) ois.readObject();
			if (gradeList == null) {
				gradeList = new ArrayList<Grade>();
			}
		} catch (IOException e) {
			gradeList = new ArrayList<Grade>();
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	private void loadCourseList() throws IOException, ClassNotFoundException {
		File filefolder = DATA_FILE2.getParentFile();
		if (!filefolder.exists()) {
			filefolder.mkdirs();
		}
		if (!DATA_FILE2.exists()) {
			DATA_FILE2.createNewFile();
			courseList = new ArrayList<Course>();
			return;
		}
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(DATA_FILE2))) {
			courseList = (List<Course>) ois.readObject();
			if (courseList == null) {
				courseList = new ArrayList<Course>();
			}

		} catch (IOException e) {
			courseList = new ArrayList<Course>();
			e.printStackTrace();
		}
	}

	public void saveUserList() {
		try (ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(DATA_FILE))) {
			os.writeObject(userList);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

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
	public List<User> findUserByName(String name) {
		List<User> result = userList.stream().filter(user -> user.getName().contains(name))
				.collect(Collectors.toList());
		return result;

	}

	@Override
	public User getUserById(Integer id) {
		User result = userList.stream().filter(user -> user.getId().equals(id)).findFirst().orElse(null);
		return result;
	}

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
	public void saveGradeList() {
		try (ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(DATA_FILE1))) {
			os.writeObject(gradeList);
		} catch (IOException e) {
			e.printStackTrace();
		}

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



	@Override
	public List<Grade> findGradeByName(String name) {
		List<Grade> result = gradeList.stream().filter(grade -> grade.getName().contains(name))
				.collect(Collectors.toList());
		return result;
	}

	@Override
	public Grade getGradeById(Integer id) {
		Grade result = gradeList.stream().filter(grade -> grade.getId().equals(id)).findFirst().orElse(null);
		return result;
	}

	@Override
	public String gradeListToString(List<Grade> gradeList) {
		StringBuffer gradeBuffer = new StringBuffer();
		gradeList.forEach(grade -> gradeBuffer.append(grade + "\n"));
		return gradeBuffer.toString();
	}

	@Override
	public String toStringGrade() {
		return gradeListToString(gradeList);
	}

	@Override
	public Course getCourseById(Integer courseid) {
		Course result = courseList.stream().filter(course -> course.getCourseId().equals(courseid)).findFirst()
				.orElse(null);
		return result;
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
	public void deleteCourse(Integer courseid) {
		Iterator<Course> courseIt = courseList.iterator();
		while (courseIt.hasNext()) {
			Course course = courseIt.next();
			if (course.getCourseId().equals(courseid)) {
				courseIt.remove();
			}
		}

	}

	@Override
	public List<Course> findCourseByCourseName(String coursename) {
		// TODO Auto-generated method stub
		return null;
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

	@Override
	public void saveCourseList() {
		try (ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(DATA_FILE2))) {
			os.writeObject(courseList);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

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
		// TODO Auto-generated method stub
		
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
		System.out.println("ÐÕÃû:"+grade1.getName()+","+"³É¼¨:"+grade1.getScore());
	}
	}
}
