package com.usermanager.service.impl;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import com.usermanager.model.Course;
import com.usermanager.model.Grade;
import com.usermanager.model.User;
import com.usermanager.service.UserManager;

public class TxtFileUserManager implements UserManager {

	private static final File TXT_FILE = new File("E:/userman/data.txt");
	private static final File TXT_FILE1 = new File("E:/userman/data1.txt");
	private static final File TXT_FILE2 = new File("E:/userman/data2.txt");

	private List<User> userList;
	private List<Grade> gradeList;
	private List<Course> courseList;

	public TxtFileUserManager() {
		userList = new ArrayList<User>();
		gradeList = new ArrayList<Grade>();
		courseList = new ArrayList<Course>();
		try {
			loadUserList();
			loadGradeList();
			loadCourseList();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void loadUserList() throws IOException {
		File fileFolder = TXT_FILE.getParentFile();
		if (!fileFolder.exists()) {
			fileFolder.mkdirs();
		}
		if (!TXT_FILE.exists()) {
			TXT_FILE.createNewFile();

		}
		try (BufferedReader bufferedReader = new BufferedReader(
				new InputStreamReader(new FileInputStream(TXT_FILE), "utf-8"))) {
			String line = bufferedReader.readLine();
			while (line != null) {
				String[] values = line.split(",");
				if (values.length == 4) {
					Integer id = Integer.parseInt(values[0]);
					String name = values[1];
					Integer sex = Integer.parseInt(values[2]);
					Date birthday = new SimpleDateFormat("yyyy-MM-dd").parse(values[3]);
					User user = new User(id, name, sex, birthday);
					userList.add(user);
				}

				line = bufferedReader.readLine();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void loadGradeList() throws IOException {
		File fileFolder = TXT_FILE1.getParentFile();
		if (!fileFolder.exists()) {
			fileFolder.mkdirs();
		}
		if (!TXT_FILE1.exists()) {
			TXT_FILE1.createNewFile();

		}
		try (BufferedReader bufferedReader = new BufferedReader(
				new InputStreamReader(new FileInputStream(TXT_FILE1), "utf-8"))) {
			String line = bufferedReader.readLine();
			while (line != null) {
				String[] values = line.split(",");
				if (values.length == 4) {
					Integer id = Integer.parseInt(values[0]);
					String name = values[1];
					String coursename = values[2];
					Integer score = Integer.parseInt(values[3]);
					Grade grade = new Grade(id, name, coursename, score);
					gradeList.add(grade);
				}
				line = bufferedReader.readLine();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void loadCourseList() throws IOException {
		File fileFolder = TXT_FILE2.getParentFile();
		if (!fileFolder.exists()) {
			fileFolder.mkdirs();
		}
		if (!TXT_FILE2.exists()) {
			TXT_FILE2.createNewFile();

		}
		try (BufferedReader bufferedReader = new BufferedReader(
				new InputStreamReader(new FileInputStream(TXT_FILE2), "utf-8"))) {
			String line = bufferedReader.readLine();
			while (line != null) {
				String[] values = line.split(",");
				if (values.length == 2) {
					Integer courseid = Integer.parseInt(values[0]);
					String coursename = values[1];
					Course course = new Course(courseid, coursename);
					courseList.add(course);
				}
				line = bufferedReader.readLine();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void saveUserList() {
		try (BufferedWriter bufferedWriter = new BufferedWriter(
				new OutputStreamWriter(new FileOutputStream(TXT_FILE), "utf-8"))) {
			for (User user : userList) {
				bufferedWriter.write(user.getId() + "," + user.getName() + "," + user.getSex() + ","
						+ new SimpleDateFormat("yyyy-MM-dd").format(user.getBirthday()) + "\n");
			}
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
	public boolean addCourse(Course course) {
		User exist = getUserById(course.getCourseId());
		if (exist == null) {
			courseList.add(course);
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
		try (BufferedWriter bufferedWriter = new BufferedWriter(
				new OutputStreamWriter(new FileOutputStream(TXT_FILE1), "utf-8"))) {
			for (Grade grade : gradeList) {
				bufferedWriter.write(grade.getId() + "," + grade.getName() + "," + grade.getCourseName() + ","
						+ grade.getScore() + "\n");
			}
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
	public Grade getGradeById(Integer id) {
		Grade result = gradeList.stream().filter(grade -> grade.getId().equals(id)).findFirst().orElse(null);
		return result;
	}

	@Override
	public List<Grade> findGradeByName(String name) {
		List<Grade> result = gradeList.stream().filter(grade -> grade.getName().contains(name))
				.collect(Collectors.toList());
		return result;
	}

	@Override
	public String toStringGrade() {
		return gradeListToString(gradeList);
	}

	@Override
	public String gradeListToString(List<Grade> gradeList) {
		StringBuffer gradeBuffer = new StringBuffer();
		gradeList.forEach(grade -> gradeBuffer.append(grade + "\n"));
		return gradeBuffer.toString();
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
	public void saveCourseList() {
		try (BufferedWriter bufferedWriter = new BufferedWriter(
				new OutputStreamWriter(new FileOutputStream(TXT_FILE2), "utf-8"))) {
			for (Course course : courseList) {
				bufferedWriter.write(course.getCourseId() + "," + course.getCourseName() + "\n");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

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
	public List<Course> findCourseByCourseName(String coursename) {
		// TODO Auto-generated method stub
		return null;
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
