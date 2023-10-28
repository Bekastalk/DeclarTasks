package myProject.mapping;

import myProject.dto.ProjectDto;
import myProject.entities.Project;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProjectMapper {
    Project toEntity(ProjectDto employeeDto);
    ProjectDto toDto(Project employee);
    List<ProjectDto> toDtoList(List<Project> list);
}
