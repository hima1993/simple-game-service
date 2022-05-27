package com.test.gameService.domain;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private Double totalWin = 0.0;
    private List<GameRound> rounds = new ArrayList<>();
    private GameState state = GameState.PLAYING;
    private GameRoundType nextRound = GameRoundType.BASIC;
    public final Double betAmount;
    public final Double startingBalance;
    private Double endingBalance;

    public Game(Double betAmount,Double startingBalance){
        this.betAmount = betAmount;
        this.startingBalance = startingBalance;
    }

    public Double getEndingBalance() {
        return endingBalance;
    }

    public void setEndingBalance(Double endingBalance) {
        this.endingBalance = endingBalance;
    }

    public List<GameRound> getRounds() {
        return rounds;
    }

    public GameRoundType getNextRound() {
        return nextRound;
    }

    public void setNextRound(GameRoundType nextRound) {
        this.nextRound = nextRound;
    }

    public void setState(GameState state) {
        this.state = state;
    }

    public GameState getState() {
        return state;
    }

    public void addRound(GameRound round) {
        this.rounds.add(round);
        this.totalWin += round.getWinAmount();
    }

    public Double getTotalWin() {
        return totalWin;
    }

    public long totalFreeRounds(){
        return this.rounds.stream().filter( r -> r.type == GameRoundType.FREE).count();
    }
}
