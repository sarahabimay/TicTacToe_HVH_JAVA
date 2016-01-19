package jttt.core.players;

import jttt.core.board.Board;
import jttt.core.board.Mark;
import jttt.core.strategy.AIMoveStrategy;
import jttt.core.strategy.AIStrategyFactory;

public class ComputerPlayer extends Player {
    private AIStrategyFactory strategyFactory;

    public ComputerPlayer(Mark mark) {
        super(mark, Type.AI);
        strategyFactory = new AIStrategyFactory();
    }

    public int getNextPosition(Board board) {
        AIMoveStrategy strategy = strategyFactory.selectStrategyByBoardSize(board.boardSize());
        return strategy.calculateNextMove(board, mark);
    }
}
