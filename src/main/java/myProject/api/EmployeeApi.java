package myProject.api;

import lombok.RequiredArgsConstructor;
import myProject.dto.EmployeeDto;
import myProject.dto.SimpleResponse;
import myProject.service.EmployeeService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/employee")
@PreAuthorize("hasAuthority('ADMIN')")
public class EmployeeApi {
    private final EmployeeService employeeService;

    @PostMapping
    public SimpleResponse save(@RequestBody EmployeeDto employeeDto){
        return employeeService.save(employeeDto);
    }

    @GetMapping
    public List<EmployeeDto> getAll(){
        return employeeService.getAll();
    }

    @GetMapping("/name")
    public EmployeeDto getByName(@RequestParam String name){
        return employeeService.getByName(name);
    }

    @PostMapping("/assignToTask")
    public SimpleResponse assign(@RequestParam Long empId, @RequestParam Long taskId){
        return employeeService.assign(empId, taskId);
    }

    @PutMapping
    public SimpleResponse update(@RequestParam String name,
                                 @RequestBody EmployeeDto employeeDto){
        return employeeService.update(name, employeeDto);
    }

    @DeleteMapping
    public SimpleResponse delete(@RequestParam String name){
        return employeeService.delete(name);
    }
}
