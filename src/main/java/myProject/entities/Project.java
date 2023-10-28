package myProject.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "projects")
@Getter
@Setter
public class Project {
    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "priority")
    @Min(value = 1, message = "Priority should not be less than 1")
    @Max(value = 10, message = "Priority should not be greater than 10")
    private short priority;

    @OneToOne
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee teamLeader;

    @OneToOne
    @JoinColumn(name = "customercompany_id", nullable = false)
    private CustomerCompany customerCompany;

//    @OneToOne
//    @JoinColumn(name = "customercompany_id", nullable = false)
//    private ContractorCompany contractorCompany;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;
}
