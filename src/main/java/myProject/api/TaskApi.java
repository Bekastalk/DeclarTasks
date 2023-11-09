package myProject.api;

import lombok.RequiredArgsConstructor;
import myProject.dto.SimpleResponse;
import myProject.dto.TaskDto;
import myProject.service.TaskService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/task")
@RequiredArgsConstructor
@RestController
@PreAuthorize("hasAuthority('ADMIN')")
public class TaskApi {
    private final TaskService taskService;

    @PostMapping
    public SimpleResponse save(@RequestBody TaskDto taskDto){
        return taskService.save(taskDto);
    }

    @GetMapping
    public List<TaskDto> getAll(){
        return taskService.getAll();
    }

    @GetMapping("/name")
    public TaskDto getByName(@RequestParam String name){
        return taskService.getByName(name);
    }

    @PutMapping
    public SimpleResponse update(@RequestParam String name,
                                 @RequestBody TaskDto taskDto){
        return taskService.update(name, taskDto);
    }

    @DeleteMapping
    public SimpleResponse delete(@RequestParam String name){
        return taskService.delete(name);
    }
}
