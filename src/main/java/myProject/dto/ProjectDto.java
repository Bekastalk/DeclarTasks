package myProject.dto;


import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.*;
import java.time.LocalDate;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProjectDto {
    private String name;
    @Min(value = 1, message = "Priority should not be less than 1")
    @Max(value = 10, message = "Priority should not be greater than 10")
    private short priority;
    private LocalDate startDate;
    private LocalDate endDate;

}
