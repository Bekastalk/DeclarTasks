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
    private String name;

    @ManyToOne(cascade = {CascadeType.ALL})
    private Project project;

    @ManyToOne(cascade = {CascadeType.ALL})
    private Employee employee;

}
