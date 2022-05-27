package com.test.gameService.services;

import com.test.gameService.api.GameHistory;
import com.test.gameService.api.GameRoundHistory;
import com.test.gameService.domain.Game;
import com.test.gameService.models.Player;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class HistoryServiceImpl implements HistoryService{

    @Override
    public List<GameHistory> getPlayerHistory(Player player)
    {
        return mapHistory(player.games);
    }

    @Override
    public Optional<GameHistory> getPlayerRoundHistory(Player player, int round){
        if(player.games.size() >= round){
            return Optional.of(mapHistory(player.games.get(round)));
        }else{
            return Optional.empty();
        }
    }

    private List<GameHistory> mapHistory(List<Game> games) {
        return games.stream()
                .map(this::mapHistory).collect(Collectors.toList());
    }

    @Override
    public GameHistory mapHistory(Game game){
        return new GameHistory(game.betAmount,game.startingBalance,game.getEndingBalance(),game.getRounds().stream()
                .map(r -> new GameRoundHistory(r.type, r.getWinAmount()))
                .collect(Collectors.toList()));
    }
}
