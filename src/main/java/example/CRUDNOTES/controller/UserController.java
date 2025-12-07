package example.CRUDNOTES.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import example.CRUDNOTES.dto.UserInputDTO;
import example.CRUDNOTES.dto.UserOutputDTO;
import example.CRUDNOTES.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@Validated
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService service;

    // endpoint do cliente
    @PutMapping("/{id}")
    public ResponseEntity<UserOutputDTO> edit(@PathVariable Long id, @Valid @RequestBody UserInputDTO dto) {
        UserOutputDTO userToEdit = service.edit(id, dto);
        return ResponseEntity.ok(userToEdit);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.ok("User deleted");
    }
}
