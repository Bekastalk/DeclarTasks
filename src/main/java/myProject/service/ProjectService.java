package myProject.service;

import myProject.dto.ProjectDto;
import myProject.dto.SimpleResponse;

import java.util.List;

public interface ProjectService {
    SimpleResponse save(ProjectDto projectDto);
    SimpleResponse update(String name, ProjectDto projectDto);
    SimpleResponse delete(String name);
    List<ProjectDto> getAll();
    ProjectDto getByName(String name);
    SimpleResponse assignToTask(Long proId, Long taskId);

}
