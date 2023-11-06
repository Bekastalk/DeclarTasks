package myProject.service;

import myProject.dto.ContractorCompanyDto;
import myProject.dto.SimpleResponse;
import org.springframework.stereotype.Service;

import java.util.List;


public interface ContractorCompanyService {
    SimpleResponse saveContractorCompany(ContractorCompanyDto contractorCompanyDto);
    List<ContractorCompanyDto> getAllCon();
    ContractorCompanyDto getContractorCompanyDtoByName(String name);

    ContractorCompanyDto getCompanyById(Long id);
    SimpleResponse update(Long id, ContractorCompanyDto contractorCompanyDto);
    SimpleResponse delete(Long id);

}
