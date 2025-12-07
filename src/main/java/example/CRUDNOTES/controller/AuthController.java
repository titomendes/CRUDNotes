package example.CRUDNOTES.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import example.CRUDNOTES.dto.AuthRequest;
import example.CRUDNOTES.dto.AuthResponse;
import example.CRUDNOTES.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final AuthService service;
       // endpoint do cliente
    @PostMapping()
    public ResponseEntity<AuthResponse> register(@Valid @RequestBody AuthRequest dto) {
        AuthResponse created = service.register(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

}
