package com.test.gameService.services;

import com.test.gameService.domain.Game;
import com.test.gameService.models.Bet;
import com.test.gameService.models.Player;

import java.util.Optional;
import java.util.Random;

public interface GameService {
    Optional<Game> play(Bet bet, Player player, Random rand);

}
