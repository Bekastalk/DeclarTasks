package myProject.repository;

import myProject.dto.ContractorCompanyDto;
import myProject.entities.ContractorCompany;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ContractorCompanyRepository extends JpaRepository<ContractorCompany, Long> {
    @Query("select new myProject.dto.ContractorCompanyDto(c.name) from ContractorCompany  c where c.name= :name")
    Optional<ContractorCompanyDto> findContractorCompanyByNamed(String name);
    ContractorCompany findByName(String name);
    @Query("""
            select new myProject.dto.ContractorCompanyDto(c.name)
             from ContractorCompany c
            """)
    List<ContractorCompanyDto> getAll();
    Optional<ContractorCompanyDto> getContractorCompaniesById(Long id);

}
