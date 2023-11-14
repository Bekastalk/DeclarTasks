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

    Optional<Employee> findByFirstName(String name);

    @Query("select new myProject.dto.EmployeeDto(e.firstName, e.lastName) from Employee e")
    List<EmployeeDto> findAlle();

    @Query("select new myProject.dto.EmployeeDto(e.firstName, e.lastName) from Employee e where e.firstName=:name")
   Optional <EmployeeDto> findEmployeeByNamed(String name);
}
