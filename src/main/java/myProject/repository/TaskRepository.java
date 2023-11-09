package myProject.repository;

import myProject.dto.TaskDto;
import myProject.entities.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    Optional<Task> findTaskByName(String name);

    @Query("select new myProject.dto.TaskDto(t.name) from Task t")
    List<TaskDto> getAllTask();

    @Query("select new myProject.dto.TaskDto(t.name) from Task t where t.name= :name")
    TaskDto getByName(String name);
}
