package myProject.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "contractor_companies")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ContractorCompany {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;
}
