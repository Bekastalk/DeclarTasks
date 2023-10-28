package myProject.repository;

import myProject.entities.ContractorCompany;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RepositoryRestResource(path = "contractorCompany")
public interface ContractorCompanyRepository extends JpaRepository<ContractorCompany, Long> {
    Optional<ContractorCompany> findByName(String name);
}
