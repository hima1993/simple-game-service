package com.test.gameService.api;

public class GamePlaySuccessResponse {
    public Double accountBalance;
    public GameHistory round;

    public GamePlaySuccessResponse(Double accountBalance,GameHistory round){
        this.accountBalance = accountBalance;
        this.round = round;
    }
}
