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

import com.infogain.demo.model.Project;
import com.infogain.demo.model.Response;
import com.infogain.demo.service.ProjectService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/project")
@Api(value = "/project")
@CrossOrigin(origins = "*")
public class ProjectController {

	@Autowired
	ProjectService projectService;

	@GetMapping(value = "/{projectId}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "get a project based on a project id", notes = "This API is used to get a specific project", protocols = "http,https", responseContainer = "Response")
	@ApiResponses({ @ApiResponse(code = 200, message = "Project Fetched Successfully"),
			@ApiResponse(code = 500, message = "Internal Server Error"),
			@ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 404, message = "Project Not Found") })
	public ResponseEntity<Response<Project>> getProject(
			@ApiParam(required = true, example = "1") @PathVariable(value = "projectId") int projectId) {
		return projectService.getProjectById(projectId);

	}

	@PostMapping(consumes = { MediaType.APPLICATION_JSON_VALUE })
	@ApiOperation(value = "Creates a new project after validation", consumes = MediaType.APPLICATION_JSON_VALUE, notes = "This endpoint creates a new project", protocols = "http")
	@ApiResponses({ @ApiResponse(code = 204, message = "new project created"),
			@ApiResponse(code = 500, message = "Internal Server Error"),
			@ApiResponse(code = 400, message = "Bad Request") })
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void createProject(@RequestBody Project project) {
		projectService.createProject(project);

	}

	@PutMapping(value = "/{projectId}", produces = { MediaType.APPLICATION_JSON_VALUE }, consumes = {
			MediaType.APPLICATION_JSON_VALUE })
	@ApiOperation(value = "Updates a project", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE, notes = "Updates a project", protocols = "https")
	@ApiResponses({ @ApiResponse(code = 200, message = "project updated successfully"), })
	public ResponseEntity<Response<String>> updateProject(@RequestBody Project project,
			@PathVariable(value = "projectId") int projectId) {
		return projectService.updateProject(project, projectId);
	}

	@DeleteMapping(value = "/{projectId}")
	@ApiOperation(value = "Deletes a project", notes = "Deletes a project from db", protocols = "https")
	@ApiResponses({ @ApiResponse(code = 204, message = "Project deleted") })
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteProject(
			@ApiParam(required = true, example = "1") @PathVariable(value = "projectId") int projectId) {
		projectService.deleteProject(projectId);
	}
}
