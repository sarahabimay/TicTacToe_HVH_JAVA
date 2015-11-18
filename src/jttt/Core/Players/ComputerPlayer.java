package jttt.Core.Players;

import jttt.Core.*;
import jttt.Core.Strategy.AIMoveStrategy;
import jttt.Core.Strategy.AIStrategyFactory;
import jttt.UI.UserInterface;

public class ComputerPlayer extends Player {
    private AIStrategyFactory strategyFactory;

    public ComputerPlayer(Mark mark, UserInterface userInterface) {
        super(mark, Type.AI , userInterface);
        strategyFactory = new AIStrategyFactory();
    }

    public Board playTurn(Board board) {
        AIMoveStrategy strategy = strategyFactory.selectStrategyByBoardSize(board.boardSize());
        return board.playCounterInPosition(strategy.calculateNextMove(board, mark), mark);
    }

    public Board playTurn(Board board, int newPosition) {
        return board;
    }
}
