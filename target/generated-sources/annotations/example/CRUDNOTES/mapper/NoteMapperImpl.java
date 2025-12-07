package example.CRUDNOTES.mapper;

import example.CRUDNOTES.dto.NoteInputDTO;
import example.CRUDNOTES.dto.NoteOutputDTO;
import example.CRUDNOTES.entity.Note;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-12-07T06:59:35+0000",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.2 (Oracle Corporation)"
)
@Component
public class NoteMapperImpl implements NoteMapper {

    @Override
    public NoteOutputDTO toDTO(Note note) {
        if ( note == null ) {
            return null;
        }

        Long id = null;
        String title = null;
        String content = null;

        id = note.getId();
        title = note.getTitle();
        content = note.getContent();

        NoteOutputDTO noteOutputDTO = new NoteOutputDTO( id, title, content );

        return noteOutputDTO;
    }

    @Override
    public Note toEntity(NoteInputDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Note note = new Note();

        note.setContent( dto.content() );
        note.setTitle( dto.title() );

        return note;
    }

    @Override
    public List<NoteOutputDTO> toDTOList(List<Note> notes) {
        if ( notes == null ) {
            return null;
        }

        List<NoteOutputDTO> list = new ArrayList<NoteOutputDTO>( notes.size() );
        for ( Note note : notes ) {
            list.add( toDTO( note ) );
        }

        return list;
    }

    @Override
    public void edit(NoteInputDTO dto, Note noteToEdit) {
        if ( dto == null ) {
            return;
        }

        noteToEdit.setContent( dto.content() );
        noteToEdit.setTitle( dto.title() );
    }
}
