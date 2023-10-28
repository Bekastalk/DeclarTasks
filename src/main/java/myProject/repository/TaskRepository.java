package myProject.repository;

import myProject.entities.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "tasks")
public interface TaskRepository extends JpaRepository<Task, Long> {
}
