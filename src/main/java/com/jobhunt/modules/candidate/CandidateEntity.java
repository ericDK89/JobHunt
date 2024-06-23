package com.jobhunt.modules.candidate;

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
@Entity(name = "candidate")
@Table(indexes = {@Index(name = "index_username", columnList = "username", unique = true)})
public class CandidateEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Pattern(regexp = "^\\S+$", message = "'name' should not have empty spaces")
    @Length(min = 3, max = 255)
    @NotNull(message = "'name' should not be empty")
    private String name;

    @Pattern(regexp = "^\\S+$", message = "'username' should not have empty spaces")
    @Length(min = 3, max = 255)
    @NotNull(message = "'username' should not be empty")
    @Column(unique = true)
    private String username;

    @Email(message = "'email' should be a valid email")
    @NotNull(message = "'email' should not be empty")
    @Column(unique = true)
    private String email;

    @Length(min = 8, max = 255)
    @NotNull(message = "'password' should not be empty")
    private String password;

    private String description;
    private String curriculum;

    @CreationTimestamp
    private Instant createdAt;

    @CreationTimestamp
    private Instant updatedAt;

    private Instant deletedAt;
}
