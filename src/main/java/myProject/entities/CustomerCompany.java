package myProject.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "customer_companies")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerCompany {
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "customerCompany_gen")
    @SequenceGenerator(
            name = "customerCompany_gen",
            sequenceName = "customerCompany_seq",
            allocationSize = 1)
    private Long id;

    private String name;

    @OneToOne(mappedBy = "customerCompany", cascade = {CascadeType.ALL})
    private Project project;
}
