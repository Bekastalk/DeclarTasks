package myProject.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import myProject.dto.ContractorCompanyDto;
import myProject.dto.SimpleResponse;
import myProject.entities.ContractorCompany;
import myProject.exception.AlreadyExistException;
import myProject.exception.NotFoundException;
import myProject.repository.ContractorCompanyRepository;
import myProject.service.ContractorCompanyService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ContractorCompanyServiceImpl implements ContractorCompanyService {

    private final ContractorCompanyRepository contractorCompanyRepository;
    @Override
    public SimpleResponse saveContractorCompany(ContractorCompanyDto contractorCompanyDto) {
        ContractorCompany byName = contractorCompanyRepository.findByName(contractorCompanyDto.getName());
        if(byName==null) {
            ContractorCompany contractorCompany = new ContractorCompany();
            contractorCompany.setName(contractorCompanyDto.getName());
            contractorCompanyRepository.save(contractorCompany);
            return new SimpleResponse(
                    HttpStatus.OK,
                    String.format("Contractor company with id: %s successfully saved!!!", contractorCompany.getId())
            );
        }else throw new AlreadyExistException
                (String.format("Company with name: %s already exist", contractorCompanyDto.getName()));
    }

    @Override
    public List<ContractorCompanyDto> getAllCon() {
        return contractorCompanyRepository.getAll();
    }

    @Override
    public ContractorCompanyDto getContractorCompanyDtoByName(String name) {
        return contractorCompanyRepository.findContractorCompanyByName(name)
                .orElseThrow(()->new NotFoundException
                        (String.format("Contractor with name: %s not found", name)));
    }

    @Override
    public ContractorCompanyDto getCompanyById(Long id) {
        return contractorCompanyRepository.getContractorCompaniesById(id)
                .orElseThrow(()->new NotFoundException
                        (String.format("Contractor company with id: %s not found", id)));
    }

    @Override
    public SimpleResponse update(Long id, ContractorCompanyDto contractorCompanyDto) {
        ContractorCompany contractorCompany = contractorCompanyRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(
                        String.format("Contractor company with id: %s not found", id)));
        contractorCompany.setName(contractorCompanyDto.getName());
        contractorCompanyRepository.save(contractorCompany);
        return new SimpleResponse(
                HttpStatus.OK,
                String.format("Contractor company with id: %s successfully updated!", id)
        );
    }

    @Override
    public SimpleResponse delete(Long id) {
        ContractorCompany contractorCompany = contractorCompanyRepository
                .findById(id).orElseThrow(() -> new NotFoundException
                        (String.format("Contractor company with id: %s not found!!! ", id)));
        contractorCompanyRepository.delete(contractorCompany);
        return new SimpleResponse(
                HttpStatus.OK,
                String.format("Contractor company with id:%s successfully deleted!!!", id)
        );
    }
}
