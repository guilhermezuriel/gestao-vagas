package org.example.guilhermezuriel.gestaodevagas.service.job;

import org.example.guilhermezuriel.gestaodevagas.entities.company.JobEntity;
import org.example.guilhermezuriel.gestaodevagas.service.job.dto.JobDto;

public interface JobService {

    public JobDto create(JobEntity jobEntity);
}
