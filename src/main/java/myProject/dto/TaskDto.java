package myProject.dto;

import myProject.entities.Employee;
import myProject.entities.Project;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TaskDto {
    private Project project;
    private Employee employees;

}
