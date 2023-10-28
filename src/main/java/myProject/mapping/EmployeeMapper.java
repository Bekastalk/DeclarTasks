package myProject.mapping;

import myProject.dto.EmployeeDto;
import myProject.entities.Employee;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {
    Employee toEntity(EmployeeDto employeeDto);
    EmployeeDto toDto(Employee employee);
    List<EmployeeDto> toDtoList(List<Employee> list);
}
