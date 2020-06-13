package com.infogain.demo.repository;

import java.util.HashMap;
import java.util.Random;

import org.springframework.stereotype.Repository;

import com.infogain.demo.model.User;

@Repository
public class UserRepository {
	HashMap<Integer, User> userDb;
	Random userIdGenerator;

	public UserRepository() {
		userDb = new HashMap<>();
		userIdGenerator = new Random();
	}

	public User getUserByUserId(int userId) {
		return userDb.get(userId);
	}

	public void createUser(User user) {
		int userId = Math.abs(userIdGenerator.nextInt());
		user.setUserId(userId);
		userDb.put(userId, user);
		System.out.println("********** user created" + user);

	}

	public void deleteUser(int userId) {
		userDb.remove(userId);

	}

	public boolean updateUser(User user) {
		User userFromDb = getUserByUserId(user.getUserId());
		if (userFromDb != null) {
			userDb.put(user.getUserId(), user);
			System.out.println("********** user updated" + user);
			return true;
		}
		return false;
	}

}
