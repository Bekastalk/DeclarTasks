package myProject.api;

import lombok.RequiredArgsConstructor;
import myProject.service.ContractorCompanyService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/contractorCompany")
public class ContractorCompanyApi {
    private final ContractorCompanyService contractorCompanyService;
}
