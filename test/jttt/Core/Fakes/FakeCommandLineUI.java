package jttt.Core.Fakes;

import jttt.Core.Board.Board;
import jttt.Core.Board.Mark;
import jttt.Core.Game;
import jttt.Core.Players.Player;
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
    private boolean playAgain = false;
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

    public int requestNextPosition() {
        int nextMove = dummyInputs.remove(0);
        while (!validDummyPosition(nextMove)) {
            nextMove = dummyInputs.size() > 0 ? dummyInputs.remove(0) : -1;
        }
        userHasBeenAskedForNextPosition = true;
        return nextMove;
    }

    public boolean requestPlayAgain() {
        haveAskedUserToQuitGame = true;
        return playAgain;
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

    public boolean validPosition(int position) {
        return position > 0;
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
        if (requestPlayAgain()) {
            game = new Game(new Board(DEFAULT_DIMENSION), DEFAULT_GAME_TYPE, new PlayerFactory());
            start();
        }
    }

    private void createNewGame(int dimension, int gameType) {
        game = new Game(new Board(dimension), gameType, new PlayerFactory() );
    }

    private void playAllMoves() {
        while (!game.isGameOver()) {
            if (game.getNextPlayerType() == Player.Type.AI) {
                game.playAIMove();
            } else {
                game.playMove(requestNextPosition());
            }
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

    private boolean validDummyPosition(int nextMove) {
        return validate(nextMove, this::validPosition);//&& nextMove <= numberOfInputs;
    }
}
