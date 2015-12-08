package jttt.UI;

import jttt.Core.Board.Board;
import jttt.Core.Board.DisplayStyler;
import jttt.Core.Game;
import jttt.Core.Board.Mark;
import jttt.Core.Players.Player;
import jttt.Core.Players.PlayerFactory;

import java.io.*;
import java.util.function.IntPredicate;

public class CommandLineUI implements UserInterface {
    private String ANSI_CLEAR = "\033[H\033[2J";
    public String GREETING = "Do you want to play a game of TIC TAC TOE? Yes(1) or No(2) : \n";
    public String GAME_TYPE_REQUEST = "Human vs Human(1) or Human vs Computer(2) or Computer vs Human(3)?:\n";
    public String DIMENSION_REQUEST = "Please provide the dimensions of the board:\n";
    public String POSITION_REQUEST = "Please enter the position number for your next move:\n";
    public String DRAW_ANNOUNCE = "The game is a draw!\n";
    public String WINNER_ANNOUNCE = "We have a Winner! Player: %s\n";
    public String REPLAY_REQUEST = "Do you want to play again? Yes(1) or No(2) :\n";
    private Game game;
    private DisplayStyler styler;
    private BufferedReader readStream;
    private Writer writeStream;

    public CommandLineUI(Game game, DisplayStyler boardStyle, InputStream inputStream, Writer writer) {
        this.readStream = new BufferedReader(new InputStreamReader(inputStream));
        this.writeStream = writer;
        this.game = game;
        this.styler = boardStyle;
    }

    public void start() {
        clearDisplay();
        boolean playGame = continueToPlayGame(displayGreetingRequest());
        while (playGame) {
            createNewGame(requestGameType(), requestBoardDimension());
            displayBoard();
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

    public int requestNextPosition() {
        return request(POSITION_REQUEST, this::validPosition);
    }

    public boolean requestPlayAgain() {
        int instruction = request(REPLAY_REQUEST, this::validReplayChoice);
        return doPlayAgain(instruction);
    }

    public String displayBoard() {
        clearDisplay();
        String output = styler.formatBoardForDisplay(game.getBoard());
        displayToOutput(output);
        return output;
    }

    public void displayResult(Mark winner) {
        if (winner.isEmpty()) {
            announceDraw();
        } else {
            announceWinner(winner);
        }
    }

    public boolean playAgain() {
        if (requestPlayAgain()) {
            clearDisplay();
            resetGame();
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

    public boolean validPosition(int position) {
        return position > 0;
    }

    public boolean validReplayChoice(int instruction) {
        return BinaryChoice.YES.equalsChoice(instruction) || BinaryChoice.NO.equalsChoice(instruction);
    }

    public boolean validateContinueChoice(int playOrQuit) {
        return BinaryChoice.YES.equalsChoice(playOrQuit) || BinaryChoice.NO.equalsChoice(playOrQuit);
    }

    public void createNewGame(int gameType, int dimension) {
        game = new Game(
                new Board(dimension),
                gameType,
                new PlayerFactory());
    }

    private void playAllMoves() {
        while (!game.isGameOver()) {
            if (nextPlayerIsAI()) {
                game.playAIMove();
            } else {
                game.playMove(requestNextPosition());
            }
            displayBoard();
        }
    }

    private void displayToOutput(String messageToDisplay) {
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
            displayToOutput(outputMessage);
            inputValue = readInput();
        }
        clearDisplay();
        return inputValue;
    }

    private void resetGame() {
        int DEFAULT_DIMENSION = 3;
        int DEFAULT_GAME_TYPE = 1;
        game = new Game(new Board(DEFAULT_DIMENSION), DEFAULT_GAME_TYPE, new PlayerFactory());
    }

    private boolean continueToPlayGame(int choice) {
        return BinaryChoice.YES.equalsChoice(choice);
    }

    private boolean nextPlayerIsAI() {
        return game.getNextPlayerType() == Player.Type.AI;
    }

    private void announceWinner(Mark winner) {
        displayToOutput(String.format(WINNER_ANNOUNCE, winner.toString()));
    }

    private void announceDraw() {
        displayToOutput(DRAW_ANNOUNCE);
    }

    private boolean doPlayAgain(int instruction) {
        return BinaryChoice.YES.equalsChoice(instruction);
    }

    private void clearDisplay() {
        displayToOutput("\n" + ANSI_CLEAR + "\n");
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
