package com.infogain.demo.repository;

import java.util.HashMap;
import java.util.Random;

import org.springframework.stereotype.Repository;

import com.infogain.demo.model.Project;

@Repository
public class ProjectRepository {
	HashMap<Integer, Project> projectDb;
	Random projectIdGenerator;

	public ProjectRepository() {
		projectDb = new HashMap<>();
		projectIdGenerator = new Random();
	}

	public Project getProjectByProjectId(int projectId) {
		return projectDb.get(projectId);
	}

	public void createProject(Project project) {
		int projectId = Math.abs(projectIdGenerator.nextInt());
		project.setProjectId(projectId);
		projectDb.put(projectId, project);
		System.out.println("********** project created" + project);

	}

	public void deleteProject(int projectId) {
		projectDb.remove(projectId);

	}

	public boolean updateProject(Project project) {
		Project projectFromDb = getProjectByProjectId(project.getProjectId());
		if (projectFromDb != null) {
			projectDb.put(project.getProjectId(), project);
			System.out.println("********** project updated" + project);
			return true;
		}
		return false;
	}

}
