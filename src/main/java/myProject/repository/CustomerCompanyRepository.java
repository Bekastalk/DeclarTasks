package myProject.repository;

import myProject.dto.CustomerCompanyDto;
import myProject.entities.CustomerCompany;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface CustomerCompanyRepository extends JpaRepository<CustomerCompany, Long> {
    CustomerCompany findByName(String name);
    @Query("select new myProject.dto.CustomerCompanyDto(" +
            "cc.name) from CustomerCompany cc")
    Optional<CustomerCompanyDto> findByName1(String name);

    @Query("select new myProject.dto.CustomerCompanyDto(" +
            "cc.name) from CustomerCompany cc")
    List<CustomerCompanyDto> findAllCustomer();

    Optional<CustomerCompanyDto> getCustomerCompaniesById(Long id);
}
