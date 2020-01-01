package com.saravana.controller;

import com.saravana.common.GameType;
import com.saravana.model.Game;
import com.saravana.model.Player;
import com.saravana.model.Score;
import com.saravana.service.GameService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/game")
public class GameController {

    @Autowired
    private GameService gameService;

    @GetMapping(path = "/{gameId}")
    public Game getGame(@PathVariable String gameId) {
        return gameService.getGame(gameId);
    }

    @PostMapping(path = "/create")
    @ResponseBody
    public Game createGame(@RequestParam("type") GameType gameType) {
        return gameService.createGame(gameType);
    }

    @GetMapping(path = "/{gameId}/players")
    public List<Player> getPlayers(@PathVariable ObjectId gameId) {
        return gameService.getPlayers(gameId);
    }

    @GetMapping(path = "/{gameId}/player/{playerId}")
    public Player getPlayer(@PathVariable ObjectId gameId, @PathVariable ObjectId playerId) {
        return gameService.getPlayer(gameId, playerId);
    }

    @PostMapping(path = "/{gameId}/player/add")
    public Player addPlayer(@PathVariable ObjectId gameId, @RequestBody Player player) {
        return gameService.addPlayer(gameId, player);
    }

    @PutMapping(path = "/{gameId}/player/update")
    public Player updatePlayer(@PathVariable ObjectId gameId, @RequestBody Player player) {
        return gameService.updatePlayer(gameId, player);
    }

    @DeleteMapping(path = "/{gameId}/player/delete")
    public void deletePlayer(@PathVariable ObjectId gameId, @RequestBody Player player) {
        gameService.deletePlayer(gameId, player);
    }

    @GetMapping(path = "/{gameId}/scores")
    public List<Score> getScores(@PathVariable ObjectId gameId) {
        return gameService.getScores(gameId);
    }

    @GetMapping(path = "/{gameId}/score/{scoreId}")
    public Score getScore(@PathVariable ObjectId gameId, @PathVariable ObjectId scoreId) {
        return gameService.getScore(gameId, scoreId);
    }

    @PostMapping(path = "/{gameId}/score/add")
    public Score addScore(@PathVariable ObjectId gameId, @RequestBody Score score) {
        return gameService.addScore(gameId, score);
    }

    @PutMapping(path = "/{gameId}/score/update")
    public Score updateScore(@PathVariable ObjectId gameId, @RequestBody Score score) {
        return gameService.updateScore(gameId, score);
    }

    @DeleteMapping(path = "/{gameId}/score/delete")
    public void deleteScore(@PathVariable ObjectId gameId, @RequestBody Score score) {
        gameService.deleteScore(gameId, score);
    }

}
