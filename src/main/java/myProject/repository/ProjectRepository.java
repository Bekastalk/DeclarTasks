package myProject.repository;

import myProject.dto.ProjectDto;
import myProject.entities.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
    Optional<Project> findProjectByName(String name);
    @Query("select new myProject.dto.ProjectDto(p.name," +
            "p.priority," +
            "p.createdDate," +
            "p.graduationDate) from Project p where p.name= :name")
    Optional<ProjectDto> findProjectByNamed(String name);
    Project findByName(String name);

    @Query("select new myProject.dto.ProjectDto(p.name,p.priority,p.createdDate,p.graduationDate) from Project p")
    List<ProjectDto> findAllA();
}
