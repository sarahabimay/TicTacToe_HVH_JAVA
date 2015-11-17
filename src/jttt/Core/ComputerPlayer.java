package jttt.Core;

public class ComputerPlayer extends Player {
    private AIStrategyFactory strategyFactory;

    public ComputerPlayer(Counter counter, UserInterface userInterface) {
        super(counter, Type.AI , userInterface);
        strategyFactory = new AIStrategyFactory();
    }

    Board playTurn(Board board) {
        AIMoveStrategy strategy = strategyFactory.selectStrategyByBoardSize(board.boardSize());
        return board.playCounterInPosition(strategy.calculateNextMove(board, counter), counter);
    }

    Board playTurn(Board board, int newPosition) {
        return board;
    }
}
