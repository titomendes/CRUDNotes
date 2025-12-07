package example.CRUDNOTES.dto;

import example.CRUDNOTES.entity.enums.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UserInputDTO(

    @NotBlank(message = "Username is mandatory")
    @Size(max = 30, message = "Username cannot exceed 30 characters")
    String username,

    @NotBlank(message = "Email is mandatory")
    @Size(max = 50, message = "Email cannot exceed 50 characters")
    @Email(message = "Email must be valid")
    String email,

    @NotBlank(message = "Password is mandatory")
    @Size(min = 8, max = 64, message = "Password must be at least 8 characters max 64")
    String password,

    Role role
) { }



