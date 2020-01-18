package com.starworkflow.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.starworkflow.model.Project;
import com.starworkflow.model.Status;

/**
 * @author Michaił
 *
 */
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
		List<Project> projects = em.createQuery("SELECT p FROM Project p WHERE p.uuid = :uuid")
				.setParameter("uuid", uuid).getResultList();
		if (projects.size() != 0) {
			return projects.get(0);
		}
		return null;
	}
	@Transactional
	public void changeStatus(boolean finish, boolean skipped, String uuid) {
		em.createQuery("update Status s set s.finish = :finish, s.skipped = :skipped where s.uuid = :uuid")
		.setParameter("uuid", uuid)
		.setParameter("finish", finish)
		.setParameter("skipped", skipped).executeUpdate();
	}
	
	
	@Transactional
	public void addOrEdit(Project project) {
	
		
		em.persist(project);

	
	}
	
	public List<Project> getProjecsByUsername(String username){
		List<Project> projects = em.createQuery("SELECT p FROM Project p WHERE p.userName = :username")
				.setParameter("username", username).getResultList();
		return projects;
	}
	@Transactional
	public void deleteProject(Project project){
		em.remove(project);
	
	}


}
