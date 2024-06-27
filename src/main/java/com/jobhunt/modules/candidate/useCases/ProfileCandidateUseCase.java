package com.jobhunt.modules.candidate.useCases;

import com.jobhunt.exceptions.NotFoundException;
import com.jobhunt.modules.candidate.CandidateRepository;
import com.jobhunt.modules.candidate.dto.ProfileCandidateResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ProfileCandidateUseCase {

    @Autowired
    private CandidateRepository candidateRepository;

    public ProfileCandidateResponseDTO execute(UUID idCandidate) {
        var candidate = candidateRepository.findById(idCandidate)
                .orElseThrow(() -> {
                    throw new NotFoundException("User not found");
                });

        var candidateDTO = ProfileCandidateResponseDTO.builder()
                .email(candidate.getEmail())
                .name(candidate.getName())
                .username(candidate.getUsername())
                .description(candidate.getDescription())
                .build();

        return candidateDTO;
    }
}
