package example.CRUDNOTES.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import example.CRUDNOTES.entity.User;




public interface UserRepository extends JpaRepository<User,Long> {

    boolean existsByEmail(String email);

    Optional<User> findByEmail(String email);(String email);
}


