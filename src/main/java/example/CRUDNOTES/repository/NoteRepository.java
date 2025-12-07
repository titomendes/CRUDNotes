package example.CRUDNOTES.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import example.CRUDNOTES.entity.Note;

public interface NoteRepository extends JpaRepository<Note,Long> {

    List<Note>findByTitle(String title);
}
