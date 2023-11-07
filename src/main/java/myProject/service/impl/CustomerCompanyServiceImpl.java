package myProject.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import myProject.dto.CustomerCompanyDto;
import myProject.dto.SimpleResponse;
import myProject.entities.CustomerCompany;
import myProject.entities.Project;
import myProject.exception.AlreadyExistException;
import myProject.exception.NotFoundException;
import myProject.repository.CustomerCompanyRepository;
import myProject.repository.ProjectRepository;
import myProject.service.CustomerCompanyService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class CustomerCompanyServiceImpl implements CustomerCompanyService {
    private final CustomerCompanyRepository customerCompanyRepository;
    private final ProjectRepository projectRepository;

    @Override
    public SimpleResponse create(CustomerCompanyDto customerCompanyDto) {
        CustomerCompany byName = customerCompanyRepository.findByName(customerCompanyDto.getName());
        if(byName==null){
            CustomerCompany customerCompany=new CustomerCompany();
            customerCompany.setName(customerCompanyDto.getName());
            customerCompanyRepository.save(customerCompany);
            return new SimpleResponse(
                    HttpStatus.OK,
                    String.format("Customer Company with name: %s successfully saved!!!", customerCompanyDto.getName())
            );
        }else throw new AlreadyExistException(
                String.format("Customer Company with name: %s already exist!!!", customerCompanyDto.getName()));
    }

    @Override
    public CustomerCompanyDto getCompanyByName(String name) {
        return customerCompanyRepository.findByName1(name).orElseThrow(
                ()->new NotFoundException(
                        String.format("Customer company with name: %s not found!!!")
                ));
    }

    @Override
    public List<CustomerCompanyDto> getAllCompanies() {
        return customerCompanyRepository.findAllCustomer();
    }

    @Override
    public CustomerCompanyDto getById(Long id) {
        return customerCompanyRepository.getCustomerCompaniesById(id)
                .orElseThrow(()->new NotFoundException(
                        String.format("Customer company with id; %s not found!!!", id
                )));
    }

    @Override
    public SimpleResponse update(Long id, CustomerCompanyDto customerCompanyDto) {
        CustomerCompany customerCompany = customerCompanyRepository.findById(id).orElseThrow(() -> new NotFoundException(
                String.format("Customer company with id; %s not found!!!", id)));
        customerCompany.setName(customerCompanyDto.getName());
        customerCompanyRepository.save(customerCompany);
        return new SimpleResponse(
                HttpStatus.OK,
                String.format("Customer Company with id: %s successfully updated!!!", id));
    }

    @Override
    public SimpleResponse delete(Long id) {
        CustomerCompany customerCompany = customerCompanyRepository.findById(id).orElseThrow(() -> new NotFoundException(
                String.format("Customer company with id; %s not found!!!", id)));
        customerCompanyRepository.delete(customerCompany);
        return new SimpleResponse(
                HttpStatus.OK,
                String.format("Customer Company with id: %s successfully deleted!!!", id));
    }

    @Override
    public SimpleResponse assignToProject(Long customerId, Long projectId) {
        Project project = projectRepository.findById(projectId).orElseThrow(
                () -> new NotFoundException(String.format("Project with id: %s not found!!!", projectId)));
        CustomerCompany customerCompany = customerCompanyRepository.findById(customerId).orElseThrow(
                () -> new NotFoundException(String.format("Customer company with id: %s not found!!!", customerId)));
        customerCompany.setProject(project);
        project.setCustomerCompany(customerCompany);
        projectRepository.save(project);
        customerCompanyRepository.save(customerCompany);
        return new SimpleResponse(
                HttpStatus.OK,
                String.format("Customer Company with id: %s successfully assigned!!!", customerId));
    }
}
