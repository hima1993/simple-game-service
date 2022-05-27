package com.test.gameService.service;

import com.test.gameService.domain.GameMode;
import com.test.gameService.models.Bet;
import com.test.gameService.models.Player;
import com.test.gameService.services.GameServiceImpl;
import java.util.Optional;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Random;

import static org.junit.Assert.*;

public class GameServiceTest {

    @Test
    public void shouldLoose(){
        //given
        GameServiceImpl impl = new GameServiceImpl();
        Bet bet = new Bet(5.0,"dd", GameMode.REAL_MONEY);
        Player player = new Player("dd","dd");
        Random random = Mockito.mock(Random.class);
        Mockito.when(random.nextInt(101)).thenReturn(50);
        //when
        var turn = impl.play(bet,player,random).get();

        //then
        assertEquals(turn.getRounds().size(),1);
        assertEquals(turn.getTotalWin(),Double.valueOf(0));
        assertEquals(turn.startingBalance,Double.valueOf(5000));
        assertEquals(turn.getEndingBalance(),Double.valueOf(4995));
    }
    @Test
    public void shouldLooseThreeTimesAndHaveCorrectBalance(){
        //given
        GameServiceImpl impl = new GameServiceImpl();
        Bet bet = new Bet(5.0,"dd",GameMode.REAL_MONEY);
        Player player = new Player("dd","dd");
        Random random = Mockito.mock(Random.class);
        Mockito.when(random.nextInt(101)).thenReturn(50);
        //when
        var turn = impl.play(bet,player,random).get();

        //then
        assertEquals(turn.getRounds().size(),1);
        assertEquals(turn.getTotalWin(),Double.valueOf(0));
        assertEquals(turn.startingBalance,Double.valueOf(5000));
        assertEquals(turn.getEndingBalance(),Double.valueOf(4995));

        //when
        turn = impl.play(bet,player,random).get();

        //then
        assertEquals(turn.getRounds().size(),1);
        assertEquals(turn.getTotalWin(),Double.valueOf(0));
        assertEquals(turn.startingBalance,Double.valueOf(4995));
        assertEquals(turn.getEndingBalance(),Double.valueOf(4990));

        //when
        turn = impl.play(bet,player,random).get();

        //then
        assertEquals(turn.getRounds().size(),1);
        assertEquals(turn.getTotalWin(),Double.valueOf(0));
        assertEquals(turn.startingBalance,Double.valueOf(4990));
        assertEquals(turn.getEndingBalance(),Double.valueOf(4985));
    }

    @Test
    public void shouldWinSmall(){
        //given
        GameServiceImpl impl = new GameServiceImpl();
        Bet bet = new Bet(5.0,"dd",GameMode.REAL_MONEY);
        Player player = new Player("dd","dd");
        Random random = Mockito.mock(Random.class);
        Mockito.when(random.nextInt(101)).thenReturn(10).thenReturn(50).thenReturn(20);
        //when
        var turn = impl.play(bet,player,random).get();

        //then
        assertEquals(turn.getRounds().size(),1);
        assertEquals(turn.getTotalWin(),Double.valueOf(15));
    }
    @Test
    public void shouldWinMedium(){
        //given
        GameServiceImpl impl = new GameServiceImpl();
        Bet bet = new Bet(5.0,"dd",GameMode.REAL_MONEY);
        Random random = Mockito.mock(Random.class);
        Player player = new Player("dd","dd");

        Mockito.when(random.nextInt(101)).thenReturn(10).thenReturn(20).thenReturn(20);
        //when
        var turn = impl.play(bet,player,random).get();

        //then
        assertEquals(turn.getRounds().size(),1);
        assertEquals(turn.getTotalWin(),Double.valueOf(50));
    }
    @Test
    public void shouldWinBig(){
        //given
        GameServiceImpl impl = new GameServiceImpl();
        Bet bet = new Bet(5.0,"dd",GameMode.REAL_MONEY);
        Random random = Mockito.mock(Random.class);
        Player player = new Player("dd","dd");
        Mockito.when(random.nextInt(101)).thenReturn(10).thenReturn(2).thenReturn(20);
        //when
        var turn = impl.play(bet,player,random).get();

        //then
        assertEquals(turn.getRounds().size(),1);
        assertEquals(turn.getTotalWin(),Double.valueOf(250));
    }
    @Test
    public void shouldWinFreeSpin(){
        //given
        GameServiceImpl impl = new GameServiceImpl();
        Bet bet = new Bet(9.0,"dd",GameMode.REAL_MONEY);
        Random random = Mockito.mock(Random.class);
        Player player = new Player("dd","dd");

        Mockito.when(random.nextInt(101)).thenReturn(10).thenReturn(10).thenReturn(10).thenReturn(40);
        //when
        var turn = impl.play(bet,player,random).get();

        //then
        assertEquals(turn.getRounds().size(),2);
        assertEquals(turn.totalFreeRounds(),1);
    }

