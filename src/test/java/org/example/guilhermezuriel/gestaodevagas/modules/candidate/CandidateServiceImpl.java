package org.example.guilhermezuriel.gestaodevagas.modules.candidate;


import org.example.guilhermezuriel.gestaodevagas.entities.candidate.CandidateEntity;
import org.example.guilhermezuriel.gestaodevagas.exceptions.JobNotFoundException;
import org.example.guilhermezuriel.gestaodevagas.repositories.CandidateRepository;
import org.example.guilhermezuriel.gestaodevagas.repositories.JobRepository;
import org.example.guilhermezuriel.gestaodevagas.service.candidate.CandidateService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
public class CandidateServiceImpl {

    @Mock
    private CandidateService candidateService;
    @Mock
    private CandidateRepository candidateRepository;
    @Mock
    private JobRepository jobRepository;

    @Test
    @DisplayName("Should not be able to apply job with candidate not found")
    public void shouldNotBeAbleToApplyJobWithCandidateNotFound() {
        try {
            this.candidateService.applyJobByCandidate(null, null);
        }catch (Exception e) {
            assertThat(e).isInstanceOf(UsernameNotFoundException.class);
        }
    }

    @Test
    @DisplayName("Should not be able to apply candidate with job not found")
    public void shouldNotBeAbleToApplyCandidateWithJobNotFound() {
        var iCandidate = UUID.randomUUID();
        var candidate = new CandidateEntity();
        candidate.setId(iCandidate);
        when(candidateRepository.findById(iCandidate)).thenReturn(Optional.of(candidate));

        try {
            this.candidateService.applyJobByCandidate(iCandidate, null);
        }catch(Exception e) {
            assertThat(e).isInstanceOf(JobNotFoundException.class);
        }
    }
}
