package myProject.api;

import lombok.RequiredArgsConstructor;
import myProject.dto.ContractorCompanyDto;
import myProject.dto.SimpleResponse;
import myProject.service.ContractorCompanyService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/contractorCompany")
public class ContractorCompanyApi {
    private final ContractorCompanyService contractorCompanyService;

    @PostMapping
    public SimpleResponse save(@RequestBody ContractorCompanyDto contractorCompanyDto){
        return contractorCompanyService.saveContractorCompany(contractorCompanyDto);
    }
}
