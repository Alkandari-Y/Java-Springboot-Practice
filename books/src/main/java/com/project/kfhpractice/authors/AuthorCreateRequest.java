package com.project.kfhpractice.authors;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

public class AuthorCreateRequest {

    @JsonProperty("firstName")
    @NotEmpty(message = "First name is required")
    @Length(max = 255, message = "First name must not exceed 255 characters")
    @Length(min = 2, message = "First name must be at least 2 characters")
    private String firstName;

    @JsonProperty("lastName")
    @NotEmpty(message = "Last name is required")
    @Length(max = 255, message = "Last name must not exceed 255 characters")
    @Length(min = 2, message = "Last name must be at least 2 characters")
    private String lastName;

    public AuthorCreateRequest() {}
    public AuthorCreateRequest(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
