package myProject.service;


import myProject.dto.EmployeeDto;
import myProject.dto.SimpleResponse;

import java.util.List;

public interface EmployeeService {
    SimpleResponse save(EmployeeDto employeeDto);
    SimpleResponse update(String name, EmployeeDto employeeDto);
    SimpleResponse delete(String name);
    SimpleResponse assign(Long empId, Long taskId);
    SimpleResponse assignToProject(Long empId, Long projectId);

    List<EmployeeDto> getAll();
    EmployeeDto getByName(String name);

}
