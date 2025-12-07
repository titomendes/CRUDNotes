package example.CRUDNOTES.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import example.CRUDNOTES.dto.AuthRequest;
import example.CRUDNOTES.dto.AuthResponse;
import example.CRUDNOTES.dto.UserInputDTO;
import example.CRUDNOTES.dto.UserOutputDTO;
import example.CRUDNOTES.entity.User;

@Mapper (componentModel = "spring")
public interface UserMapper {

    UserOutputDTO toDTO(User user);
    AuthResponse toDTOAuth(User user);
    User toEntity(UserInputDTO userRegisterDto);
    User toEntityaAuth(  AuthRequest authRequestDTO);
    void edit(UserInputDTO dto, @MappingTarget User userToEdit);
}