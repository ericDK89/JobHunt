package com.jobhunt.modules.company.useCases;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.jobhunt.exceptions.AuthenticationException;
import com.jobhunt.exceptions.NotFoundException;
import com.jobhunt.modules.company.dto.AuthCompanyDTO;
import com.jobhunt.modules.company.dto.AuthCompanyResponseDTO;
import com.jobhunt.modules.company.repositories.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;

@Service
public class AuthCompanyUseCase {

    @Value("${security.token.secret}")
    private String secretKey;

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public AuthCompanyResponseDTO execute(AuthCompanyDTO authCompanyDTO) {
        var company = companyRepository.findByUsername(authCompanyDTO.getUsername())
                .orElseThrow(() -> new NotFoundException("Company not found."));

        var doesPasswordsMatches = passwordEncoder.matches(authCompanyDTO.getPassword(), company.getPassword());

        if (!doesPasswordsMatches) {
            throw new AuthenticationException("Username/password incorrect");
        }

        var algorithm = Algorithm.HMAC256(secretKey);

        var expires_in = Instant.now().plus(Duration.ofDays(7));
        var token = JWT.create().withIssuer("JobHunt")
                .withSubject(company.getId().toString())
                .withExpiresAt(expires_in)
                .withClaim("roles", Arrays.asList("COMPANY"))
                .sign(algorithm);

        return AuthCompanyResponseDTO
                .builder()
                .access_token(token)
                .expires_in(expires_in.toEpochMilli())
                .build();
    }
}
