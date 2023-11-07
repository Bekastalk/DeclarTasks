package myProject.service;

import myProject.dto.CustomerCompanyDto;
import myProject.dto.SimpleResponse;

import java.util.List;

public interface CustomerCompanyService {
    SimpleResponse create(CustomerCompanyDto customerCompanyDto);

    CustomerCompanyDto getCompanyByName(String name);

    List<CustomerCompanyDto> getAllCompanies();

    CustomerCompanyDto getById(Long id);

    SimpleResponse update(Long id, CustomerCompanyDto customerCompanyDto);
    SimpleResponse delete(Long id );

    SimpleResponse assignToProject(Long customerId, Long projectId);
}
