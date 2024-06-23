package com.jobhunt.modules.company.controllers;

import com.jobhunt.exceptions.AlreadyExists;
import com.jobhunt.modules.company.entities.CompanyEntity;
import com.jobhunt.modules.company.useCases.CreateCompanyUseCase;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/company")
public class CompanyController {

    @Autowired
    private CreateCompanyUseCase createCompanyUseCase;

    @PostMapping
    public ResponseEntity<Object> create(@Valid @RequestBody CompanyEntity company) {
        try {
            var response = createCompanyUseCase.execute(company);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (AlreadyExists e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }
}
