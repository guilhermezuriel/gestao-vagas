package org.example.guilhermezuriel.gestaodevagas.service.job.impl;

import org.example.guilhermezuriel.gestaodevagas.entities.company.JobEntity;
import org.example.guilhermezuriel.gestaodevagas.repositories.JobRepository;
import org.example.guilhermezuriel.gestaodevagas.service.job.JobService;
import org.example.guilhermezuriel.gestaodevagas.service.job.dto.JobDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JobServiceImpl implements JobService {
    @Autowired
    private JobRepository jobRepository;

    @Override
    public JobDto create(JobEntity jobEntity) {
        return null;
    }
}
