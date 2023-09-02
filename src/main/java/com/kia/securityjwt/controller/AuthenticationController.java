package com.kia.securityjwt.controller;


import com.kia.securityjwt.controller.model.AuhtenticateResponse;
import com.kia.securityjwt.controller.model.AuthenticateRequest;
import com.kia.securityjwt.controller.model.RegisterRequest;
import com.kia.securityjwt.service.AuthenticateService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticateService authenticateService;
    @PostMapping("/register")
    public ResponseEntity<AuhtenticateResponse> register(@RequestBody RegisterRequest request){
        return ResponseEntity.ok(authenticateService.register(request));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuhtenticateResponse> register(@RequestBody AuthenticateRequest request){
        //

        return ResponseEntity.ok(authenticateService.authenticate(request));
    }


}
