package myProject.dto;


import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.*;
import myProject.entities.Project;

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

    public Project build() {
        Project project = new Project();
        project.setName(this.name);
        project.setPriority(this.priority);
        project.setCreatedDate(this.startDate);
        project.setGraduationDate(this.endDate);
        return project;
    }
}
