package com.test.gameService.domain;

public class GameRound {
    
    public final GameRoundType type;
    private Double winAmount;

    public GameRound(GameRoundType type){
        this.type = type;
    }

    public Double getWinAmount() {
        return winAmount;
    }

    public void setWinAmount(Double winAmount) {
        this.winAmount = winAmount;
    }
}
