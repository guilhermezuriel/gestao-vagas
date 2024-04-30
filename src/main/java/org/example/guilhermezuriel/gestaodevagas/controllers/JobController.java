package org.example.guilhermezuriel.gestaodevagas.controllers;

import jakarta.servlet.http.HttpServletRequest;
import org.example.guilhermezuriel.gestaodevagas.entities.company.JobEntity;
import org.example.guilhermezuriel.gestaodevagas.service.job.JobService;
import org.example.guilhermezuriel.gestaodevagas.service.job.dto.JobDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/job")
public class JobController {

    @Autowired
    private JobService jobService;

    @PostMapping("/create")
    public ResponseEntity<Object> createJob(@RequestBody JobEntity jobEntity, HttpServletRequest request) {
        var companyId = request.getAttribute("company_id");
        jobEntity.setCompanyId(UUID.fromString(companyId.toString()));
        try {
            JobDto result = this.jobService.create(jobEntity);
            return ResponseEntity.ok().body(result);
        }catch (Exception e){
            e.printStackTrace();
            return  ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
