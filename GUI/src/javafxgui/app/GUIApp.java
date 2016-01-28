package javafxgui.app;

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

public class GUIApp implements UIAppControls, UIPresenter{
    private GameMaker gameMaker;
    private GUIView guiView;
    private Game game;

    public GUIApp(GameMaker gameMaker, GUIView guiView) {
        this.guiView = guiView;
        guiView.setGuiApp(this);
        this.gameMaker = gameMaker;
        this.game = null;
    }

    public GUIHumanPlayer getCurrentPlayer() {
        return (GUIHumanPlayer)game.getNextPlayer();
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
    public void displayGameOptions() {
        guiView.displayGameOptions();
    }

    @Override
    public void startGame() {
        playMoveOnGameBoard();
        displayResult();
        displayPlayAgainOption();
    }

    public void displayPlayAgainOption() {
        if (foundWinOrDraw()) {
            guiView.makePlayAgainVisible();
        }
    }

    public void registerPlayerMove(String displayPositionId) {
        getCurrentPlayer().setNextUserMove(displayPositionId);
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
                new GUIBoardDisplayer(this));
    }

    public boolean foundWinOrDraw() {
        return game.isGameOver();
    }

    private void playMoveOnGameBoard() {
        game.playAllAvailableMoves();
    }
}
