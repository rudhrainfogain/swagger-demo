package com.infogain.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.infogain.demo.model.Response;
import com.infogain.demo.model.User;
import com.infogain.demo.repository.UserRepository;

@Service
public class UserService {
	@Autowired
	UserRepository userRepository;

	public ResponseEntity<Response<User>> getUserById(int userId) {
		if (userId > 0) {
			User user = userRepository.getUserByUserId(userId);
			if (user != null) {
				return new ResponseEntity<Response<User>>(new Response<>(user), HttpStatus.OK);
			} else {
				return new ResponseEntity<Response<User>>(new Response<>("User with specified id not found"),
						HttpStatus.NOT_FOUND);
			}
		} else {
			return new ResponseEntity<Response<User>>(new Response<>("User Id should be greater than 0"),
					HttpStatus.BAD_REQUEST);
		}
	}

	public void createUser(User user) {
		userRepository.createUser(user);

	}

	public ResponseEntity<Response<String>> updateUser(User user, int userId) {

		boolean updated = false;
		if (user != null) {
			user.setUserId(userId);
			updated = userRepository.updateUser(user);
			if (updated) {
				Response<String> response = new Response<>();
				response.setData("User Updated Successfully");
				return new ResponseEntity<Response<String>>(response, HttpStatus.OK);
			} else {
				return new ResponseEntity<Response<String>>(new Response<>("User with specified details not found"),
						HttpStatus.NOT_FOUND);
			}
		}

		return new ResponseEntity<Response<String>>(new Response<>("User s required for updation"),
				HttpStatus.BAD_REQUEST);

	}

	public void deleteUser(int userId) {
		userRepository.deleteUser(userId);

	}

}
