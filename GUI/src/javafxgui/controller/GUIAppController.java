package javafxgui.controller;

import javafxgui.javafxcomponents.GUIBoardDisplayer;
import javafxgui.javafxcomponents.GUIView;
import javafxgui.players.GUIHumanPlayer;
import javafxgui.players.GUIPlayerFactory;
import jttt.core.UIAppControls;
import jttt.core.UIPresenter;
import jttt.core.board.Board;
import jttt.core.game.Game;
import jttt.core.game.GameMaker;
import jttt.core.game.GameType;
import jttt.core.players.Player;

public class GUIAppController implements UIAppControls, UIPresenter{
    private GameMaker gameMaker;
    private GUIView guiView;
    private Game game;

    public GUIAppController(GameMaker gameMaker, GUIView guiView) {
        this.guiView = guiView;
        guiView.setController(this);
        this.gameMaker = gameMaker;
        this.game = null;
    }

    public Player getCurrentPlayer() {
        return game.getNextPlayer();
    }

    @Override
    public void displayGameLayout(Board board) {
        guiView.displayGameLayoutComponent(board);
    }

    @Override
    public void displayResult() {
        if (foundWinOrDraw()) {
            guiView.disableBoard(game.getBoard());
            guiView.displayResult(game.findWinner());
        }
    }

    @Override
    public int displayPlayAgainOption() {
        if (foundWinOrDraw()) {
            guiView.makePlayAgainVisible();
        }
        return 0; //Potential LSP violation as return value isn't used in GUI
    }

    public void registerPlayerMove(String displayPositionId) {
        GUIHumanPlayer guiHuman = (GUIHumanPlayer) getCurrentPlayer();
        guiHuman.setNextUserMove(displayPositionId);
    }

    @Override
    public void displayGameOptions() {
        guiView.displayGameOptions();
    }

    @Override
    public void startGame() {
        playMoveOnGameBoard();
        displayGameLayout(game.getBoard());
        displayResult();
        displayPlayAgainOption();
    }

    public void startGame(GameType gameType, int boardDimension){
        initializeGame(gameType, boardDimension);
        startGame();
    }

    public void initializeGame(GameType gameType, int boardDimension) {
        this.game = gameMaker.initializeGame(
                boardDimension,
                gameType.getNumericGameType(),
                new GUIPlayerFactory(),
                new GUIBoardDisplayer());
    }

    public boolean foundWinOrDraw() {
        return game.isGameOver();
    }

    private void playMoveOnGameBoard() {
        game.playAllAvailableMoves();
    }
}
