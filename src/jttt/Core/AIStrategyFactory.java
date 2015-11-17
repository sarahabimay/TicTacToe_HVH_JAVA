package jttt.Core;

import java.util.HashMap;

public class AIStrategyFactory {

    private final HashMap<Integer, AIMoveStrategy> strategies = new HashMap<>();
    private final int DEFAULT_STRATEGY = 0;

    public AIStrategyFactory() {
        strategies.put(DEFAULT_STRATEGY, new AlphaBetaStrategy());
        strategies.put(9, new AlphaBetaStrategy());
        strategies.put(16, new FourByFourAlphaBetaStrategy());
    }

    public AIMoveStrategy selectStrategyByBoardSize(int boardSize) {
        if (strategies.get(boardSize) == null ){
            return strategies.get(DEFAULT_STRATEGY);
        }
        return strategies.get(boardSize);
    }
}
