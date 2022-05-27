package com.test.gameService.api;

import java.util.Optional;

public class GamePlayResponse {
    public final Optional<GamePlaySuccessResponse> success;
    public final Optional<GamePlayErrorResponse> error;

    public GamePlayResponse(Optional<GamePlaySuccessResponse> success, Optional<GamePlayErrorResponse> error){
        this.success = success;
        this.error = error;
    }
}
