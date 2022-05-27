/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.test.gameService.services;

import com.test.gameService.domain.*;
import com.test.gameService.domain.framework.FreeSpinFeature;
import com.test.gameService.domain.framework.RandomNumberGame;
import com.test.gameService.domain.framework.WinFeature;
import com.test.gameService.models.Bet;

import java.util.*;

import com.test.gameService.models.Player;
import org.springframework.stereotype.Service;

/**
 *
 * @author himaruksilva
 */
@Service
public class GameServiceImpl implements GameService
{
    public final double MIN_BET_AMOUNT = 0;
    public final double MAX_BET_AMOUNT = 10;
    public final int WINNING_PERCENTAGE = 30;

    public final int BIG_WIN_PERCENTAGE =5;
    public final int MEDIUM_WIN_PERCENTAGE =25;

    public final int BIG_WIN = 50;
    public final int MEDIUM_WIN = 10;
    public final int SMALL_WIN = 3;


    private boolean isValidBet(Double amount){
        return amount.compareTo(MIN_BET_AMOUNT) >0 && amount.compareTo(MAX_BET_AMOUNT) <= 0;
    }

    private boolean validBalance(Double balance){
        return Double.compare(balance,0.0) > 0;
    }

    @Override
    public Optional<Game> play(Bet bet, Player player, Random rand){
        if(isValidBet(bet.amount) && validBalance(player.getBalance())){
            var features = List.of(new WinFeature(),new FreeSpinFeature());
            var game = new RandomNumberGame(player,bet,features,rand).play();
            return Optional.of(game);
        }
        return Optional.empty();
    }

    private int randomNumber(Random rand){
        return rand.nextInt(101);
    }
    
    
    public boolean isEligibleForFreeRound(Random rand)
    {
        return randomNumber(rand) <= 10;
    }

}
