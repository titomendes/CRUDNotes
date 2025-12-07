package example.CRUDNOTES.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import example.CRUDNOTES.dto.NoteInputDTO;
import example.CRUDNOTES.dto.NoteOutputDTO;
import example.CRUDNOTES.entity.Note;
import example.CRUDNOTES.exception.NoteNotFoundException;
import example.CRUDNOTES.mapper.NoteMapper;
import example.CRUDNOTES.repository.NoteRepository;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class NoteService {

    
    private final NoteRepository repo;
    private final NoteMapper mapper;


     // preciso de verificar o utilizador para so  ver as notes dele
    @Transactional(readOnly = true)
    public List<NoteOutputDTO> get(String title) {
        List<Note> notes = repo.findByTitle(title);
        return mapper.toDTOList(notes);
    }

    // falta gravar no utilizador, fazer a ligação bidirecional
    // ir ao contexto ver quem esta, ir procurar a base de dade e fazer
    // saved.setUser
    public NoteOutputDTO create(NoteInputDTO dto) {
        Note newNote = mapper.toEntity(dto);
        Note saved = repo.save(newNote);
        return mapper.toDTO(saved);

    }

    // preciso de verificar o utilizador ants de apagar, com spring security usando
    // o contexto
    public NoteOutputDTO edit(Long id, NoteInputDTO dto) {
        Note noteToEdit = repo.findById(id).orElseThrow(() -> new NoteNotFoundException("Note not found"));
        mapper.edit(dto, noteToEdit);
        Note saved = repo.save(noteToEdit);
        return mapper.toDTO(saved);
    }

    // preciso de verificar o utilizador ants de apagar, com spring security usando
    // o contexto
    public void delete(Long id) {
        if(!repo.existsById(id))
            throw new NoteNotFoundException("Note not found");
        repo.deleteById(id);
    }
}
