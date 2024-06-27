package com.jobhunt.modules.company.dto;

import lombok.Data;

@Data
public class AuthCompanyDTO {

    private String username;
    private String password;

    public AuthCompanyDTO(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
