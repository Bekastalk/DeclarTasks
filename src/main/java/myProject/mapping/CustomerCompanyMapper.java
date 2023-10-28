package myProject.mapping;

import myProject.dto.CustomerCompanyDto;
import myProject.entities.CustomerCompany;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CustomerCompanyMapper {
    CustomerCompany toEntity(CustomerCompanyDto companyDto);
    CustomerCompanyDto toDto(CustomerCompany destination);
    List<CustomerCompanyDto> toDtoList(List<CustomerCompany> list);
}

