package com.infogain.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.infogain.demo.model.Response;
import com.infogain.demo.model.User;
import com.infogain.demo.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/user")
@Api(value = "/user")
@CrossOrigin(origins = "*")
public class UserController {

	@Autowired
	UserService userService;

	@GetMapping(value = "/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "get a user based on a user id", notes = "This API is used to get a specific user", protocols = "http,https", responseContainer = "Response")
	@ApiResponses({ @ApiResponse(code = 200, message = "User Fetched Successfully"),
			@ApiResponse(code = 500, message = "Internal Server Error"),
			@ApiResponse(code = 400, message = "Bad Request"), @ApiResponse(code = 404, message = "User Not Found") })
	public ResponseEntity<Response<User>> getUser(
			@ApiParam(required = true, example = "1") @PathVariable(value = "userId") int userId) {
		return userService.getUserById(userId);

	}

	@PostMapping(consumes = { MediaType.APPLICATION_JSON_VALUE })
	@ApiOperation(value = "Creates a new user after validation", consumes = MediaType.APPLICATION_JSON_VALUE, notes = "This endpoint creates a new user", protocols = "http")
	@ApiResponses({ @ApiResponse(code = 204, message = "new user created"),
			@ApiResponse(code = 500, message = "Internal Server Error"),
			@ApiResponse(code = 400, message = "Bad Request") })
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void createUser(@RequestBody User user) {
		userService.createUser(user);

	}

	@PutMapping(value = "/{userId}", produces = { MediaType.APPLICATION_JSON_VALUE }, consumes = {
			MediaType.APPLICATION_JSON_VALUE })
	@ApiOperation(value = "Updates a user", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE, notes = "Updates a user", protocols = "https")
	@ApiResponses({ @ApiResponse(code = 200, message = "user updated successfully"), })
	public ResponseEntity<Response<String>> updateUser(@RequestBody User user,
			@PathVariable(value = "userId") int userId) {
		return userService.updateUser(user, userId);
	}

	@DeleteMapping(value = "/{userId}")
	@ApiOperation(value = "Deletes a user", notes = "Deletes a user from db", protocols = "https")
	@ApiResponses({ @ApiResponse(code = 204, message = "User deleted") })
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteUser(@ApiParam(required = true, example = "1") @PathVariable(value = "userId") int userId) {
		userService.deleteUser(userId);
	}
}
