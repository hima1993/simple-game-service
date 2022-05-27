package com.test.gameService.api;

import com.test.gameService.models.Player;
import java.util.Optional;

public class UserCreationResponse {
    
    public final Optional<Player> success;
    public final Optional<UserCreationErrorResponse> error;
    
    public UserCreationResponse(Optional<Player> success, Optional<UserCreationErrorResponse> error)
    {
        this.success = success;
        this.error = error;
    }
    
}
