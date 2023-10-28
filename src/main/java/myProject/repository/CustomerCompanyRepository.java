package myProject.repository;

import myProject.entities.CustomerCompany;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@RepositoryRestResource(path = "customerCompany")
@Repository
public interface CustomerCompanyRepository extends JpaRepository<CustomerCompany, Long> {
    Optional<CustomerCompany> findByName(String name);
}
