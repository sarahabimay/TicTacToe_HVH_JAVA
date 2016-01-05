package jttt.Core.Players;

import jttt.Core.Board.Board;
import jttt.Core.Board.Mark;
import jttt.Core.Strategy.AIMoveStrategy;
import jttt.Core.Strategy.AIStrategyFactory;

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
