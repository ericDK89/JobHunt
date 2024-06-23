package com.jobhunt.exceptions;

import lombok.Data;

@Data
public class ErrorDTO {
    private final String message;
    private final String field;
}
