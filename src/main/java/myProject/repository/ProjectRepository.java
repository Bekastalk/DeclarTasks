package myProject.repository;

import myProject.entities.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@Repository
@RepositoryRestResource(path = "project")
public interface ProjectRepository extends JpaRepository<Project, Long> {
}
