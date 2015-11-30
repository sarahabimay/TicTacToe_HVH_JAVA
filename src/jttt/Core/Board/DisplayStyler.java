package jttt.Core.Board;

import static java.util.stream.Collectors.toList;

public class DisplayStyler {
    private String ANSI_BLUE = "\u001B[34m";
    ;
    private String ANSI_RESET = "\u001B[0m";
    private String ANSI_RED = "\u001B[31m";

    public String displayBoard(Board board) {
        String output = "";
        for (int i = 0; i < board.boardSize(); i++) {
            output += convertCellToString(i, board);
            output += appendSpaces(i, board);
            output += appendNewLine(i, board);
        }
        return output;
    }

    public String colourBoard(Board board) {
        board.getCells().stream().map(mark -> addColor(mark.toString())).collect(toList());
        return "";
    }

    public String colourCell(String mark) {
        return addColor(mark);
    }

    private String addColor(String mark) {
        if (mark == Mark.X.toString()) {
            return "\n" + ANSI_RED + mark + ANSI_RESET + "\n";
        } else if (mark == Mark.O.toString()) {
            return "\n" + ANSI_BLUE + mark + ANSI_RESET + "\n";
        }
        return "\n" + mark;
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
        if (cellForDisplay.length() == 1) {
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
