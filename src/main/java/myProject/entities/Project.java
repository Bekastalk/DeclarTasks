package myProject.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "projects")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Project {
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "project_gen")
    @SequenceGenerator(
            name = "project_gen",
            sequenceName = "project_seq",
            allocationSize = 1)
    private Long id;

    private String name;

    @Min(value = 1, message = "Priority should not be less than 1")
    @Max(value = 10, message = "Priority should not be greater than 10")
    private short priority;

    @ManyToMany( cascade = {CascadeType.ALL})
    @JoinColumn(name = "employee_id", nullable = false)
    private List<Employee> employees;

    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "customer_company_id", nullable = false)
    private CustomerCompany customerCompany;

    @OneToMany(mappedBy = "project", cascade = {CascadeType.ALL})
    private List<Task> task;


    private LocalDate createdDate;

    private LocalDate graduationDate;

}
