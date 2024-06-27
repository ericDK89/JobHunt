package com.jobhunt.modules.candidate.useCases;

import com.jobhunt.modules.company.entities.JobEntity;
import com.jobhunt.modules.company.repositories.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
public class ListAllJobsByFilterUseCase {

    @Autowired
    private JobRepository jobRepository;

    public List<JobEntity> execute(String filter) {
        return jobRepository.findByDescriptionContaining(filter);
    }

}
