package com.test.gameService.services;

import com.test.gameService.api.GameHistory;
import com.test.gameService.domain.Game;
import com.test.gameService.models.Player;

import java.util.List;
import java.util.Optional;

public interface HistoryService {
    List<GameHistory> getPlayerHistory(Player player);
    Optional<GameHistory> getPlayerRoundHistory(Player player, int round);
    GameHistory mapHistory(Game game);
}
