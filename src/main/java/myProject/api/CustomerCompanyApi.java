package myProject.api;

import lombok.RequiredArgsConstructor;
import myProject.dto.CustomerCompanyDto;
import myProject.dto.SimpleResponse;
import myProject.service.CustomerCompanyService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/customerCompanyApi")
public class CustomerCompanyApi {
    private final CustomerCompanyService customerCompanyService;

    @PostMapping
    public SimpleResponse save(@RequestBody CustomerCompanyDto customerCompanyDto){
        return customerCompanyService.create(customerCompanyDto);
    }

    @GetMapping
    public List<CustomerCompanyDto> getAll(){
        return customerCompanyService.getAllCompanies();
    }

    @GetMapping("/{id}")
    public CustomerCompanyDto getById(@PathVariable Long id){
        return customerCompanyService.getById(id);
    }

    @GetMapping("/name")
    public CustomerCompanyDto getByName(@RequestBody String name){
        return customerCompanyService.getCompanyByName(name);
    }

    @PutMapping("{id}")
    public SimpleResponse update(@PathVariable Long id,
                                 @RequestBody CustomerCompanyDto customerCompanyDto){
        return customerCompanyService.update(id, customerCompanyDto);
    }

    @DeleteMapping("{id}")
    public SimpleResponse delete(@PathVariable Long id){
        return customerCompanyService.delete(id);
    }

    @PostMapping("/assignToProject")
    public SimpleResponse assign(@RequestParam Long projectId,
                                 @RequestParam Long customerId){
        return customerCompanyService.assignToProject(customerId, projectId);
    }

}
