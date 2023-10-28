package myProject.dto.auth;


import jakarta.validation.constraints.Email;
import myProject.enums.Role;
import lombok.Builder;

@Builder
public record SignUpRequest(
        String firstName,
        String lastName,
        @Email
        String email,
        String password,
        Role role
) {
}
