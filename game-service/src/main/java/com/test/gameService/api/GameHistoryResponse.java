package com.test.gameService.api;

import java.util.List;

public class GameHistoryResponse {
    
    public final List<GameHistory> history;

    public GameHistoryResponse(List<GameHistory> history){
        this.history = history;
    }
}
