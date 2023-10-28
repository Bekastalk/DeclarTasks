package myProject.repository;

import myProject.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Optional;

@RepositoryRestResource(path = "users")
public interface UserRepository extends JpaRepository<User, Long> {
   Optional<User> getUserByEmail(String email);

   boolean existsByEmail(String email);

}