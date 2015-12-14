package javafxgui;

import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import jttt.Core.Board.Board;
import jttt.Core.Board.Mark;

public class BoardDisplay {
    private final int POSITION_OFFSET = 1;
    private Board board;

    public BoardDisplay(Board board) {
        this.board = board;
    }

    public GridPane getBoardForDisplay() {
        return createGameBoard(board);
    }

    public GridPane getDisabledBoard() {
        GridPane boardPane = createGameBoard(board);
        for (int i = 0; i < boardPane.getChildren().size(); i++) {
            boardPane.getChildren().get(i).setDisable(true);
        }
        return boardPane;
    }

    private GridPane createGameBoard(Board board) {
        GridPane boardGrid = createGridPane("gameBoard");
        boardGrid = generateBoardCells(board, boardGrid);
        return boardGrid;
    }

    private GridPane createGridPane(String id) {
        GridPane boardGrid = new GridPane();
        boardGrid.setId(id);
        return boardGrid;
    }

    private GridPane generateBoardCells(Board board, GridPane boardGrid) {
        int position = 0;
        for (int row = 0; row < board.getDimension(); row++) {
            for (int col = 0; col < board.getDimension(); col++) {
                Button cell = createButtonForBoard(board, position);
                boardGrid.add(cell, col, row);
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
        Button cell = createButton(text, buttonID(position));
        cell.setDisable(setDisabled);
        return cell;
    }

    private String buttonID(int position) {
        return String.valueOf(position + POSITION_OFFSET);
    }

    private Button createButton(String label, String id) {
        Button playAgain = new Button(label);
        playAgain.setId(id);
        return playAgain;
    }
}
