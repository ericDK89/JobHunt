package com.jobhunt.modules.company.useCases;

import com.jobhunt.exceptions.AlreadyExists;
import com.jobhunt.modules.company.entities.CompanyEntity;
import com.jobhunt.modules.company.repositories.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateCompanyUseCase {

    @Autowired
    private CompanyRepository companyRepository;

    public CompanyEntity execute(CompanyEntity company) {
        companyRepository.findByUsernameOrEmail(company.getUsername(), company.getEmail())
                .ifPresent((entity) -> {
                    throw new AlreadyExists("Company already exists.");
                });

        return companyRepository.save(company);
    }
}
