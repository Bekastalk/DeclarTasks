package myProject.api;

import lombok.RequiredArgsConstructor;
import myProject.dto.ContractorCompanyDto;
import myProject.dto.SimpleResponse;
import myProject.service.ContractorCompanyService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/contractorCompany")
public class ContractorCompanyApi {
    private final ContractorCompanyService contractorCompanyService;

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping
    public SimpleResponse save(@RequestBody ContractorCompanyDto contractorCompanyDto){
        return contractorCompanyService.saveContractorCompany(contractorCompanyDto);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/{id}")
    public ContractorCompanyDto getById(@PathVariable Long id){
        return contractorCompanyService.getCompanyById(id);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping
    public List<ContractorCompanyDto> getAll(){
        return contractorCompanyService.getAllCon();
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/name")
    public ContractorCompanyDto getByName(@RequestBody  String name){
        return contractorCompanyService.getContractorCompanyDtoByName(name);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/{id}")
    public SimpleResponse update(@PathVariable Long id,
                                 @RequestBody ContractorCompanyDto contractorCompanyDto){
        return contractorCompanyService.update(id, contractorCompanyDto);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/{id}")
    public SimpleResponse delete(@PathVariable Long id){
        return contractorCompanyService.delete(id);
    }



}
