package com.test.gameService.services;

import com.test.gameService.models.Player;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class PlayerServiceImpl implements PlayerService{
    private final Map<String, Player> players = new HashMap<>();

    @Override
    public Player getPlayer(String userId){
        return players.get(userId);
    }

    @Override
    public Optional<Player> createPlayer(String username, String password) {
        
        Player player = new Player(username, password);
        Player newPlayer = players.put(username, player);
        if(newPlayer == null)
        {
            return Optional.of(player);
        }
        else
        {
            return Optional.empty();
        }  
    }
}
