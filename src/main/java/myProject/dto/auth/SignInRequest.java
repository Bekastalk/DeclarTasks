package myProject.dto.auth;

import lombok.Builder;

@Builder
public record SignInRequest(
        String email,
        String password
) {
}
