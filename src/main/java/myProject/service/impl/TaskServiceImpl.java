package myProject.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import myProject.dto.SimpleResponse;
import myProject.dto.TaskDto;
import myProject.entities.Task;
import myProject.exception.NotFoundException;
import myProject.repository.TaskRepository;
import myProject.service.TaskService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@Transactional
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;
    @Override
    public SimpleResponse save(TaskDto taskDto) {
            Task task=new Task();
            task.setName(taskDto.getName());
            taskRepository.save(task);
            return new SimpleResponse(
                    HttpStatus.OK,
                    String.format("Task with name: %s successfully saved!!!", task.getName())
            );
    }

    @Override
    public SimpleResponse update(String name, TaskDto taskDto) {
        Task task = taskRepository.findTaskByName(name).orElseThrow(
                () -> new NotFoundException(String.format("Task with name: %s not found!!!", name)));
        task.setName(taskDto.getName());
        taskRepository.save(task);
        return new SimpleResponse(
                HttpStatus.OK,
                String.format("Task with name: %s successfully updated!!!", task.getName())
        );
    }

    @Override
    public SimpleResponse delete(String name) {
        Task task = taskRepository.findTaskByName(name).orElseThrow(
                () -> new NotFoundException(String.format("Task with name: %s not found!!!", name)));
        taskRepository.delete(task);
        return new SimpleResponse(
                HttpStatus.OK,
                String.format("Task with name: %s successfully deleted!!!", task.getName())
        );
    }

    @Override
    public List<TaskDto> getAll() {
        return taskRepository.getAllTask();
    }

    @Override
    public TaskDto getByName(String name) {
        return taskRepository.getByName(name);
    }
}
