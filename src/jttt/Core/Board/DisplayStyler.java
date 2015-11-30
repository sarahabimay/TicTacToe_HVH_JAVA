package jttt.Core.Board;

public class DisplayStyler {
    private final Board board;

    public DisplayStyler() {
       this.board = null;
    }

    public String displayBoard(Board board) {
        String output = "";
        for (int i = 0; i < board.boardSize(); i++) {
            output += convertCellToString(i, board);
            output += appendSpaces(i, board);
            output += appendNewLine(i, board);
        }
        return output;
    }

    private String convertCellToString(int index, Board board) {
        String cellForDisplay = board.findMarkAtIndex(index).markOrPositionForDisplay(index);
        return String.format("[%s]", cellForDisplay);
    }

    private String appendSpaces(int index, Board board) {
        String tabs = "";
        if (!isEndOfRow(index, board)) {
            tabs += generateSpaces(index, board);
        }
        return tabs;
    }

    private String generateSpaces(int index, Board board) {
        String tabs = "";
        String cellForDisplay = board.findMarkAtIndex(index).markOrPositionForDisplay(index);
        if (cellForDisplay.length()==1){
            tabs += "\t";
        }
        return tabs + "\t";
    }

    private String appendNewLine(int index, Board board) {
        String newLine = "";
        if (isEndOfRow(index, board)) {
            newLine += "\n";
        }
        return newLine;
    }

    private boolean isEndOfRow(int index, Board board) {
        return (index + 1) % calculateDimension(board) == 0;
    }

    private int calculateDimension(Board board) {
        return (int) Math.sqrt(board.boardSize());
    }
}
