package com.test.gameService.api;

import java.util.List;

public class GameHistory {
    public Double startingBalance;
    public Double endingBalance;
    public Double bet;
    public final List<GameRoundHistory> roundHistories;
    
    public GameHistory(Double bet,Double startingBalance,Double endingBalance,List<GameRoundHistory> roundHistories){
        this.bet = bet;
        this.startingBalance = startingBalance;
        this.endingBalance = endingBalance;
        this.roundHistories = roundHistories;
    }
}
