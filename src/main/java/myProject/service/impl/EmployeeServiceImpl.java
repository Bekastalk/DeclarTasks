package myProject.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import myProject.dto.EmployeeDto;
import myProject.dto.SimpleResponse;
import myProject.entities.Employee;
import myProject.entities.Project;
import myProject.entities.Task;
import myProject.exception.NotFoundException;
import myProject.repository.EmployeeRepository;
import myProject.repository.ProjectRepository;
import myProject.repository.TaskRepository;
import myProject.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class EmployeeServiceImpl implements EmployeeService {
    private  final EmployeeRepository employeeRepository;
    private final TaskRepository taskRepository;
    private final ProjectRepository projectRepository;

    @Override
    public SimpleResponse save(EmployeeDto employeeDto) {
            Employee  employee=new Employee();
            employee.setFirstName(employeeDto.getFirstName());
            employee.setLastName(employeeDto.getLastName());
            employeeRepository.save(employee);
            return new SimpleResponse(
                    HttpStatus.OK,
                    String.format("Employee with name: %s successfully saved!!!", employee.getFirstName())
            );
    }

    @Override
    public SimpleResponse update(String name, EmployeeDto employeeDto) {
        Employee employee = employeeRepository.findByFirstName(name).orElseThrow(
                () -> new NotFoundException(String.format("Employee with name:%s not found!!!", name)));
        employee.setFirstName(employeeDto.getFirstName());
        employee.setLastName(employeeDto.getLastName());
        employeeRepository.save(employee);
        return new SimpleResponse(
                HttpStatus.OK,
                String.format("Employee with name: %s successfully updated!!!", employee.getFirstName())
        );
    }

    @Override
    public SimpleResponse delete(String name) {
        Employee employee = employeeRepository.findByFirstName(name).orElseThrow(
                () -> new NotFoundException(String.format("Employee with name:%s not found!!!", name)));
        employeeRepository.delete(employee);
        return new SimpleResponse(
                HttpStatus.OK,
                String.format("Employee with name: %s successfully deleted!!!", employee.getFirstName())
        );
    }

    @Override
    public SimpleResponse assign(Long empId, Long taskId) {
        Employee employee = employeeRepository.findById(empId).orElseThrow(
                () -> new NotFoundException("Employee with id: " + empId + " not found!!!"));
        Task task = taskRepository.findById(taskId).orElseThrow(
                () -> new NotFoundException("Task with id: " + taskId + " not found!!!"));
        task.setEmployee(employee);
        taskRepository.save(task);
        return new SimpleResponse(
                HttpStatus.OK,
                String.format("Employee with name: %s successfully assigned!!!", employee.getFirstName())
        );
    }

    @Override
    public SimpleResponse assignToProject(Long empId, Long projectId) {
        Employee employee = employeeRepository.findById(empId).orElseThrow(
                () -> new NotFoundException("Employee with id: " + empId + " not found!!!"));
        Project project = projectRepository.findById(projectId).orElseThrow(
                () -> new NotFoundException("Project with id: " + projectId + " not found!!!"));
        employee.getProject().add(project);
        project.getEmployees().add(employee);
        employeeRepository.save(employee);
        projectRepository.save(project);
        return new SimpleResponse(
                HttpStatus.OK,
                String.format("Employee with id: %s successfully assigned to Project!!!", empId)
        );
    }

    @Override
    public List<EmployeeDto> getAll() {
        return employeeRepository.findAlle();
    }

    @Override
    public EmployeeDto getByName(String name) {
        return employeeRepository.findEmployeeByNamed(name).orElseThrow(
                ()->new NotFoundException(String.format("Employee with name:%s not found!!!", name)));
    }
}
