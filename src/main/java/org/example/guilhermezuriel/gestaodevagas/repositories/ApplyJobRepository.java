package org.example.guilhermezuriel.gestaodevagas.repositories;

import org.example.guilhermezuriel.gestaodevagas.entities.candidate.ApplyJobEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ApplyJobRepository extends JpaRepository<ApplyJobEntity, UUID> {
}
