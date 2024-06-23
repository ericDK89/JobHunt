package com.jobhunt.modules.candidate.useCases;

import com.jobhunt.exceptions.UserAlreadyExistsException;
import com.jobhunt.modules.candidate.CandidateEntity;
import com.jobhunt.modules.candidate.CandidateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateCandidateUseCase {

    @Autowired
    private CandidateRepository candidateRepository;

    public CandidateEntity execute(CandidateEntity candidate) {
        candidateRepository.findByUsernameOrEmail(candidate.getUsername(), candidate.getEmail())
                .ifPresent((user) -> {
                    throw new UserAlreadyExistsException("User already exists");
                });

        return candidateRepository.save(candidate);
    }
}
