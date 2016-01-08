package jttt.Core.UI;

import jttt.Core.Board.Board;
import jttt.Core.Board.Mark;
import jttt.Core.Game;
import jttt.Core.GameType;
import jttt.Core.Players.PlayerFactory;
import jttt.UI.BinaryChoice;
import jttt.UI.UserInterface;

import java.util.ArrayList;
import java.util.List;
import java.util.function.IntPredicate;

public class FakeCommandLineUI implements UserInterface {
    private final int DEFAULT_DIMENSION = 3;
    private Game game;
    private List<Integer> dummyInputs;
    private int playerType;
    private int dummyDimension;

    public FakeCommandLineUI() {
        this.game = new Game(new Board(DEFAULT_DIMENSION), GameType.HVH.getNumericGameType(), new PlayerFactory());
        this.playerType = -1;
        this.dummyDimension = 0;
        this.dummyInputs = new ArrayList<>();
    }

    @Override
    public void start() {
        createNewGame(requestBoardDimension(), requestGameType());
        playAllMoves();
        displayResult(game.findWinner());
        playAgain();
    }

    @Override
    public int requestNextPosition(Board board) {
        int nextMove = dummyInputs.remove(0);
        while (!validBoardPosition(nextMove, board)) {
            nextMove = dummyInputs.size() > 0 ? dummyInputs.remove(0) : -1;
        }
        return nextMove;
    }

    @Override
    public boolean validBoardPosition(int oneIndexedPosition, Board board) {
        return (0 < oneIndexedPosition && oneIndexedPosition <= board.boardSize()) &&
                !board.cellIsOccupied(oneIndexedPosition - 1);
    }

    @Override
    public int displayGreetingRequest() {
        return 1;
    }

    @Override
    public boolean validateContinueChoice(int playOrQuit) {
        return false;
    }

    @Override
    public int requestBoardDimension() {
        return dummyDimension;
    }

    @Override
    public int requestGameType() {
        if (!validate(playerType, this::validGameType)) {
            playerType = -1;
        }
        return playerType;
    }

    @Override
    public int requestPlayAgain() {
        return BinaryChoice.NO.getChoiceOption();
    }

    @Override
    public boolean validate(int choiceFromInput, IntPredicate isValidChoice) {
        return isValidChoice.test(choiceFromInput);
    }

    @Override
    public boolean validateDimension(int dimension) {
        return dimension >= 3;
    }

    @Override
    public boolean validReplayChoice(int instruction) {
        return 0 < instruction && instruction < 3;
    }

    @Override
    public boolean validGameType(int choice) {
        return choice == 1 || choice == 2 || choice == 3;
    }

    @Override
    public void displayResult(Mark winner) {
    }

    public String displayBoard() {
        String output = boardForDisplay(game.getBoard());
        return output;
    }

    public void setGameType(int gameType) {
        this.playerType = gameType;
    }

    public void addDummyDimension(int dimension) {
        this.dummyDimension = dimension;
    }

    public void addDummyHumanMoves(List<Integer> inputs) {
        dummyInputs = inputs;
    }

    private void playAgain() {
        if (userWantsToPlay(requestPlayAgain())) {
            game = new Game(new Board(DEFAULT_DIMENSION), GameType.HVH.getNumericGameType(), new PlayerFactory());
            start();
        }
    }

    private void createNewGame(int dimension, int gameType) {
        game = new Game(new Board(dimension), gameType, new PlayerFactory());
    }

    private void playAllMoves() {
        while (!game.isGameOver()) {
            game.playCurrentPlayerMove();
            displayBoard();
        }
    }

    private String boardForDisplay(Board board) {
        String output = "";
        for (int i = 0; i < board.boardSize(); i++) {
            output += convertRowToString(i, board.findMarkAtIndex(i), board);
        }
        return output;
    }

    private String convertRowToString(int index, Mark cellValue, Board board) {
        String cellForDisplay = cellValue.markOrPositionForDisplay(index);
        String output = String.format("[%s]", cellForDisplay);
        if (isEndOfRow(index, board)) {
            output += "\n";
        }
        return output;
    }

    private boolean isEndOfRow(int index, Board board) {
        return (index + 1) % calculateDimension(board) == 0;
    }

    private int calculateDimension(Board board) {
        return (int) Math.sqrt(board.boardSize());
    }

    private boolean userWantsToPlay(int choice) {
        return BinaryChoice.YES.equalsChoice(choice);
    }
}
