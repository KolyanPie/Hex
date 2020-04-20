package ru.edu.kolyanpie.server.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.edu.kolyanpie.server.model.User;

public interface UserRepo extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
