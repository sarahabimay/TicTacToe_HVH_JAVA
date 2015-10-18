import java.util.ArrayList;
import java.util.List;

public class Board {
    public final int startIndex = 0;
    public final int POSITIVE_OFFSET = 1;
    public final int NEGATIVE_OFFSET = -1;
    private int dimension;
    private List<String> cells;

    public Board(int dimension) {
        this.dimension = dimension;
        this.cells = new ArrayList<>(generateEmptyCells(dimension));
    }

    public Board(List<String> cells) {
        this.dimension = (int) Math.round(Math.sqrt(cells.size()));
        this.cells = cells;
    }

    public int size() {
        return cells.size();
    }

    private int boardSize() {
        return dimension * dimension;
    }

    public void setCellValue(int cellNumber, String counter) {
        cells.set(cellNumber, counter);
    }

    private String cellValue(int startIndex) {
        return cells.get(startIndex);
    }

    private List<String> generateEmptyCells(int dimension) {
        List<String> initialCells = new ArrayList<>(boardSize());
        for (int i = 0; i < boardSize(); i++) {
            int cellNumber = i + POSITIVE_OFFSET;
            initialCells.add(String.valueOf(cellNumber));
        }
        return initialCells;
    }

    public String boardAsString() {
        String output = "";
        for (int i = 0; i < cells.size(); i++) {
            output += convertRowToString(i, cellValue(i));
        }
        return output;
    }

    private String convertRowToString(int index, String cellValue) {
        String output = String.format("[%s]", cellValue);
        if (isEndOfRow(index)) {
            output += "\n";
        }
        return output;
    }

    private boolean isEndOfRow(int index) {
        return (index + POSITIVE_OFFSET) % dimension == 0;
    }

    public boolean findWin() {
        return findRowWin() || findColumnWin() || findDiagonalWin();
    }

    private boolean findDiagonalWin() {
        return checkDiagonalWin(startIndex, POSITIVE_OFFSET) || checkDiagonalWin(dimension - 1, NEGATIVE_OFFSET);
    }

    private boolean checkDiagonalWin(int startIndex, int offset) {
        int diagonalOffset = determineDiagonalOffset(offset);
        String counterToMatch = cellValue(startIndex);
        int indexLimit = boardSize() + offset;
        for (int i = startIndex + diagonalOffset; i < indexLimit; i += diagonalOffset) {
            if (!isMatch(counterToMatch, i)) {
                return false;
            }
        }
        return true;
    }

    private int determineDiagonalOffset(int offset) {
        return dimension + offset;
    }

    private boolean isMatch(String counterToMatch, int indexToCompare) {
        return cellValue(indexToCompare) == counterToMatch;
    }

    private boolean findColumnWin() {
        for (int column = 0; column < dimension; column++) {
            if (columnWin(column)) {
                return true;
            }
        }
        return false;
    }

    private boolean columnWin(int columnIndex) {
        String counter = cellValue(columnIndex);
        for (int i = columnIndex; i < boardSize(); i += dimension) {
            if (cellValue(i) != counter) {
                return false;
            }
        }
        return true;
    }

    private boolean findRowWin() {
        for (int row = 0; row < boardSize(); row += dimension) {
            if (rowWin(row)) {
                return true;
            }
        }
        return false;
    }

    private boolean rowWin(int rowIndex) {
        String counter = cellValue(rowIndex);
        for (int i = rowIndex; i < (rowIndex + dimension); i++) {
            if (cellValue(i) != counter) {
                return false;
            }
        }
        return true;
    }
}
