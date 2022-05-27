package com.test.gameService.domain.framework;

import com.test.gameService.domain.*;
import com.test.gameService.models.Bet;
import com.test.gameService.models.Player;

import java.util.List;
import java.util.Random;
import java.util.function.Supplier;

import static com.test.gameService.domain.GameRoundType.BASIC;
import static com.test.gameService.domain.GameState.PLAYING;

public class RandomNumberGame {
    private final List<IFeature> features;
    private final Player player;
    private final Bet bet;
    private Random rand;
    private final Supplier<Integer> randomNumber = () -> rand.nextInt(101);


    public RandomNumberGame(Player player, Bet bet, List<IFeature> features, Random random){
        this.player = player;
        this.bet = bet;
        this.features = features;
        this.rand = random;
    }
    public Game play(){

        var turn = new Game(bet.amount,player.getBalance());
        while (turn.getState() == PLAYING){
            if(turn.getNextRound() == BASIC && bet.mode == GameMode.REAL_MONEY){
                player.updateBalance(-bet.amount);
            }
            features.forEach(feature -> {
                feature.play(bet.amount,turn,randomNumber);
            });

        }
        player.updateBalance(turn.getTotalWin());
        turn.setEndingBalance(player.getBalance());
        player.addGame(turn);
        return turn;
    }

}
