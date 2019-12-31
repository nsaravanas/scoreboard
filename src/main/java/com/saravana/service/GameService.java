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
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

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
        game.setGameId(findNextGameId());
        return gameRepository.save(game);
    }

    public Game getLastGame() {
        return gameRepository.findTopByOrderByIdDesc().orElse(null);
    }

    public int findNextGameId() {
        Game game = getLastGame();
        return (game == null || game.getGameId() == null) ? 0 : game.getGameId() + 1;
    }

    public Game getGame(Object gameId) {
        Objects.nonNull(gameId);
        Optional<Game> optionalGame;
        if (gameId instanceof ObjectId) {
            optionalGame = gameRepository.findById((ObjectId) gameId);
        } else if (gameId instanceof String) {
            if (((String) gameId).length() == 24) {
                optionalGame = gameRepository.findById(new ObjectId((String) gameId));
            } else {
                Integer gameNo = Integer.parseInt((String) gameId);
                optionalGame = gameRepository.findByGameId(gameNo);
            }
        } else {
            throw new IllegalArgumentException("Unknown gameId type " + gameId);
        }

        return optionalGame.orElseThrow(() -> new IllegalArgumentException("Can't find a game with _id " + gameId));
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
        return playerRepository.findAllByGameId(gameId);
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
        return scoreRepository.findAllByGameId(gameId);
    }

}
