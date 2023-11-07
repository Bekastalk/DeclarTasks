package myProject.entities;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@Table(name = "tasks")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Task {
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "task_gen")
    @SequenceGenerator(
            name = "task_gen",
            sequenceName = "task_seq",
            allocationSize = 1)
    private Long id;

    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "project_id", nullable = false)
    private Project project;

    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "employee_id")
    private Employee employee;

}
