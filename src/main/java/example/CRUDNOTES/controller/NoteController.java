package example.CRUDNOTES.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import example.CRUDNOTES.dto.NoteInputDTO;
import example.CRUDNOTES.dto.NoteOutputDTO;
import example.CRUDNOTES.service.NoteService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;

@RestController
// necessario apra @Request paranm validações. neste caso o notblank
@Validated
@RequiredArgsConstructor
@RequestMapping("/notes")

public class NoteController {

    private final NoteService service;

    @GetMapping
    public ResponseEntity<List<NoteOutputDTO>> get(@NotBlank @RequestParam String title) {
        return ResponseEntity.ok().body(service.get(title));
    }

    @PostMapping()
    public ResponseEntity<NoteOutputDTO> create(@Valid @RequestBody NoteInputDTO dto) {
        NoteOutputDTO created = service.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<NoteOutputDTO> edit( @PathVariable Long id, @Valid @RequestBody  NoteInputDTO dto) {
        NoteOutputDTO edited = service.edit(id, dto);

        return ResponseEntity.ok(edited);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.ok("item deleted");
    }
    
}
