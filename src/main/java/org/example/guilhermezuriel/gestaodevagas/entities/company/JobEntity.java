package org.example.guilhermezuriel.gestaodevagas.entities.company;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.guilhermezuriel.gestaodevagas.enums.LevelEnum;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity(name = "tb_jobs")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JobEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String description;
    private String benefits;
    private LevelEnum level;

    @ManyToOne()
    @JoinColumn(name="company_id", insertable = false, updatable = false)
    private CompanyEntity company;

    @Column(name = "company_id", nullable = false)
    private UUID companyId;

    @CreationTimestamp
    private LocalDateTime createdAt;
}
