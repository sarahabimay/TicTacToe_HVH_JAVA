package jttt.console;

import jttt.core.board.Board;
import jttt.core.board.Mark;

public class ConsoleDisplayStyler {
    private String ANSI_RESET = "\u001B[0m";
    private String ANSI_BLUE = "\u001B[34m";
    private String ANSI_RED = "\u001B[31m";
    private String ANSI_GREEN = "\u001B[32m";


    public String createBoardForDisplay(Board board) {
        String output = "";
        for (int i = 0; i < board.boardSize(); i++) {
            output += formatMarkForDisplay(i, board);
            output += appendSpaces(i, board);
            output += appendDivider(i, board);
            output += appendNewLine(i, board);
            output += addBottomLine(i, board);
        }
        output += "\n";
        return output;
    }

    String formatMarkForDisplay(int index, Board board) {
        String cellAsString = markAsString(board.findMarkAtIndex(index), index);
        cellAsString = setDisplayColor(cellAsString);
        return addCellFormatting(cellAsString);
    }

    String addBottomLine(int index, Board board) {
        String underLine = "";
        if (isEndOfRow(index, board) && !isLastBoardElement(index, board)) {
            for (int i = 1; i < calculateDimension(board); i++) {
                underLine += ANSI_GREEN + "_________" + ANSI_RESET;
            }
            underLine += "\n\n";
        }
        return underLine;
    }

    String appendSpaces(int index, Board board) {
        String tabs = "";
        if (!isEndOfRow(index, board)) {
            tabs += generateSpaces(index, board.findMarkAtIndex(index));
        }
        return tabs;
    }

    String appendDivider(int index, Board board) {
        if (!isEndOfRow(index, board)) {
            return ANSI_GREEN + "|  " + ANSI_RESET;
        }
        return "";
    }

    String appendNewLine(int index, Board board) {
        String newLine = "";
        if (isEndOfRow(index, board)) {
            newLine += "\n";
        }
        return newLine;
    }

    String setDisplayColor(String cellValue) {
        if (cellValue == Mark.X.toString()) {
            return ANSI_RED + cellValue + ANSI_RESET;
        } else if (cellValue == Mark.O.toString()) {
            return ANSI_BLUE + cellValue + ANSI_RESET;
        }
        return cellValue;
    }

    private String markAsString(Mark mark, int index) {
        return mark.markOrPositionForDisplay(index);
    }

    private String addCellFormatting(String cellValue) {
        return String.format("[%s]", cellValue);
    }

    private String generateSpaces(int index, Mark mark) {
        String tabs = "";
        if (isASingleCharacter(index, mark)) {
            tabs += " ";
        }
        return tabs + " ";
    }

    private boolean isASingleCharacter(int index, Mark mark) {
        return markAsString(mark, index).length() == 1;
    }

    private boolean isLastBoardElement(int index, Board board) {
        return index == board.boardSize() - 1;
    }

    private boolean isEndOfRow(int index, Board board) {
        return (index + 1) % calculateDimension(board) == 0;
    }

    private int calculateDimension(Board board) {
        return (int) Math.sqrt(board.boardSize());
    }
}
