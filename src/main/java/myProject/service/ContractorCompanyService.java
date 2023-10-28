package myProject.service;

import myProject.dto.ContractorCompanyDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ContractorCompanyService {
    ContractorCompanyDto create(ContractorCompanyDto contractorCompanyDto);

    ContractorCompanyDto getCompany(String name);

    List<ContractorCompanyDto> getAllCompanies();
}
