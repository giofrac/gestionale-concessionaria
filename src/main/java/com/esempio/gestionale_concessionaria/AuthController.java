package com.esempio.gestionale_concessionaria;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public AuthController(AuthenticationManager authenticationManager, JwtService jwtService) {
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest richiesta) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(richiesta.username(), richiesta.password())
            );
        } catch (AuthenticationException e) {
            ProblemDetail problema = ProblemDetail.forStatus(HttpStatus.UNAUTHORIZED);
            problema.setTitle("Credenziali non valide");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(problema);
        }
        return ResponseEntity.ok(new LoginResponse(jwtService.generaToken(richiesta.username())));
    }
}
