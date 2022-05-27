package com.test.gameService.service;

import com.test.gameService.domain.Game;
import com.test.gameService.domain.GameRound;
import com.test.gameService.domain.GameRoundType;
import com.test.gameService.models.Player;
import com.test.gameService.services.HistoryServiceImpl;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

public class HistoryServiceTest {

    @Test
    public void shouldReturnHistory(){
        //given
        var historyServie = new HistoryServiceImpl();
        var player = new Player("dd","dd");
        Game g1 = new Game(10.0,50000.0);
        GameRound g1r1 = new GameRound(GameRoundType.BASIC);
        g1r1.setWinAmount(30.0);
        g1.addRound(g1r1);

        Game g2 = new Game(10.0,50000.0);
        GameRound g2r1 = new GameRound(GameRoundType.BASIC);
        g2r1.setWinAmount(40.0);
        g2.addRound(g2r1);
        GameRound g2r2 = new GameRound(GameRoundType.FREE);
        g2r2.setWinAmount(50.0);
        g2.addRound(g2r2);

        player.addGame(g1);
        player.addGame(g2);

        //when
        var history = historyServie.getPlayerHistory(player);

        //then
        assertEquals(history.size(),2);
        var h1 = history.get(0);
        assertEquals(h1.roundHistories.size(),1);
        assertEquals(h1.roundHistories.get(0).winAmount,30);
        assertEquals(h1.roundHistories.get(0).type,GameRoundType.BASIC);

        var h2 = history.get(1);
        assertEquals(h2.roundHistories.size(),2);
        assertEquals(h2.roundHistories.get(0).winAmount,40);
        assertEquals(h2.roundHistories.get(0).type,GameRoundType.BASIC);
        assertEquals(h2.roundHistories.get(1).winAmount,50);
        assertEquals(h2.roundHistories.get(1).type,GameRoundType.FREE);
    }
}