    @Test
    public void shouldWin65(){
        //given
        GameServiceImpl impl = new GameServiceImpl();
        Bet bet = new Bet(5.0,"dd",GameMode.REAL_MONEY);
        Random random = Mockito.mock(Random.class);
        Player player = new Player("dd","dd");

        Mockito.when(random.nextInt(101))
                .thenReturn(10) // win
                .thenReturn(10) // win medium
                .thenReturn(10) // win freespins
                .thenReturn(30) // win small
                .thenReturn(50); // end
        //when
        var turn = impl.play(bet,player,random).get();

        //then
        assertEquals(turn.getRounds().size(),2);
        assertEquals(turn.totalFreeRounds(),1);
        assertEquals(turn.getTotalWin(),Double.valueOf(65));
        assertEquals(player.getBalance(),Double.valueOf(5060));
    }

    @Test
    public void shouldWin(){
        //given
        GameServiceImpl impl = new GameServiceImpl();
        Bet bet = new Bet(5.0,"dd",GameMode.REAL_MONEY);
        Random random = Mockito.mock(Random.class);
        Player player = new Player("dd","dd");

        Mockito.when(random.nextInt(101))
                .thenReturn(10) // win
                .thenReturn(10) // medium win
                .thenReturn(10) // free spin 1
                .thenReturn(30) // win
                .thenReturn(50) // free spin small win
                .thenReturn(8) // free spin 2
                .thenReturn(10) // win
                .thenReturn(5) // big win free spin
                .thenReturn(50); // game over
        //when
        var turn = impl.play(bet,player,random).get();

        //then
        assertEquals(turn.getRounds().size(),3);
        assertEquals(turn.totalFreeRounds(),2);
        assertEquals(turn.getTotalWin(),Double.valueOf(315));
        assertEquals(player.getBalance(),Double.valueOf(5310));
    }

    @Test
    public void shouldLooseAndWinFreeRoundAndThenWinBig(){
        //given
        GameServiceImpl impl = new GameServiceImpl();
        Bet bet = new Bet(5.0,"dd",GameMode.REAL_MONEY);
        Random random = Mockito.mock(Random.class);
        Player player = new Player("dd","dd");

        Mockito.when(random.nextInt(101))
                .thenReturn(40) // no win
                .thenReturn(10) // win free round
                .thenReturn(30) // win
                .thenReturn(5) // win big
                .thenReturn(40); // game over
        //when
        var turn = impl.play(bet,player,random).get();

        //then
        assertEquals(turn.getRounds().size(),2);
        assertEquals(turn.totalFreeRounds(),1);
        assertEquals(turn.getTotalWin(),Double.valueOf(250));
        assertEquals(player.getBalance(),Double.valueOf(5245));
    }
    
    @Test
    public void shouldLooseAndWinFreeRoundAndThenWinMedium(){
        
        //given
        GameServiceImpl impl = new GameServiceImpl();
        Bet bet = new Bet(5.0,"dd",GameMode.REAL_MONEY);
        Random random = Mockito.mock(Random.class);
        Player player = new Player("dd","dd");

        //when
        var turn = impl.play(bet,player,random).get();

        //then
        assertEquals(turn.getRounds().size(),2);
        assertEquals(turn.totalFreeRounds(),1);
        assertEquals(turn.getTotalWin(),Double.valueOf(250));
        assertEquals(player.getBalance(),Double.valueOf(5245));
    }
    
    @Test
    public void shouldPlaceBetMoreThanTenAndgetInvalid(){
        
        //given
        GameServiceImpl impl = new GameServiceImpl();
        Bet bet = new Bet(20.0,"dd",GameMode.REAL_MONEY);
        Random random = Mockito.mock(Random.class);
        Player player = new Player("dd","dd");

        //when
        var turn = impl.play(bet,player,random).get();

        //then
        assertEquals(turn.getTotalWin(),Double.valueOf(0));
        assertEquals(turn.getRounds(),0);
        assertEquals(turn,Optional.empty());
        
    }
    
    @Test
    public void shouldPlaceBetZeroAndgetInvalid(){
        
        //given
        GameServiceImpl impl = new GameServiceImpl();
        Bet bet = new Bet(0.0,"dd",GameMode.REAL_MONEY);
        Random random = Mockito.mock(Random.class);
        Player player = new Player("dd","dd");

        Mockito.when(random.nextInt(101))
                .thenReturn(40) // no win
                .thenReturn(10) // win free round
                .thenReturn(30) // win
                .thenReturn(22) // win medium
                .thenReturn(40); // game over
        //when
        var turn = impl.play(bet,player,random).get();

        //then
        assertEquals(turn.getTotalWin(),Double.valueOf(0));
        assertEquals(turn.getRounds(),0);
        assertEquals(turn,Optional.empty());
        
    }
}
