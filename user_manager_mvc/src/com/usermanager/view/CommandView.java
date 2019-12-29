package com.usermanager.view;

import java.util.Scanner;

import com.usermanager.controller.UserController;

public class CommandView {
	public void index(UserController controller) {
		System.out.println("欢迎使用用户管理系统，请输入指令和参数：");
		Scanner scanner = new Scanner(System.in);
		String command = scanner.nextLine();
		while (!"quit".equals(command)) {
			controller.execCommand(command);
			command = scanner.nextLine();
		}
		System.out.println("系统正常退出");
		scanner.close();
	}
	
	public void showResult(String result) {
		System.out.println(result);
	}
	
	public void showError(String error) {
		System.err.println(error);
	}

	public void showResult(float avreageGrade) {
		System.out.println(avreageGrade);
	}
	public void showResult(int maxGrade) {
		System.out.println(maxGrade);
	}
}
