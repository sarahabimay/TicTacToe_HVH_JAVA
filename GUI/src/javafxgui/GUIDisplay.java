package javafxgui;

import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import jttt.Core.Board.Board;
import jttt.Core.Board.Mark;

public class GUIDisplay {

    public static final String GAME_HEADER = "TIC TAC TOE GAME!";
    public static final String WINNER_ANNOUNCEMENT = "PLAYER %s HAS WON!";
    public static final String DRAW_ANNOUNCEMENT = "THE GAME IS A DRAW!";
    private final int POSITION_OFFSET = 1;
    private Controller controller;
    private BorderPane border;
    private Scene scene;
    private StackPane root;

    public GUIDisplay() {
        this.controller = null;
        this.root = new StackPane();
        this.scene = new Scene(root, 700, 675);
        scene.getStylesheets().add(Main.class.getResource("javafxgui.css").toExternalForm());
        this.border = new BorderPane();
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }

    public Node lookup(String id) {
        return scene.lookup(id);
    }

    public Scene displayGUI() {
        root.getChildren().add(generateBorderLayout(new Board(3)));
        return scene;
    }

    public GridPane displayBoard(Board board) {
        GridPane boardPane = createGameBoard(board);
        border.setCenter(boardPane);
        return boardPane;
    }

    public void disableBoard() {
        
    }

    public void displayResult(Mark winner) {
        Text resultTarget = (Text)lookup("#resultTarget");
        resultTarget.setText(createResultAnnouncement(winner));
    }

    public String createResultAnnouncement(Mark winner) {
        if (winner.isEmpty()){
            return announceDraw();
        }
        return announceWinner(winner);
    }

    public String announceDraw() {
        return DRAW_ANNOUNCEMENT;
    }

    public String announceWinner(Mark winner) {
        return String.format(WINNER_ANNOUNCEMENT, winner.toString());
    }

    public BorderPane generateBorderLayout(Board board) {
        border.setTop(titleHeader());
        border.setCenter(createGameBoard(board));
        border.setBottom(resultFooter());
        border.setId("borderPane");
        return border;
    }

    public HBox resultFooter() {
        HBox resultFooter = new HBox();
        resultFooter.setId("footer");
        resultFooter.getChildren().add(createGameResultsTarget());
        return resultFooter;
    }

    public void registerBoardButtonWithHandler(ClickableElement clickableElement) {
        registerElementWithHandler(clickableElement, new NewPlayerMoveEventHandler(controller));
    }

    public void registerElementWithHandler(ClickableElement clickableElement, ClickEventHandler eventHandler) {
        clickableElement.setOnAction(eventHandler);
    }

    private Text createGameResultsTarget() {
        Text resultTarget = new Text("RESULTS HERE");
        resultTarget.setId("resultTarget");
        return resultTarget;
    }

    private GridPane createGameBoard(Board board) {
        GridPane boardGrid = new GridPane();
        boardGrid.setId("gameBoard");
        boardGrid = generateBoardCells(board, boardGrid);
        return boardGrid;
    }

    private GridPane generateBoardCells(Board board, GridPane boardGrid) {
        int position = 0;
        for (int row = 0; row < board.getDimension(); row++) {
            for (int col = 0; col < board.getDimension(); col++) {
                Button cell = createButtonForBoard(board, position);
                boardGrid.add(cell, col, row);
                registerBoardButtonWithHandler(new JavaFXButton(cell));
                position++;
            }
        }
        return boardGrid;
    }

    private Button createButtonForBoard(Board board, int position) {
        return boardCell(position,
                cellForDisplay(board, position),
                shouldBeDisabled(board, position));
    }

    private boolean shouldBeDisabled(Board board, int position) {
        return board.findMarkAtIndex(position).isEmpty() ? false : true;
    }

    private String cellForDisplay(Board board, int position) {
        return cellContents(position, board.findMarkAtIndex(position));
    }

    private String cellContents(int position, Mark markAtIndex) {
        return markAtIndex.markOrPositionForDisplay(position);
    }

    private Button boardCell(int position, String text, boolean setDisabled) {
        Button cell = new Button(text);
        cell.setId(buttonID(position));
        cell.setDisable(setDisabled);
        return cell;
    }

    private String buttonID(int position) {
        return String.valueOf(position + POSITION_OFFSET);
    }

    public HBox titleHeader() {
        HBox titleBar = new HBox();
        titleBar.setId("titleBar");
        Text title = new Text(GAME_HEADER);
        title.setId("gameTitle");
        titleBar.getChildren().add(title);
        return titleBar;
    }
}
