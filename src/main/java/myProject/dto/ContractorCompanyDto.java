package myProject.dto;


import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
public class ContractorCompanyDto {
    private String name;

    public ContractorCompanyDto(String name) {
        this.name = name;
    }
}
