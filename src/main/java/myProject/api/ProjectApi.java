package myProject.api;

import lombok.RequiredArgsConstructor;
import myProject.dto.ProjectDto;
import myProject.dto.SimpleResponse;
import myProject.service.ProjectService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/project")
public class ProjectApi {
    private final ProjectService projectService;

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping
    public SimpleResponse save(@RequestBody ProjectDto projectDto){
        return projectService.save(projectDto);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping
    public List<ProjectDto> getAll(){
        return projectService.getAll();
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/name")
    public ProjectDto getByName(@RequestParam String name){
        return projectService.getByName(name);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping
    public  SimpleResponse update(@RequestParam String name,
                                  @RequestBody ProjectDto projectDto){
        return projectService.update(name,projectDto);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping
    public SimpleResponse delete(@RequestParam String  name){
        return projectService.delete(name);
    }
}
