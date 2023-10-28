package myProject.service;


import myProject.dto.auth.AuthenticationResponse;
import myProject.dto.auth.SignInRequest;
import myProject.dto.auth.SignUpRequest;

public interface AuthenticationService {

    AuthenticationResponse signUp(SignUpRequest signUpRequest);
    AuthenticationResponse signIn(SignInRequest signInRequest);
}
