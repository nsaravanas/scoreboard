package com.saravana.service;

import com.saravana.common.GameType;
import com.saravana.model.Game;
import com.saravana.model.Player;
import com.saravana.model.Score;
import com.saravana.repository.GameRepository;
import com.saravana.repository.PlayerRepository;
import com.saravana.repository.ScoreRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class GameService {

    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private ScoreRepository scoreRepository;

    public Game createGame(GameType gameType) {
        final Game game = new Game();
        game.setId(ObjectId.get());
        game.setGameType(gameType);
        return gameRepository.save(game);
    }

    public Game getGame(ObjectId gameId) {
        return gameRepository.findById(gameId)
                .orElseThrow(() -> new IllegalArgumentException("Can't find a game with _id " + gameId));
    }

    public Game getGame(Integer gameId) {
        final Game probe = new Game();
        probe.setGameId(gameId);
        final ExampleMatcher matcher = ExampleMatcher.matching();
        return gameRepository.findOne(Example.of(probe, matcher))
                .orElseThrow(() -> new IllegalArgumentException("Can't find a probe with _id " + gameId));
    }

    public Player addPlayer(ObjectId gameId, Player player) {
        final Game game = getGame(gameId);
        player.setGameId(game.getId());
        player.setId(ObjectId.get());
        player.setAddedOn(new Date());
        return playerRepository.save(player);
    }

    public Player updatePlayer(ObjectId gameId, Player updatePlayer) {
        final Game game = getGame(gameId);
        final List<Player> players = getPlayers(game.getId());
        for (Player player : players) {
            if (player.getId().equals(updatePlayer.getId())) {
                player.setName(updatePlayer.getName());
                return playerRepository.save(player);
            }
        }
        throw new IllegalArgumentException("Can't find player to update " + updatePlayer.getId());
    }

    public void deletePlayer(ObjectId gameId, Player deletePlayer) {
        final Game game = getGame(gameId);
        final List<Player> players = getPlayers(game.getId());
        for (Player player : players) {
            if (player.getId().equals(deletePlayer.getId())) {
                playerRepository.deleteById(player.getId());
                return;
            }
        }
        throw new IllegalArgumentException("Can't find player to delete " + deletePlayer.getId());
    }

    public Player getPlayer(ObjectId gameId, ObjectId playerId) {
        final Game game = getGame(gameId);
        final List<Player> players = getPlayers(game.getId());
        for (Player player : players) {
            if (player.getId().equals(playerId)) {
                return player;
            }
        }
        throw new IllegalArgumentException("Can't find player " + playerId);
    }

    public List<Player> getPlayers(ObjectId gameId) {
        final Game game = getGame(gameId);
        List<Player> players = new ArrayList<>();
        playerRepository.findAllById(List.of(gameId)).forEach(players::add);
        return players;
    }

    public Score addScore(ObjectId gameId, Score score) {
        final Game game = getGame(gameId);
        score.setGameId(game.getId());
        score.setId(ObjectId.get());
        score.setAddedOn(new Date());
        return scoreRepository.save(score);
    }

    public Score updateScore(ObjectId gameId, Score updateScore) {
        final Game game = getGame(gameId);
        final List<Score> scores = getScores(game.getId());
        for (Score score : scores) {
            if (score.getId().equals(updateScore.getId())) {
                score.getScore().putAll(updateScore.getScore());
                return scoreRepository.save(score);
            }
        }
        throw new IllegalArgumentException("Can't find score to update " + updateScore.getId());
    }

    public void deleteScore(ObjectId gameId, Score deleteScore) {
        final Game game = getGame(gameId);
        final List<Score> scores = getScores(game.getId());
        for (Score score : scores) {
            if (score.getId().equals(deleteScore.getId())) {
                scoreRepository.deleteById(score.getId());
                return;
            }
        }
        throw new IllegalArgumentException("Can't find score to delete " + deleteScore.getId());
    }

    public Score getScore(ObjectId gameId, ObjectId scoreId) {
        final Game game = getGame(gameId);
        final List<Score> scores = getScores(game.getId());
        for (Score score : scores) {
            if (score.getId().equals(scoreId)) {
                return score;
            }
        }
        throw new IllegalArgumentException("Can't find score " + scoreId);
    }

    public List<Score> getScores(ObjectId gameId) {
        final Game game = getGame(gameId);
        List<Score> scores = new ArrayList<>();
        scoreRepository.findAllById(List.of(game.getId())).forEach(scores::add);
        return scores;
    }

}
