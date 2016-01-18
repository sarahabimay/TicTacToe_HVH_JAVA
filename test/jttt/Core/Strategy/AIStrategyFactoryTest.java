package jttt.core.strategy;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class AIStrategyFactoryTest {
    @Test
    public void getStrategyFor3By3() {
        AIStrategyFactory sf = new AIStrategyFactory();
        int boardSize = 9;
        assertEquals(AlphaBetaStrategy.class, sf.selectStrategyByBoardSize(boardSize).getClass());
    }

    @Test
    public void getStrategyFor4By4() {
        AIStrategyFactory sf = new AIStrategyFactory();
        int boardSize = 16;
        assertEquals(FourByFourAlphaBetaStrategy.class, sf.selectStrategyByBoardSize(boardSize).getClass());
    }

    @Test
    public void defaultStrategyIfNotFound() {
        AIStrategyFactory sf = new AIStrategyFactory();
        int boardSize = 2;
        assertEquals(AlphaBetaStrategy.class, sf.selectStrategyByBoardSize(boardSize).getClass());
    }
}
