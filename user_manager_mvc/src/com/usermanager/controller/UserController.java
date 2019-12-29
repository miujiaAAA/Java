package com.usermanager.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.usermanager.model.Course;
import com.usermanager.model.Grade;
import com.usermanager.model.User;
import com.usermanager.service.UserManager;
import com.usermanager.view.CommandView;

public class UserController {
	private UserManager service;

	public UserController(UserManager service) {
		this.service = service;
	}

	public void setService(UserManager service) {
		this.service = service;
	}

	public void start() {
		CommandView view = new CommandView();
		view.index(this);
//		SwingView view = new SwingView();
//		view.index(this);
	}

	public void execCommand(String command) {
		CommandView view = new CommandView();
		try {
			String[] params = command.split(",");
			String methodName = params[0];
			switch (methodName) {
			case "addUser": {
				Integer id = Integer.parseInt(params[1]);
				String name = params[2];
				Integer sex = Integer.parseInt(params[3]);
				Date birthday = new SimpleDateFormat("yyyy-MM-dd").parse(params[4]);
				User user = new User(id, name, sex, birthday);
				boolean result = service.addUser(user);
				String resultStr = "����û�" + (result ? "�ɹ�" : "ʧ��");
				view.showResult(resultStr);
				break;
			}
			case "addCourse": {
				Integer courseid = Integer.parseInt(params[1]);
				String coursename = params[2];
				Course course = new Course(courseid, coursename);
				boolean result = service.addCourse(course);
				String resultStr = "����û�" + (result ? "�ɹ�" : "ʧ��");
				view.showResult(resultStr);
				break;
			}
			case "addGrade": {
				Integer id = Integer.parseInt(params[1]);
				String name = params[2];
				String coursename = params[3];
				Integer score = Integer.parseInt(params[4]);

				Grade grade = new Grade(id, name, coursename, score);
				boolean result = service.addGrade(grade);
				String resultStr = "����û�" + (result ? "�ɹ�" : "ʧ��");
				view.showResult(resultStr);
				break;

			}
			case "deleteUser": {
				Integer id = Integer.parseInt(params[1]);
				service.deleteUser(id);
				view.showResult("ɾ���û��ɹ�");
				break;
			}
			case "deleteCourse": {
				Integer courseid = Integer.parseInt(params[1]);
				service.deleteCourse(courseid);
				view.showResult("ɾ���γ̳ɹ�");
				break;
			}
			case "deleteGrade": {
				Integer id = Integer.parseInt(params[1]);
				service.deleteGrade(id);
				view.showResult("ɾ���ɼ��ɹ�");
				break;
			}
			case "getUserById": {
				Integer id = Integer.parseInt(params[1]);
				User user = service.getUserById(id);
				if (user == null) {
					view.showError("û��ָ�����û�");
					break;
				}
				view.showResult(user.toString());
				break;
			}
			case "getGradeById": {
				Integer id = Integer.parseInt(params[1]);
				Grade grade = service.getGradeById(id);
				if (grade == null) {
					view.showError("û��ָ�����û�");
					break;
				}
				view.showResult(grade.toString());
				break;
			}
			case "getCourseById": {
				Integer courseid = Integer.parseInt(params[1]);
				Course course = service.getCourseById(courseid);
				if (course == null) {
					view.showError("��");
					break;
				}
				view.showResult(course.toString());
				break;
			}
			case "findUserByName": {
				String name = params[1];
				List<User> result = service.findUserByName(name);
				view.showResult(service.userListToString(result));
				break;
			}
			case "findGradeByName": {
				String name = params[1];
				List<Grade> result = service.findGradeByName(name);
				view.showResult(service.gradeListToString(result));
				break;
			}
			case "avreageGrade": {
				String coursename = params[1];
				service.avreageGrade(coursename);
				view.showResult(service.avreageGrade(coursename));
				break;
			}
			case "maxGrade": {
				String coursename = params[1];
				service.avreageGrade(coursename);
				view.showResult(service.maxGrade(coursename));
				break;
			}
			case "minGrade": {
				String coursename = params[1];
				service.avreageGrade(coursename);
				view.showResult(service.minGrade(coursename));
				break;
			}
			case"sort":{
				service.sort();
				break;
			}
			case "saveUserList": {
				service.saveUserList();
				view.showResult("�û������ѱ���");
				break;
			}
			case "saveGradeList": {
				service.saveGradeList();
				view.showResult("�ɼ��ѱ���");
				break;
			}
			case "saveCourseList": {
				service.saveCourseList();
				view.showResult("�γ��ѱ���");
				break;
			}
			default:
				view.showError("�����ָ��");
			}
		} catch (Exception e) {
			view.showError("�����ָ��Ͳ���");
			e.printStackTrace();
		}

	}
}
