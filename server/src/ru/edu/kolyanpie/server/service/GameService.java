package ru.edu.kolyanpie.server.service;

import org.springframework.stereotype.Service;
import ru.edu.kolyanpie.server.model.Game;
import ru.edu.kolyanpie.server.model.User;
import ru.edu.kolyanpie.server.repos.GameRepo;

import java.util.UUID;

@Service
public class GameService {
    private final GameRepo gameRepo;

    public GameService(GameRepo gameRepo) {
        this.gameRepo = gameRepo;
    }

    public String createGame(User user, boolean isBlue) {
        Game game = new Game();
        String uuid = UUID.randomUUID().toString();

        game.setUuid(uuid);
        game.setHistory("");
        if (isBlue) {
            game.setBlue(user);
        } else {
            game.setRed(user);
        }
        return uuid;
    }

}
