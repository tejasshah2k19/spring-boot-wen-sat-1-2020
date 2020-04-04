package com.com.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.com.bean.ResponseBean;
import com.com.bean.UserBean;
import com.com.dao.UserDao;

//this is comment

@RestController
public class UserController {
	// inject dao for usercontroller
	@Autowired
	UserDao userDao;

	@PostMapping("/user")
	public ResponseBean<UserBean> addUser(UserBean userBean, @Value("${x}") String x) {
		System.out.println(userBean.getFirstName());
		System.out.println(userBean.getLastName());
		System.out.println(userBean.getEmail());
		System.out.println(userBean.getPassword());
		// here we need to call add user from dao
		userDao.insertUser(userBean);
		ResponseBean rb = new ResponseBean();
		rb.setCode(200);
		rb.setMessage("User Added");
		rb.setData(userBean);
		System.out.println("x ==>  " + x);
		return rb;

	}

	@GetMapping("/users")
	public ArrayList<UserBean> listUsers() {
		return userDao.listUsers();
	}

	@GetMapping("/user/{email}")
	public UserBean getUser(@PathVariable("email") String email) {
		UserBean userBean = userDao.getUserByEmail(email);
		return userBean;// so we get either user or null value in response
		// later we learn how to send error instead of null
	}

	@DeleteMapping("user/{email}")
	public ArrayList<UserBean> deleteUser(@PathVariable("email") String email) {
		// will remove user from array list
		userDao.deleteUser(email);
		// after removal of user we sent updated list to the client
		return userDao.listUsers();
	}

}
