package myProject.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import myProject.dto.ProjectDto;
import myProject.dto.SimpleResponse;
import myProject.entities.Project;
import myProject.entities.Task;
import myProject.exception.AlreadyExistException;
import myProject.exception.NotFoundException;
import myProject.repository.ProjectRepository;
import myProject.repository.TaskRepository;
import myProject.service.ProjectService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class ProjectServiceImpl implements ProjectService {
    private final ProjectRepository projectRepository;
    private final TaskRepository taskRepository;
    @Override
    public SimpleResponse save(ProjectDto projectDto) {
        Project byName = projectRepository.findByName(projectDto.getName());
        if(byName==null){
            Project project=new Project();
            project.setName(projectDto.getName());
            project.setPriority(projectDto.getPriority());
            project.setCreatedDate(LocalDate.now());
            project.setGraduationDate(projectDto.getEndDate());
            projectRepository.save(project);
            log.info(String.format("Project with name:%s successfully saved!!!", project.getName()));
            return new SimpleResponse(
                    HttpStatus.OK,
                    (String.format("Project with name:%s successfully saved!!!", project.getName()))
            );
        }else throw new AlreadyExistException(String.format("Project with name:%s already exist!!!", projectDto.getName()));
    }

    @Override
    public SimpleResponse update(String name, ProjectDto projectDto) {
        Project project = projectRepository.findProjectByName(name).orElseThrow(
                () -> new NotFoundException(String.format("Project with name:%s not found", name)));
        project.setName(projectDto.getName());
        project.setPriority(projectDto.getPriority());
        project.setCreatedDate(LocalDate.now());
        project.setGraduationDate(projectDto.getEndDate());
        projectRepository.save(project);
        log.info(String.format("Project with name:%s successfully updated!!!", project.getName()));
        return new SimpleResponse(
                HttpStatus.OK,
                (String.format("Project with name:%s successfully updated!!!", project.getName()))
        );

    }

    @Override
    public SimpleResponse delete(String name) {
        Project project = projectRepository.findProjectByName(name).orElseThrow(
                () -> new NotFoundException(String.format("Project with name:%s not found", name)));
        projectRepository.delete(project);
        return new SimpleResponse(
                HttpStatus.OK,
                (String.format("Project with name:%s successfully deleted!!!", project.getName()))
        );
    }

    @Override
    public List<ProjectDto> getAll() {
        return  projectRepository.findAllA();
    }

    @Override
    public ProjectDto getByName(String name) {
        return projectRepository.findProjectByNamed(name).orElseThrow(
                () -> new NotFoundException(String.format("Project with name:%s not found", name)));
    }

    @Override
    public SimpleResponse assignToTask(Long proId, Long taskId) {
        Project project = projectRepository.findById(proId).orElseThrow(
                () -> new NotFoundException(String.format("Project with id: %s not found!!!", proId)));
        Task task = taskRepository.findById(taskId).orElseThrow(
                () -> new NotFoundException(String.format("Task with id: %s not found!!!", taskId)));
        task.setProject(project);
        taskRepository.save(task);
        return new SimpleResponse(
                HttpStatus.OK,
                String.format("Project with id: %s successfully assigned to task!!!", proId)
        );
    }
}
