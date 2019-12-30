package com.starworkflow.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.starworkflow.model.Project;
import com.starworkflow.model.User;

@Repository
public class ProjectRepository {
	
	@Transactional
	public void addProject() {
		Project project = new Project();
		project.setName("nazwa");
		em.persist(project);
		
	}
	@PersistenceContext
	   private EntityManager em;

	public EntityManager getEm() {
		return em;
	}

	public void setEm(EntityManager em) {
		this.em = em;
	}
	
	public Project getProjectByuuid(String uuid) {
		List<Project> projects = em.createQuery("SELECT p FROM Project p WHERE p.uuid = :uuid").setParameter("uuid", uuid).getResultList();

		
		return projects.get(0);
	}
	
	     
 
	

}
