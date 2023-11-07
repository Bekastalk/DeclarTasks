package myProject.dto;

import lombok.*;
import myProject.entities.Employee;
import myProject.entities.Project;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TaskDto {
    private Project project;
    private Employee employees;

}
