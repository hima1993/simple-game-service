package com.test.gameService.api;

import com.test.gameService.domain.GameRoundType;

public class GameRoundHistory {
    public final GameRoundType type;
    public final Double winAmount;

    public GameRoundHistory(GameRoundType type, Double winAmount){
        this.type = type;
        this.winAmount = winAmount;
    }

}
