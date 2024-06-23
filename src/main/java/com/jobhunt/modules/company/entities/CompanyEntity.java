package com.jobhunt.modules.company.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.Length;

import java.time.Instant;
import java.util.UUID;

@Data
@Entity(name = "company")
@Table(indexes = {@Index(name = "index_username", columnList = "username", unique = true)})
public class CompanyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Pattern(regexp = "^\\S+$", message = "'username' should not have empty spaces")
    @NotNull(message = "'username' should not be empty")
    @Column(unique = true)
    private String username;

    @Email(message = "'email' should be a valid email")
    @NotNull(message = "'email' should not be empty")
    private String email;

    @NotNull
    @Length(min = 8, max = 255)
    private String password;

    private String website;

    @Length(min = 3, max = 255)
    @Column(unique = true)
    private String name;

    @Length(max = 1024)
    private String description;

    @CreationTimestamp
    private Instant createdAt;

    @CreationTimestamp
    private Instant updatedAt;

    private Instant deletedAt;
}
