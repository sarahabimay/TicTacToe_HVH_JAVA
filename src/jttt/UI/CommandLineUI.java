package jttt.UI;

import jttt.core.game.GameMaker;
import jttt.core.board.Board;
import jttt.core.board.Mark;
import jttt.core.game.Game;

import java.io.*;
import java.util.function.IntPredicate;

public class CommandLineUI implements UserInterface {
    private final String ANSI_CLEAR = "\033[H\033[2J";
    public static final String GREETING = "Do you want to play a game of TIC TAC TOE? Yes(1) or No(2) : \n";
    public static final String GAME_TYPE_REQUEST = "Human vs Human(1) or Human vs Computer(2) or Computer vs Human(3)?:\n";
    public static final String DIMENSION_REQUEST = "Please provide the dimensions of the board:\n";
    public static final String POSITION_REQUEST = "Please enter the position number for your next move:\n";
    public static final String DRAW_ANNOUNCE = "The game is a draw!\n";
    public static final String WINNER_ANNOUNCE = "We have a Winner! Player: %s\n";
    public static final String REPLAY_REQUEST = "Do you want to play again? Yes(1) or No(2) :\n";
    private GameMaker gameMaker;
    private Game game;
    private BufferedReader readStream;
    private Writer writeStream;

    public CommandLineUI(GameMaker gameMaker, InputStream inputStream, Writer writer) {
        this.readStream = new BufferedReader(new InputStreamReader(inputStream));
        this.writeStream = writer;
        this.gameMaker = gameMaker;
        this.game = null;
    }

    public void start() {
        clearDisplay();
        boolean playGame = userWantsToPlay(displayGreetingRequest());
        while (playGame) {
            createNewGameFromOptions(requestGameType(), requestBoardDimension());
            playAllMoves();
            displayResult(game.findWinner());
            playGame = playAgain();
        }
    }

    public int displayGreetingRequest() {
        return request(GREETING, this::validateContinueChoice);
    }

    public int requestBoardDimension() {
        return request(DIMENSION_REQUEST, this::validateDimension);
    }

    public int requestGameType() {
        return request(GAME_TYPE_REQUEST, this::validGameType);
    }

    public int requestNextPosition(Board board) {
        int inputValue = -1;
        while (!validBoardPosition(inputValue, board)) {
            displayMessage(POSITION_REQUEST);
            inputValue = readInput();
        }
        clearDisplay();
        return inputValue;
    }

    public int requestPlayAgain() {
        return request(REPLAY_REQUEST, this::validReplayChoice);
    }

    @Override
    public void displayBoardToUser(String boardForDisplay) {
        clearDisplay();
        displayMessage(boardForDisplay);
    }

    public void displayResult(Mark winner) {
        if (winner.isEmpty()) {
            announceDraw();
        } else {
            announceWinner(winner);
        }
    }

    public boolean playAgain() {
        if (userWantsToPlay(requestPlayAgain())) {
            clearDisplay();
            return true;
        }
        return false;
    }

    public boolean validate(int choiceFromInput, IntPredicate isValidChoice) {
        return isValidChoice.test(choiceFromInput);
    }

    public boolean validGameType(int choice) {
        return choice == 1 || choice == 2 || choice == 3;
    }

    public boolean validateDimension(int dimension) {
        return dimension >= 3;
    }

    public boolean validBoardPosition(int oneIndexedPosition, Board board) {
        return (0 < oneIndexedPosition && oneIndexedPosition <= board.boardSize()) &&
                !board.cellIsOccupied(oneIndexedPosition - 1);
    }

    public boolean validReplayChoice(int instruction) {
        return BinaryChoice.YES.equalsChoice(instruction) || BinaryChoice.NO.equalsChoice(instruction);
    }

    public boolean validateContinueChoice(int playOrQuit) {
        return BinaryChoice.YES.equalsChoice(playOrQuit) || BinaryChoice.NO.equalsChoice(playOrQuit);
    }

    @Override
    public void createNewGameFromOptions(int gameType, int dimension) {
        game = gameMaker.initializeGame(dimension, gameType, this);
    }

    private void playAllMoves() {
        game.playAllAvailableMoves();
    }

    private void displayMessage(String messageToDisplay) {
        try {
            writeStream.write(messageToDisplay);
            writeStream.flush();
        } catch (IOException e) {
            System.out.println(String.format("IO Exception: %s", e.toString()));
        }
    }

    private int request(String outputMessage, IntPredicate isValidChoice) {
        int inputValue = -1;
        while (!validate(inputValue, isValidChoice)) {
            displayMessage(outputMessage);
            inputValue = readInput();
        }
        clearDisplay();
        return inputValue;
    }

    private void resetGame() {
        int DEFAULT_DIMENSION = 3;
        int DEFAULT_GAME_TYPE = 1;
        game = gameMaker.initializeGame(DEFAULT_DIMENSION, DEFAULT_GAME_TYPE, this);
    }

    private boolean userWantsToPlay(int choice) {
        return BinaryChoice.YES.equalsChoice(choice);
    }

    private void announceWinner(Mark winner) {
        displayMessage(String.format(WINNER_ANNOUNCE, winner.toString()));
    }

    private void announceDraw() {
        displayMessage(DRAW_ANNOUNCE);
    }

    private void clearDisplay() {
        displayMessage("\n" + ANSI_CLEAR + "\n");
    }

    private int readInput() {
        try {
            return Integer.parseInt(readStream.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NumberFormatException e) {
            return 0;
        }
        return 0;
    }
}
