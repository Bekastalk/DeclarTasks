package myProject.mapping;

import myProject.dto.ContractorCompanyDto;
import myProject.entities.ContractorCompany;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ContractorCompanyMapper {
    ContractorCompany toEntity(ContractorCompanyDto companyDto);
    ContractorCompanyDto toDto(ContractorCompany destination);
    List<ContractorCompanyDto> toDtoList(List<ContractorCompany> list);
}
