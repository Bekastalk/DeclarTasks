package myProject.service;

import myProject.dto.SimpleResponse;
import myProject.dto.TaskDto;

import java.util.List;

public interface TaskService {
    SimpleResponse save(TaskDto taskDto);
    SimpleResponse update(String name, TaskDto taskDto);
    SimpleResponse delete(String name);
    List<TaskDto> getAll();
    TaskDto getByName(String name);


}
