package myProject.dto.auth;

import myProject.enums.Role;
import lombok.Builder;

@Builder
public record AuthenticationResponse(
        String token,
        String email,
        Role role
) {
}
