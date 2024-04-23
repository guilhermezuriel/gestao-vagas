package org.example.guilhermezuriel.gestaodevagas.repositories;

import org.example.guilhermezuriel.gestaodevagas.entities.company.JobEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface JobRepository extends JpaRepository<JobEntity, UUID> {
}
