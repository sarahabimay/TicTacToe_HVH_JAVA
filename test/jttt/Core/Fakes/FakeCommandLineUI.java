package jttt.Core.Fakes;

import jttt.Core.Board.Board;
import jttt.Core.Board.Mark;
import jttt.Core.Game;
import jttt.Core.Players.PlayerFactory;
import jttt.UI.UserInterface;

import java.util.ArrayList;
import java.util.List;
import java.util.function.IntPredicate;

public class FakeCommandLineUI implements UserInterface {
    private int DEFAULT_DIMENSION = 3;
    private int DEFAULT_GAME_TYPE = 3;
    private Game game;
    private List<Integer> dummyInputs = new ArrayList<>();
    private int playerType = 1;
    private final int doNotPlayAgainOption = 2;
    private Mark winner = Mark.EMPTY;
    private boolean userHasBeenAskedForDimension = false;
    private boolean userHasBeenAskedForNextPosition = false;
    private boolean haveDisplayedBoardToUser = false;
    private boolean haveDisplayedResultToUser = false;
    private boolean haveAskedUserToQuitGame = false;
    private boolean haveAskedUserForGameType = false;
    private boolean haveValidatedGameType = false;
    private int dummyDimension = 0;

    public FakeCommandLineUI() {
        this.game = new Game(new Board(DEFAULT_DIMENSION), DEFAULT_GAME_TYPE, new PlayerFactory());
    }

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
        userHasBeenAskedForNextPosition = true;
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

    public int requestBoardDimension() {
        userHasBeenAskedForDimension = true;
        return dummyDimension;
    }

    public int requestGameType() {
        haveAskedUserForGameType = true;
        if (!validate(playerType, this::validGameType)) {
            playerType = -1;
        }
        return playerType;
    }

    public int requestPlayAgain() {
        haveAskedUserToQuitGame = true;
        return doNotPlayAgainOption;
    }

    public String displayBoard() {
        String output = boardForDisplay(game.getBoard());
        haveDisplayedBoardToUser = true;
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

    public boolean validate(int choiceFromInput, IntPredicate isValidChoice) {
        return isValidChoice.test(choiceFromInput);
    }

    public boolean validateDimension(int dimension) {
        return dimension >= 3;
    }

    public boolean validReplayChoice(int instruction) {
        return 0 < instruction && instruction < 3;
    }

    public boolean validGameType(int choice) {
        haveValidatedGameType = true;
        return choice == 1 || choice == 2 || choice == 3;
    }

    public void displayResult(Mark winner) {
        if (!winner.isEmpty()) {
            this.winner = winner;
        }
        haveDisplayedResultToUser = true;
    }

    private void playAgain() {
        if (userWantsToPlay(requestPlayAgain())) {
            game = new Game(new Board(DEFAULT_DIMENSION), DEFAULT_GAME_TYPE, new PlayerFactory());
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
    private enum BinaryChoice {
        YES(1),
        NO(2);

        private int choiceOption;

        BinaryChoice(int choiceOption) {
            this.choiceOption = choiceOption;
        }

        public boolean equalsChoice(int choice) {
            return choiceOption == choice;
        }
    }
}
