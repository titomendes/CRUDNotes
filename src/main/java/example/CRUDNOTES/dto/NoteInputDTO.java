package example.CRUDNOTES.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record NoteInputDTO(

        @NotBlank(message = "Title is mandatory") 
        @Size(max = 50, message = "Title cannot exceed 50 characters")
        String title,
        
        @Size(max = 300, message = "Content cannot exceed 50 characters")
        String content)
        {}
