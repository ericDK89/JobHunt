package com.jobhunt.modules.candidate.useCases;

import com.jobhunt.exceptions.AlreadyExistsException;
import com.jobhunt.modules.candidate.CandidateEntity;
import com.jobhunt.modules.candidate.CandidateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CreateCandidateUseCase {

    @Autowired
    private CandidateRepository candidateRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public CandidateEntity execute(CandidateEntity candidate) {
        candidateRepository.findByUsernameOrEmail(candidate.getUsername(), candidate.getEmail())
                .ifPresent((entity) -> {
                    throw new AlreadyExistsException("User already exists");
                });
        var password = passwordEncoder.encode(candidate.getPassword());
        candidate.setPassword(password);
        return candidateRepository.save(candidate);
    }
}
