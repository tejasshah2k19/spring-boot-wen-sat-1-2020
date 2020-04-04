package com.com.dao;

import java.util.ArrayList;

import org.springframework.stereotype.Repository;

import com.com.bean.UserBean;

@Repository
public class UserDao {

	
	
	
	
	
	// contains all user data
	ArrayList<UserBean> users = new ArrayList<>();

	// will add user in array list
	public void insertUser(UserBean userBean) {
		users.add(userBean);
	}

	// will return entire list of user
	public ArrayList<UserBean> listUsers() {
		return users;
	}

	public UserBean getUserByEmail(String email) {
		// search from entier list one by one
		for (UserBean u : users) {
			// match given email from user object
			if (u.getEmail().equals(email)) {
				return u;// if match then return user
			}
		}

		return null; // if not match then last we send null
	}

	public void deleteUser(String email) {
		for (UserBean u : users) {
			if (u.getEmail().equals(email)) {
				users.remove(u);
				break;
			}

		}

	}
}
