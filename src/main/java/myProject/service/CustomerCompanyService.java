package myProject.service;

import myProject.dto.CustomerCompanyDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CustomerCompanyService {
    CustomerCompanyDto create(CustomerCompanyDto customerCompanyDto);

    CustomerCompanyDto getCompany(String name);

    List<CustomerCompanyDto> getAllCompanies();
}
