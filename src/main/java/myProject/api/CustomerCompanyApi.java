package myProject.api;

import lombok.RequiredArgsConstructor;
import myProject.dto.CustomerCompanyDto;
import myProject.dto.SimpleResponse;
import myProject.service.CustomerCompanyService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/customerCompanyApi")
public class CustomerCompanyApi {
    private final CustomerCompanyService customerCompanyService;

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping
    public SimpleResponse save(@RequestBody CustomerCompanyDto customerCompanyDto){
        return customerCompanyService.create(customerCompanyDto);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping
    public List<CustomerCompanyDto> getAll(){
        return customerCompanyService.getAllCompanies();
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/{id}")
    public CustomerCompanyDto getById(@PathVariable Long id){
        return customerCompanyService.getById(id);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/name")
    public CustomerCompanyDto getByName(@RequestParam String name){
        return customerCompanyService.getCompanyByName(name);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("{id}")
    public SimpleResponse update(@PathVariable Long id,
                                 @RequestBody CustomerCompanyDto customerCompanyDto){
        return customerCompanyService.update(id, customerCompanyDto);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("{id}")
    public SimpleResponse delete(@PathVariable Long id){
        return customerCompanyService.delete(id);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/assignToProject")
    public SimpleResponse assign(@RequestParam Long projectId,
                                 @RequestParam Long customerId){
        return customerCompanyService.assignToProject(customerId, projectId);
    }

}
