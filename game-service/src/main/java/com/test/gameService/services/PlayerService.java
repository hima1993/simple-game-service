package com.test.gameService.services;

import com.test.gameService.models.Player;
import java.util.Optional;

public interface PlayerService {
     Player getPlayer(String userId);
     
     Optional<Player> createPlayer(String username, String password);
}
