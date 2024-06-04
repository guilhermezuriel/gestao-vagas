package org.example.guilhermezuriel.gestaodevagas.entities.candidate;

import jakarta.persistence.*;
import lombok.*;
import org.example.guilhermezuriel.gestaodevagas.entities.company.JobEntity;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity(name = "apply_jobs")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ApplyJobEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID uuid;

    @ManyToOne
    @JoinColumn(name = "candidate_id", insertable = false, updatable = false)
    private CandidateEntity candidate;

    @ManyToOne
    @JoinColumn(name = "job_id", insertable = false, updatable = false)
    private JobEntity jobEntity;

    @Column(name = "candidate_id", nullable = false)
    private UUID candidateId;

    @Column(name = "job_id", nullable = false)
    private UUID jobId;

    @CreationTimestamp
    private LocalDateTime createdAt;

}
