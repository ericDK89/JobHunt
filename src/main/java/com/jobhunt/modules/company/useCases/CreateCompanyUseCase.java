package com.jobhunt.modules.company.useCases;

import com.jobhunt.exceptions.AlreadyExistsException;
import com.jobhunt.modules.company.entities.CompanyEntity;
import com.jobhunt.modules.company.repositories.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CreateCompanyUseCase {

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public CompanyEntity execute(CompanyEntity company) {
        companyRepository.findByUsernameOrEmail(company.getUsername(), company.getEmail())
                .ifPresent((entity) -> {
                    throw new AlreadyExistsException("Company already exists.");
                });

        var password = passwordEncoder.encode(company.getPassword());
        company.setPassword(password);
        return companyRepository.save(company);
    }
}
