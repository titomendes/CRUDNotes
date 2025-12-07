package example.CRUDNOTES.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import example.CRUDNOTES.dto.NoteInputDTO;
import example.CRUDNOTES.dto.NoteOutputDTO;
import example.CRUDNOTES.entity.Note;

//(componentModel  = "spring") cria um bean apra depois se3r injetado
@Mapper(componentModel = "spring")
public interface NoteMapper {

    NoteOutputDTO toDTO(Note note);
    Note toEntity(NoteInputDTO dto);
    List<NoteOutputDTO> toDTOList(List<Note> notes);
    void edit(NoteInputDTO dto, @MappingTarget Note noteToEdit);



}