package com.test.gameService.models;

import com.test.gameService.domain.GameMode;

public class Bet
{
    public final Double amount;
    public final String userId;
    public final GameMode mode;
    
    public Bet(Double amount, String userId,GameMode mode)
    {
        this.amount = amount;
        this.userId = userId;
        this.mode = mode;
    }


}
