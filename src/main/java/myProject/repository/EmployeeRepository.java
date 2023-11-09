package myProject.repository;

import myProject.dto.EmployeeDto;
import myProject.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Employee findByName(String name);

    Optional<Employee> findEmployeeByName(String name);

    @Query("select new myProject.dto.EmployeeDto(e.name, e.lastname, e.patronymic,e.email) from Employee e")
    List<EmployeeDto> findAlle();

    @Query("select new myProject.dto.EmployeeDto(e.name, e.lastname, e.patronymic,e.email) from Employee e where e.name=:name")
   Optional <EmployeeDto> findEmployeeByNamed(String name);
}
