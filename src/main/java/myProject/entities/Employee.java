package myProject.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "employees")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Employee {
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "employee_gen")
    @SequenceGenerator(
            name = "employee_gen",
            sequenceName = "employee_seq",
            allocationSize = 1)
    private Long id;

    private String firstName;

    private String lastName;

    @ManyToMany(mappedBy = "employees", cascade = {CascadeType.ALL})
    private List<Project> project;

    @OneToMany(mappedBy = "employee", cascade = {CascadeType.ALL})
    private List<Task> task;

    @OneToOne(mappedBy = "employee", cascade = {CascadeType.ALL})
    private User user;

}
