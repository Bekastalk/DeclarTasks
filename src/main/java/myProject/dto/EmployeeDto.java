package myProject.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmployeeDto {
    private String name;
    private String lastname;
    private String patronymic;
    private String email;

}
