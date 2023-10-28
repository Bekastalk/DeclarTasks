package myProject.service.impl;


import jakarta.transaction.Transactional;
import myProject.dto.auth.AuthenticationResponse;
import myProject.dto.auth.SignInRequest;
import myProject.dto.auth.SignUpRequest;
import myProject.entities.User;
import myProject.exception.AlreadyExistException;
import myProject.exception.BadCredentialException;
import myProject.exception.NotFoundException;
import myProject.repository.UserRepository;
import myProject.security.jwt.JwtService;
import myProject.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public AuthenticationResponse signUp(SignUpRequest signUpRequest) {
        if (userRepository.existsByEmail(signUpRequest.email())) {
            throw new AlreadyExistException(
                    "User with email: " + signUpRequest.email() + " already exists!");
        }
        User user = User.builder()
                .firstName(signUpRequest.firstName())
                .lastName(signUpRequest.lastName())
                .email(signUpRequest.email())
                .password(passwordEncoder.encode(signUpRequest.password()))
                .role(signUpRequest.role())
                .build();
        userRepository.save(user);

        String token = jwtService.generateToken(user);

        return AuthenticationResponse.builder()
                .token(token)
                .email(user.getEmail())
                .role(user.getRole())
                .build();
    }

    @Override
    public AuthenticationResponse signIn(SignInRequest signInRequest) {
        User user = userRepository.getUserByEmail(signInRequest.email())
                .orElseThrow(
                        () -> new NotFoundException(
                                "User with email: " + signInRequest.email() + " doesn't exist!"));
        if(signInRequest.email().isBlank()){
            throw new BadCredentialException("Email is blank");
        }
        if(!passwordEncoder.matches(signInRequest.password(), user.getPassword())){
            throw new BadCredentialException("Wrong password");
        }
        String token=jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(token)
                .email(user.getEmail())
                .role(user.getRole())
                .build();
    }
}
