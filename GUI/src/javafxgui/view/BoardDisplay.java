package javafxgui.view;

import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import jttt.Core.Board.Board;
import jttt.Core.Board.Mark;

public class BoardDisplay {
    private final int POSITION_OFFSET = 1;

    public GridPane getBoardForDisplay(Board board) {
        return createGameBoard(board);
    }

    public GridPane getDisabledBoard(Board board) {
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
                Button cell = createButtonForBoard(position, board);
                boardGrid.add(cell, col, row);
                position++;
            }
        }
        return boardGrid;
    }

    private Button createButtonForBoard(int position, Board board) {
        return boardCell(position,
                cellForDisplay(position, board),
                shouldBeDisabled(position, board));
    }

    private Button boardCell(int position, String text, boolean setDisabled) {
        Button cell = createButton(text, buttonID(position));
        cell.setDisable(setDisabled);
        return cell;
    }

    private Button createButton(String label, String id) {
        Button positionButton = new Button(label);
        positionButton.setId(id);
        return positionButton;
    }

    private String cellForDisplay(int position, Board board) {
        return cellContents(position, board.findMarkAtIndex(position));
    }

    private boolean shouldBeDisabled(int position, Board board) {
        return board.findMarkAtIndex(position).isEmpty() ? false : true;
    }

    private String cellContents(int position, Mark markAtIndex) {
        return markAtIndex.markOrPositionForDisplay(position);
    }

    private String buttonID(int position) {
        return String.valueOf(position + POSITION_OFFSET);
    }
}
