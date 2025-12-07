package example.CRUDNOTES.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record AuthRequest(@NotNull @Email String email, @NotNull @Size(min = 8, max = 64, message = "Password must be at least 8 characters max 64") String password) {

}
