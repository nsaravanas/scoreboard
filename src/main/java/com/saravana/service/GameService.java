package com.saravana.service;

import org.bson.types.ObjectId;
import org.saravana.common.GameType;
import org.saravana.model.Game;
import org.saravana.model.Player;
import org.saravana.model.Score;
import org.saravana.model.ScoreCard;
import org.saravana.repository.GameRepository;
import org.saravana.repository.PlayerRepository;
import org.saravana.repository.ScoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

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
        game.set_id(ObjectId.get());
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

    public List<Player> getPlayers(ObjectId gameId) {
        Iterable<Player> it = playerRepository.findAllById(Arrays.asList(gameId));
        List<Player> players = new ArrayList<>();
        it.forEach(players::add);
        return players;
    }

    public List<Player> addPlayer(ObjectId gameId, List<Player> players) {
        final Game game = getGame(gameId);
        Objects.nonNull(players);
        final List<Player> dbPlayers = getPlayers(game.get_id());
        for (Player db : dbPlayers) {
            for (Player rq : players) {
                if (db.get_id().equals(rq.get_id())) {
                    throw new IllegalArgumentException(db.getName() + " name already exists");
                }
            }
        }
        return playerRepository.saveAll(players);
    }

    public Player editPlayer(ObjectId gameId, Player player) {
        final Game game = getGame(gameId);
        Objects.nonNull(player);
        List<Player> existingPlayers = getPlayers(game.get_id());
        for (Player db : existingPlayers) {
            if (db.get_id().equals(player.get_id())) {
                db.setName(player.getName());
            }
        }
        return playerRepository.save(player);
    }

    public void deletePlayer(ObjectId gameId, Player player) {
        final Game game = getGame(gameId);
        Objects.nonNull(player);
        List<Player> players = getPlayers(game.get_id());
        for (Player p : players) {
            if (p.get_id().equals(player.get_id())) {
                playerRepository.deleteById(player.get_id());
            }
        }
    }

    public Score addScore(ObjectId gameId, ScoreCard scoreCard) {
        final Game game = getGame(gameId);
        Score score = getScore(game.get_id());
        if (score == null) {
            score = new Score();
            score.setGameId(game.get_id());
            score.setScore(new ArrayList<>());
        }
        if (score.getScore() == null) {
            score.setScore(new ArrayList<>());
        }
        score.getScore().add(scoreCard);
        return scoreRepository.save(score);
    }

    public Score editScore(ObjectId gameId, ScoreCard scoreCard) {
        Objects.nonNull(gameId);
        Objects.nonNull(scoreCard);
        final Game game = getGame(gameId);
        final Score score = getScore(game.get_id());
        for (ScoreCard dbScoreCard : score.getScore()) {
            if (dbScoreCard.getScoreCardId().equals(scoreCard.getScoreCardId())) {
                dbScoreCard.getScoreMap().putAll(scoreCard.getScoreMap());
            }
        }
        return scoreRepository.save(score);
    }

    public void deleteScore(ObjectId gameId, ScoreCard scoreCard) {
        Objects.nonNull(gameId);
        Objects.nonNull(scoreCard);
        final Game game = getGame(gameId);
        final Score score = getScore(game.get_id());
        for (ScoreCard sc : score.getScore()) {
            if (sc.getScoreCardId().equals(scoreCard.getScoreCardId())) {
                playerRepository.deleteById(sc.getScoreCardId());
            }
        }
    }

    public Score getScore(ObjectId gameId) {
        final Game game = getGame(gameId);
        return scoreRepository.findById(game.get_id()).orElse(null);
    }

}
