package example.CRUDNOTES.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import example.CRUDNOTES.dto.AuthRequest;
import example.CRUDNOTES.dto.AuthResponse;
import example.CRUDNOTES.exception.EmailAlreadyExistsException;
import example.CRUDNOTES.mapper.UserMapper;
import example.CRUDNOTES.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class AuthService {

    private final UserRepository repo;
    private final UserMapper mapper;
    private final PasswordEncoder encoder;

    // vou me registar com o emaile mais tarde escolher o username e ver se é unico

    public AuthResponse register(AuthRequest dto) {
        if (repo.existsByEmail(dto.email())) {
            throw new EmailAlreadyExistsException("Email already registered");
        }
      
     return null;
    }

}
