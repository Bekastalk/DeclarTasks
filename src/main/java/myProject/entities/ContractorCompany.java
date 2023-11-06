package myProject.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "contractor_companies")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ContractorCompany {
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "companycont_gen")
    @SequenceGenerator(
            name = "companycont_gen",
            sequenceName = "companycont_seq",
            allocationSize = 1)
    private Long id;

    private String name;
}
