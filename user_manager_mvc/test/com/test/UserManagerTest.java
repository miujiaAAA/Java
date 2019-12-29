package com.test;

import com.usermanager.controller.UserController;
import com.usermanager.service.UserManager;
//import com.usermanager.service.impl.MemoryUserManager;
//import com.usermanager.service.impl.DataFileUserManager;
import com.usermanager.service.impl.TxtFileUserManager;

public class UserManagerTest {
	public static void main(String[] args) {

		UserManager service = new TxtFileUserManager();
		UserController controller = new UserController(service);
		controller.start();

	}
}
