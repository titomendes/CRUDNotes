package example.CRUDNOTES.service;

import org.springframework.stereotype.Service;

import example.CRUDNOTES.dto.UserInputDTO;
import example.CRUDNOTES.dto.UserOutputDTO;
import example.CRUDNOTES.entity.User;
import example.CRUDNOTES.exception.UserNotFoundException;
import example.CRUDNOTES.mapper.UserMapper;
import example.CRUDNOTES.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {

    private final UserRepository repo;
    private final UserMapper mapper;
    // criar o securityconfig

    // eliminar user

    public void delete(Long id) {
        if (!repo.existsById(id))
            throw new UserNotFoundException("User not found");
        repo.deleteById(id);
    }

    // aqui para editar tambem tens de ir busxar o user ao contexto pois ele é vai
    // ser editado e
    public UserOutputDTO edit(Long id, UserInputDTO dto) {
        User userToEdit = repo.findById(id).orElseThrow(() -> new UserNotFoundException("User not found"));
        mapper.edit(dto, userToEdit);
        User saved = repo.save(userToEdit);
        return mapper.toDTO(saved);

    }
}
