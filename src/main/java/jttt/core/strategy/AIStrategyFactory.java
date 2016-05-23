package jttt.core.strategy;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class AIStrategyFactory {

    private final Map<Integer, AIMoveStrategy> strategies = new HashMap<>();
    private final int DEFAULT_STRATEGY = 0;
    private final int BOARD_SIZE_3X3 = 9;
    private final int BOARD_SIZE_4X4 = 16;

    public AIStrategyFactory() {
        strategies.put(DEFAULT_STRATEGY, new AlphaBetaStrategy());
        strategies.put(BOARD_SIZE_3X3, new AlphaBetaStrategy());
        strategies.put(BOARD_SIZE_4X4, new FourByFourAlphaBetaStrategy());
    }

    public AIMoveStrategy selectStrategyByBoardSize(int boardSize) {
        AIMoveStrategy aiMoveStrategy = strategies.get(boardSize);
        if (Optional.ofNullable(aiMoveStrategy).isPresent()) {
            return strategies.get(boardSize);
        }
        return strategies.get(DEFAULT_STRATEGY);
    }
}
