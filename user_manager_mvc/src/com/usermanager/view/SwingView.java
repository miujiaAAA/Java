package com.usermanager.view;

import javax.swing.JFrame;
import javax.swing.JLabel;

import com.usermanager.controller.UserController;

public class SwingView {
	public void index(UserController controller) {
		JFrame.setDefaultLookAndFeelDecorated(true);
		// 创建及设置窗口
		JFrame frame = new JFrame("用户管理系统");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JLabel label = new JLabel("请输入指令:");
		frame.getContentPane().add(label);
		// 显示窗口
		frame.setVisible(true);
		frame.setBounds(100, 100, 400, 300);
	}
	
	public void showResult(String result) {
		JFrame.setDefaultLookAndFeelDecorated(true);
		// 创建及设置窗口
		JFrame frame = new JFrame("用户管理系统");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JLabel label = new JLabel("运行结果:"+result);
		frame.getContentPane().add(label);
		// 显示窗口
		frame.setVisible(true);
		frame.setBounds(100, 100, 400, 300);
	}
	
	public void showError(String error) {
		JFrame.setDefaultLookAndFeelDecorated(true);
		// 创建及设置窗口
		JFrame frame = new JFrame("用户管理系统");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JLabel label = new JLabel("错误:"+error);
		frame.getContentPane().add(label);
		// 显示窗口
		frame.setVisible(true);
		frame.setBounds(100, 100, 400, 300);
	}
	
}
