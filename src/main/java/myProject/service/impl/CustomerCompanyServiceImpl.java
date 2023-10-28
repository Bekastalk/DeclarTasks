package myProject.service.impl;

import myProject.dto.CustomerCompanyDto;
import myProject.entities.CustomerCompany;
import myProject.mapping.CustomerCompanyMapper;
import myProject.repository.CustomerCompanyRepository;
import myProject.service.CustomerCompanyService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

public class CustomerCompanyServiceImpl implements CustomerCompanyService {
    @Autowired
    private CustomerCompanyMapper customerCompanyMapper;
    @Autowired
    private CustomerCompanyRepository customerCompanyRepository;

    @Override
    public CustomerCompanyDto create(CustomerCompanyDto customerCompanyDto) {
        Optional<CustomerCompany> existCompany = customerCompanyRepository
                .findByName(customerCompanyDto.getName());
        if(existCompany.isPresent()) {
            throw new NoSuchElementException("Company is already exists");
        }

        customerCompanyRepository.save(customerCompanyMapper.toEntity(customerCompanyDto));
        return customerCompanyDto;
    }
    @Override
    public CustomerCompanyDto getCompany(String name) {
        CustomerCompany company = customerCompanyRepository.findByName(name)
                .orElseThrow(() -> new NoSuchElementException("Company not found"));

        CustomerCompanyDto companyDto = customerCompanyMapper.toDto(company);
        return companyDto;
    }
    @Override
    public List<CustomerCompanyDto> getAllCompanies() {
        List<CustomerCompany> list = customerCompanyRepository.findAll();
        if(list.isEmpty()) {
            throw new NoSuchElementException("List of contractor companies is empty");
        }
        return customerCompanyMapper.toDtoList(list);
    }
}
