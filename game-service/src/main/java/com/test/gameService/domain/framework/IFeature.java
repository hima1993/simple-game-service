package com.test.gameService.domain.framework;

import com.test.gameService.domain.Game;
import java.util.function.Supplier;

public interface IFeature {
    void play(Double betAmount, Game game, Supplier<Integer> ff);
}
