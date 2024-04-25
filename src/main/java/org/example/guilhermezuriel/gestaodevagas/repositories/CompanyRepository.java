package org.example.guilhermezuriel.gestaodevagas.repositories;

import org.example.guilhermezuriel.gestaodevagas.entities.company.CompanyEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface CompanyRepository extends JpaRepository<CompanyEntity, UUID> {
    Optional<CompanyEntity> findByUsernameOrEmail(String username, String email);
    Optional<CompanyEntity> findById(UUID id);
    Optional<CompanyEntity> findByUsername(String username);
}
