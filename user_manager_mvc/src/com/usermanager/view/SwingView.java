package com.usermanager.view;

import javax.swing.JFrame;
import javax.swing.JLabel;

import com.usermanager.controller.UserController;

public class SwingView {
	public void index(UserController controller) {
		JFrame.setDefaultLookAndFeelDecorated(true);
		// ���������ô���
		JFrame frame = new JFrame("�û�����ϵͳ");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JLabel label = new JLabel("������ָ��:");
		frame.getContentPane().add(label);
		// ��ʾ����
		frame.setVisible(true);
		frame.setBounds(100, 100, 400, 300);
	}
	
	public void showResult(String result) {
		JFrame.setDefaultLookAndFeelDecorated(true);
		// ���������ô���
		JFrame frame = new JFrame("�û�����ϵͳ");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JLabel label = new JLabel("���н��:"+result);
		frame.getContentPane().add(label);
		// ��ʾ����
		frame.setVisible(true);
		frame.setBounds(100, 100, 400, 300);
	}
	
	public void showError(String error) {
		JFrame.setDefaultLookAndFeelDecorated(true);
		// ���������ô���
		JFrame frame = new JFrame("�û�����ϵͳ");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JLabel label = new JLabel("����:"+error);
		frame.getContentPane().add(label);
		// ��ʾ����
		frame.setVisible(true);
		frame.setBounds(100, 100, 400, 300);
	}
	
}
