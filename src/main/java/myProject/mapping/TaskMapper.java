package myProject.mapping;

import myProject.dto.TaskDto;
import myProject.entities.Task;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TaskMapper {
    Task toEntity(TaskDto employeeDto);
    TaskDto toDto(Task employee);
    List<TaskDto> toDtoList(List<Task> list);
}
