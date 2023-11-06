package myProject.service;

import myProject.dto.CustomerCompanyDto;

import java.util.List;

public interface CustomerCompanyService {
    CustomerCompanyDto create(CustomerCompanyDto customerCompanyDto);

    CustomerCompanyDto getCompany(String name);

    List<CustomerCompanyDto> getAllCompanies();
}
