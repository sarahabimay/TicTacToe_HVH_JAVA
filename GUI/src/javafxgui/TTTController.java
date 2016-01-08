package javafxgui;

import javafxgui.view.GUIView;
import jttt.Core.Board.Board;
import jttt.Core.Game;
import jttt.Core.GameType;
import jttt.Core.Players.Player;
import jttt.Core.Players.PlayerFactory;

public class TTTController implements Controller {
    private GUIView guiView;
    private Game game;

    public TTTController(GUIView guiView, Game game) {
        this.guiView = guiView;
        guiView.setController(this);
        this.game = game;
    }

    public TTTController(GUIView guiView) {
        this.guiView = guiView;
        guiView.setController(this);
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
        this.game = new Game(new Board(boardDimension), gameType.getNumericGameType(), new PlayerFactory());
    }

    @Override
    public void playGame() {
        while (!game.isGameOver() && game.getNextPlayer().isReady()) {
            playMoveOnGameBoard();
            displayGameLayout();
        }
        displayResult();
        displayPlayAgain();
    }

    @Override
    public void createNewGame() {
        game = null;
        presentGameOptions();
    }

    @Override
    public boolean foundWinOrDraw() {
        return game != null && game.isGameOver();
    }

    private void playMoveOnGameBoard() {
        game.playCurrentPlayerMove();
    }
}
