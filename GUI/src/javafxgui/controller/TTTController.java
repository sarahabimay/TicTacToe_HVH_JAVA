package javafxgui.controller;

import javafxgui.view.GUIView;
import jttt.core.game.GameMaker;
import jttt.core.game.Game;
import jttt.core.game.GameType;
import jttt.core.players.Player;

public class TTTController implements Controller {
    private GameMaker gameMaker;
    private GUIView guiView;
    private Game game;

    public TTTController(GUIView guiView, GameMaker gameMaker) {
        this.guiView = guiView;
        guiView.setController(this);
        this.gameMaker = gameMaker;
        this.game = null;
    }

    public Player getCurrentPlayer() {
        return game.getNextPlayer();
    }

    @Override
    public void presentGameOptions() {
        guiView.displayGameOptions();
    }

    @Override
    public void displayGameLayout() {
        guiView.displayGameLayoutComponent(game.getBoard());
    }

    @Override
    public void displayResult() {
        if (foundWinOrDraw()) {
            guiView.disableBoard(game.getBoard());
            guiView.displayResult(game.findWinner());
        }
    }

    @Override
    public void displayPlayAgain() {
        if (foundWinOrDraw()) {
            guiView.makePlayAgainVisible();
        }
    }

    @Override
    public void startGame(GameType gameType, int boardDimension) {
        initializeGame(gameType, boardDimension);
        playGame();
    }

    @Override
    public void initializeGame(GameType gameType, int boardDimension) {
        this.game = gameMaker.initializeGame(boardDimension, gameType.getNumericGameType(), null);
    }

    @Override
    public void playGame() {
        playMoveOnGameBoard();
        displayGameLayout();
        displayResult();
        displayPlayAgain();
    }

    @Override
    public void createNewGame() {
        presentGameOptions();
    }

    @Override
    public boolean foundWinOrDraw() {
        return game.isGameOver();
    }

    private void playMoveOnGameBoard() {
        game.playAllAvailableMoves();
    }
}
