package jttt.Core.Strategy;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class AIStrategyFactoryTest {
    @Test
    public void defaultStrategyIfNotFound(){
        AIStrategyFactory sf = new AIStrategyFactory();
        assertEquals(AlphaBetaStrategy.class, sf.selectStrategyByBoardSize(2).getClass());
    }
}
