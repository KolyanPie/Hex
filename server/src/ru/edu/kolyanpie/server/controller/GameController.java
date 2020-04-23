package ru.edu.kolyanpie.server.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import ru.edu.kolyanpie.server.model.User;
import ru.edu.kolyanpie.server.service.GameService;

@RestController
@RequestMapping("/game")
public class GameController {
    private final GameService gameService;

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @PostMapping("/blue")
    public String createGameAsBlue(@AuthenticationPrincipal User user) {
        return gameService.createGame(user, true);
    }

    @PostMapping("/red")
    public String createGameAsRed(@AuthenticationPrincipal User user) {
        return gameService.createGame(user, false);
    }

    @GetMapping("/join/{uuid}")
    public String joinGame(@AuthenticationPrincipal User user, @PathVariable String uuid) {
        if (gameService.joinGame(user, uuid)) {
            return gameService.getHistory(uuid);
        } else {
            return "false";
        }
    }

    @GetMapping("/{uuid}")
    public String hetHistory(@PathVariable String uuid) {
        return gameService.getHistory(uuid);
    }

    @GetMapping("/turn/{uuid}")
    public boolean isMyTurn(@AuthenticationPrincipal User user, @PathVariable String uuid) {
        return gameService.isUserTurn(user, uuid);
    }

}
