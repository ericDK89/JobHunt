package com.jobhunt.modules.company.useCases;

import com.jobhunt.modules.company.entities.JobEntity;
import com.jobhunt.modules.company.repositories.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateJobUseCase {

    @Autowired
    private JobRepository jobRepository;

    public JobEntity execute(JobEntity job) {
        return jobRepository.save(job);
    }
}
