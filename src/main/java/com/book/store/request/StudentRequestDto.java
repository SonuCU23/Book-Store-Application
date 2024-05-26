package com.book.store.request;

import lombok.Data;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Data
public class StudentRequestDto {

    @NotBlank(message = "Name must not be blank")
    private String name;

    @Email
    @NotBlank(message = "email Id must not be blank")
    private String emailId;

    @NotBlank(message = "Mobile Number must not be blank")
    private String mobile;
}
