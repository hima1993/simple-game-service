package com.test.gameService.models;

import com.test.gameService.domain.Game;

import java.util.ArrayList;
import java.util.List;

public class Player
{

    private static final Double DEFAULT_BALANCE = 5000.0;
    private String username;
    private String password;
    private final ArrayList<String> history;
    public final List<Game> games = new ArrayList<>();

    private Double balance = DEFAULT_BALANCE;

    public Player(String username, String password)
    {
        this.username = username;
        this.password = password;
        this.history = new ArrayList<>();
        this.history.add("User "+username+" Created");
    }
    public Double getBalance() {
        
        return balance;
    }
    public void addGame(Game game){
        
        games.add(game);
    }
    public void deductBetAmount(Player player, Double bet){
        player.updateBalance(-bet);
    }

    public String getUserName(){  
        return username;
    }

    public void setUserName(String userName){
        this.username = userName;
    }

    public String getPassword(){
        return password;
    }

    public void setPassword(String password){
        this.password = password;
    }
    
    public ArrayList<String> getHistory(){
        return history;
    }

    public void recordHistory(String history){
        this.history.add(history);
    }


    public void updateBalance(double balance) {
        this.balance += balance;
    }




    
}
