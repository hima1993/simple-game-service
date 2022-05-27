package com.test.gameService.controllers;

import com.test.gameService.api.GameHistoryResponse;
import com.test.gameService.api.GamePlayErrorResponse;
import com.test.gameService.api.GamePlayResponse;
import com.test.gameService.api.GamePlaySuccessResponse;
import com.test.gameService.api.GetBalanceResponse;
import com.test.gameService.api.UserCreationErrorResponse;
import com.test.gameService.api.UserCreationResponse;
import com.test.gameService.domain.Game;
import com.test.gameService.models.Bet;
import com.test.gameService.models.Player;
import com.test.gameService.services.GameService;

import com.test.gameService.services.HistoryService;
import com.test.gameService.services.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping(path = "/api/v1/gameController")
public class GameController {
    
    @Autowired
    GameService gameService;
    @Autowired
    PlayerService playerService;
    @Autowired
    HistoryService historyService;


    @PostMapping("/balance/{playerId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<GetBalanceResponse> getBalance(@PathVariable String playerId)
    {
        var player = playerService.getPlayer(playerId);
        return player != null ? new ResponseEntity<>(new GetBalanceResponse(player.getBalance(),null), HttpStatus.OK)
                : new ResponseEntity<>(null, HttpStatus.NOT_FOUND);    
    }

    @PostMapping("/bet")
    public ResponseEntity<GamePlayResponse> bet(@RequestBody Bet bet) {
        Random random = new Random();
        var player = playerService.getPlayer(bet.userId);
        var game = gameService.play(bet, player, random);
        return game.map(value -> new ResponseEntity<>(successGamePlay(player, value), HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(new GamePlayResponse(Optional.empty()
                        , Optional.of(new GamePlayErrorResponse("Bet placement not allowed"))), HttpStatus.CONFLICT));

    }

    private GamePlayResponse successGamePlay(Player player, Game game) {
        var success = new GamePlaySuccessResponse(player.getBalance(), historyService.mapHistory(game));
        return new GamePlayResponse(Optional.of(success), Optional.empty());
    }

    @GetMapping("/history/player/{playerId}")
    public ResponseEntity<GameHistoryResponse> historyByPlayerId(@PathVariable String playerId) {
        var player = playerService.getPlayer(playerId);
        return player != null ? new ResponseEntity<>(new GameHistoryResponse(historyService.getPlayerHistory(player)), HttpStatus.OK)
                : new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @GetMapping("/history/player/{playerId}/round/{round}")
    public ResponseEntity<GameHistoryResponse> historyByRoundId(@PathVariable String playerId, @PathVariable int round) {
        var player = playerService.getPlayer(playerId);
        if (player != null) {
            return historyService.getPlayerRoundHistory(player, round).map(val -> new ResponseEntity<>(new GameHistoryResponse(List.of(val)), HttpStatus.OK))
                    .orElseGet(() -> new ResponseEntity<>(null, HttpStatus.NOT_FOUND));
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }
    
    @PostMapping("/createuser/player/{playerId}/password/{password}")
    public ResponseEntity<UserCreationResponse> createUser(@PathVariable String playerId, @PathVariable String password){
        var player = playerService.getPlayer(playerId);
        return playerService.createPlayer(playerId, password).map(val -> new ResponseEntity<>(new UserCreationResponse(Optional.of(val),null), HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(new UserCreationResponse(Optional.empty(), Optional.of(new UserCreationErrorResponse("User Already Sign Up"))), HttpStatus.CONFLICT));
    }
}
