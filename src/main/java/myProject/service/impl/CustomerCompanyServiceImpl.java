package myProject.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import myProject.dto.CustomerCompanyDto;
import myProject.service.CustomerCompanyService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class CustomerCompanyServiceImpl implements CustomerCompanyService {
    private final CustomerCompanyService customerCompanyService;
    @Override
    public CustomerCompanyDto create(CustomerCompanyDto customerCompanyDto) {
        return null;
    }

    @Override
    public CustomerCompanyDto getCompany(String name) {
        return null;
    }

    @Override
    public List<CustomerCompanyDto> getAllCompanies() {
        return null;
    }
}
