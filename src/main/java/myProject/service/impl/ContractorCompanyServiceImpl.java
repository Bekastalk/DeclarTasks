package myProject.service.impl;

import myProject.dto.ContractorCompanyDto;
import myProject.entities.ContractorCompany;
import myProject.mapping.ContractorCompanyMapper;
import myProject.repository.ContractorCompanyRepository;
import myProject.service.ContractorCompanyService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

public class ContractorCompanyServiceImpl implements ContractorCompanyService {
    @Autowired
    private ContractorCompanyMapper contractorCompanyMapper;
    @Autowired
    private ContractorCompanyRepository contractorCompanyRepository;

    @Override
    public ContractorCompanyDto create(ContractorCompanyDto contractorCompanyDto) {
        Optional<ContractorCompany> existCompany = contractorCompanyRepository
                .findByName(contractorCompanyDto.getName());
        if(existCompany.isPresent()) {
            throw new NoSuchElementException("Company is already exists");
        }

        contractorCompanyRepository.save(contractorCompanyMapper.toEntity(contractorCompanyDto));
        return contractorCompanyDto;
    }
    @Override
    public ContractorCompanyDto getCompany(String name) {
        ContractorCompany company = contractorCompanyRepository.findByName(name)
                .orElseThrow(() -> new NoSuchElementException("Company not found"));

        ContractorCompanyDto companyDto = contractorCompanyMapper.toDto(company);
        return companyDto;
    }
    @Override
    public List<ContractorCompanyDto> getAllCompanies() {
        List<ContractorCompany> list = contractorCompanyRepository.findAll();
        if(list.isEmpty()) {
            throw new NoSuchElementException("List of contractor companies is empty");
        }
        return contractorCompanyMapper.toDtoList(list);
    }
}
