package myProject.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import myProject.dto.ContractorCompanyDto;
import myProject.dto.SimpleResponse;
import myProject.entities.ContractorCompany;
import myProject.entities.Project;
import myProject.exception.AlreadyExistException;
import myProject.exception.NotFoundException;
import myProject.repository.ContractorCompanyRepository;
import myProject.repository.ProjectRepository;
import myProject.service.ContractorCompanyService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class ContractorCompanyServiceImpl implements ContractorCompanyService {

    private final ContractorCompanyRepository contractorCompanyRepository;
    private final ProjectRepository projectRepository;
    @Override
    public SimpleResponse saveContractorCompany(ContractorCompanyDto contractorCompanyDto) {
        ContractorCompany byName = contractorCompanyRepository.findByName(contractorCompanyDto.getName());
        if(byName==null) {
            ContractorCompany contractorCompany = new ContractorCompany();
            contractorCompany.setName(contractorCompanyDto.getName());
            contractorCompanyRepository.save(contractorCompany);
            log.info(                    String.format("Contractor company with id: %s successfully saved!!!", contractorCompany.getId())
            );
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
        log.info((String.format("Contractor with name: %s not found", name)));
        return contractorCompanyRepository.findContractorCompanyByNamed(name)
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

    @Override
    public SimpleResponse assignToProject(Long contractorId, Long projectId) {
        Project project = projectRepository.findById(projectId).orElseThrow(
                () -> new NotFoundException(String.format("Project with id: %s not found!!!", projectId)));
        ContractorCompany contractorCompany = contractorCompanyRepository.findById(contractorId).orElseThrow(() -> new NotFoundException
                (String.format("Contractor company with id: %s not found", contractorId)));
        project.setContractorCompany(contractorCompany);
        contractorCompany.setProject(project);
        projectRepository.save(project);
        contractorCompanyRepository.save(contractorCompany);
        return new SimpleResponse(
                HttpStatus.OK,
                String.format("Contractor Company with id: %s successfully assigned!!!", contractorId));
    }
}
