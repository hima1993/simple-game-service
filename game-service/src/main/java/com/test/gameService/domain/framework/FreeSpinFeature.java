package com.test.gameService.domain.framework;

import com.test.gameService.domain.Game;
import com.test.gameService.domain.GameRoundType;
import com.test.gameService.domain.GameState;

import java.util.function.Supplier;

public class FreeSpinFeature implements IFeature {

    @Override
    public void play(Double betAmount, Game game, Supplier<Integer> randomNumber) {
        if (isEligibleForFreeRound(randomNumber.get())) {
            game.setNextRound(GameRoundType.FREE);
        } else {
            game.setNextRound(null);
            game.setState(GameState.END);
        }
    }

    public boolean isEligibleForFreeRound(int randomNumber) {
        return randomNumber <= 10;
    }
}
