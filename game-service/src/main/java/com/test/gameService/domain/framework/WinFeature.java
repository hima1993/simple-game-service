package com.test.gameService.domain.framework;

import com.test.gameService.domain.Game;
import com.test.gameService.domain.GameRound;
import com.test.gameService.domain.GameRoundType;

import java.util.function.Supplier;

public class WinFeature implements IFeature{

    public final int WINNING_PERCENTAGE = 30;
    public final int BIG_WIN_PERCENTAGE =5;
    public final int MEDIUM_WIN_PERCENTAGE =25;

    public final int BIG_WIN = 50;
    public final int MEDIUM_WIN = 10;
    public final int SMALL_WIN = 3;


    @Override
    public void play(Double betAmount, Game game, Supplier<Integer> ff)
    {
        GameRound round = new GameRound(game.getNextRound());
        round.setWinAmount(ff.get() <= WINNING_PERCENTAGE ? getWinningAmount(betAmount,ff.get()) : 0.0);
        game.addRound(round);
    }

    private Double getWinningAmount(Double betAmount,int randomNumber)
    {

        if(randomNumber <= BIG_WIN_PERCENTAGE)
        {
            return betAmount * BIG_WIN;
        }
        else if(randomNumber <= MEDIUM_WIN_PERCENTAGE)
        {
            return betAmount * MEDIUM_WIN;
        }
        else
        {
            return betAmount * SMALL_WIN;
        }
    }
}
