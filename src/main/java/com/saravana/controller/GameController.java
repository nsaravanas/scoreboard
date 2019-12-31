package com.saravana.controller;

import org.saravana.common.GameType;
import org.saravana.model.Game;
import org.saravana.model.Player;
import org.saravana.model.Score;
import org.saravana.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("/games")
public class GameController {

    @Autowired
    private GameService gameService;

    @PostMapping(path = "/create")
    public Game createGame(@RequestParam GameType gameType) {
        return gameService.createGame(gameType);
    }

    @GetMapping(path = "/{:gameId}")
    public Game getGame(@PathVariable int gameId) {
        return gameService.getGame(gameId);
    }

    @PostMapping(path = "/{:gameId}/player/add")
    public Game addPlayer(@PathVariable int gameId, @RequestBody List<Player> players) {
        return gameService.addPlayer(gameId, players);
    }

    @PutMapping(path = "/{:gameId}/player/edit")
    public Game editPlayer(@PathVariable int gameId, @RequestBody Player player) {
        return gameService.editPlayer(gameId, player);
    }

    @DeleteMapping(path = "/{:gameId}/player/delete")
    public Game deletePlayer(@PathVariable int gameId, @RequestBody Player player) {
        return gameService.deletePlayer(gameId, player);
    }

    @PostMapping(path = "/{:gameId}/score/add")
    public Game addScore(@PathVariable int gameId, @RequestBody Score score) {
        return gameService.addScore(gameId, score);
    }

    @PutMapping(path = "/{:gameId}/score/edit")
    public Game updateScore(@PathVariable int gameId, @RequestBody Score score) {
        return gameService.editScore(gameId, score);
    }

    @DeleteMapping(path = "/{:gameId}/score/delete")
    public Game deleteScore(@PathVariable int gameId, @RequestBody Score score) {
        return gameService.deleteScore(gameId, score);
    }

}
