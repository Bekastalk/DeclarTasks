package myProject.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import myProject.dto.EmployeeDto;
import myProject.dto.SimpleResponse;
import myProject.entities.Employee;
import myProject.entities.Task;
import myProject.exception.NotFoundException;
import myProject.repository.EmployeeRepository;
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

    @Override
    public SimpleResponse save(EmployeeDto employeeDto) {
            Employee  employee=new Employee();
            employee.setName(employeeDto.getName());
            employee.setLastname(employeeDto.getLastname());
            employee.setPatronymic(employeeDto.getPatronymic());
            employee.setEmail(employeeDto.getEmail());
            employeeRepository.save(employee);
            return new SimpleResponse(
                    HttpStatus.OK,
                    String.format("Employee with name: %s successfully saved!!!", employee.getName())
            );
    }

    @Override
    public SimpleResponse update(String name, EmployeeDto employeeDto) {
        Employee employee = employeeRepository.findEmployeeByName(name).orElseThrow(
                () -> new NotFoundException(String.format("Employee with name:%s not found!!!", name)));
        employee.setName(employeeDto.getName());
        employee.setLastname(employeeDto.getLastname());
        employee.setPatronymic(employeeDto.getPatronymic());
        employee.setEmail(employeeDto.getEmail());
        employeeRepository.save(employee);
        return new SimpleResponse(
                HttpStatus.OK,
                String.format("Employee with name: %s successfully updated!!!", employee.getName())
        );
    }

    @Override
    public SimpleResponse delete(String name) {
        Employee employee = employeeRepository.findEmployeeByName(name).orElseThrow(
                () -> new NotFoundException(String.format("Employee with name:%s not found!!!", name)));
        employeeRepository.delete(employee);
        return new SimpleResponse(
                HttpStatus.OK,
                String.format("Employee with name: %s successfully deleted!!!", employee.getName())
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
                String.format("Employee with name: %s successfully assigned!!!", employee.getName())
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
