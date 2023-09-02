package com.kia.securityjwt.service;


import com.kia.securityjwt.config.JwtService;
import com.kia.securityjwt.controller.model.AuhtenticateResponse;
import com.kia.securityjwt.controller.model.AuthenticateRequest;
import com.kia.securityjwt.controller.model.RegisterRequest;
import com.kia.securityjwt.model.Role;
import com.kia.securityjwt.model.User;
import com.kia.securityjwt.repository.UserRepository;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticateService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService ;
    private final AuthenticationManager authenticationManager ;


    public AuhtenticateResponse register (RegisterRequest request){
        var user = User.builder()
                .firstname(request.getFirstname())
                .lastname(request.getLastname())
                .email(request.getEmail())
                .role(Role.USER)
                .password(passwordEncoder.encode(request.getPassword()))
                .build();
        userRepository.save(user);
        var token = jwtService.generateToken(user);
        return AuhtenticateResponse.builder()
                .token(token)
                .build();
    }

    public AuhtenticateResponse authenticate(AuthenticateRequest request){
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),request.getPassword()));
        var user = userRepository.findByEmail(request.getEmail()).orElseThrow(()->new UsernameNotFoundException("user not found "));
        var token = jwtService.generateToken(user);
        return AuhtenticateResponse.builder()
                .token(token)
                .build();
    }
}
