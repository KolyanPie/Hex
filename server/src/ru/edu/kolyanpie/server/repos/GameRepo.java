package ru.edu.kolyanpie.server.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.edu.kolyanpie.server.model.Game;

public interface GameRepo extends JpaRepository<Game, String> {
    Game findByUuid(String uuid);
}
