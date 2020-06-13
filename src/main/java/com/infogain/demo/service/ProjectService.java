package com.infogain.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.infogain.demo.model.Project;
import com.infogain.demo.model.Response;
import com.infogain.demo.repository.ProjectRepository;

@Service
public class ProjectService {
	@Autowired
	ProjectRepository projectRepository;

	public ResponseEntity<Response<Project>> getProjectById(int projectId) {
		if (projectId > 0) {
			Project project = projectRepository.getProjectByProjectId(projectId);
			if (project != null) {
				return new ResponseEntity<Response<Project>>(new Response<>(project), HttpStatus.OK);
			} else {
				return new ResponseEntity<Response<Project>>(new Response<>("Project with specified id not found"),
						HttpStatus.NOT_FOUND);
			}
		} else {
			return new ResponseEntity<Response<Project>>(new Response<>("Project Id should be greater than 0"),
					HttpStatus.BAD_REQUEST);
		}
	}

	public void createProject(Project project) {
		projectRepository.createProject(project);

	}

	public ResponseEntity<Response<String>> updateProject(Project project, int projectId) {

		boolean updated = false;
		if (project != null) {
			project.setProjectId(projectId);
			updated = projectRepository.updateProject(project);
			if (updated) {
				Response<String> response = new Response<>();
				response.setData("Project Updated Successfully");
				return new ResponseEntity<Response<String>>(response, HttpStatus.OK);
			} else {
				return new ResponseEntity<Response<String>>(new Response<>("Project with specified details not found"),
						HttpStatus.NOT_FOUND);
			}
		}

		return new ResponseEntity<Response<String>>(new Response<>("Project s required for updation"),
				HttpStatus.BAD_REQUEST);

	}

	public void deleteProject(int projectId) {
		projectRepository.deleteProject(projectId);

	}

}
