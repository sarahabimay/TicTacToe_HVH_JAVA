import java.util.ArrayList;
import java.util.List;

public class Board {
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

    private List<String> generateEmptyCells(int dimension) {
        List<String> initialCells = new ArrayList<>(dimension * dimension);
        for (int i = 0; i < dimension * dimension; i++) {
            int cellNumber = i + 1;
            initialCells.add(String.valueOf(cellNumber));
        }
        return initialCells;
    }

    public void updateBoard(int cellNumber, String counter) {
        cells.set(cellNumber, counter);
    }

    public String boardAsString() {
        String output = "";
        for (int i = 0; i < cells.size(); i++) {
            output += convertRowToString(i, cells.get(i));
        }
        return output;
    }

    private String convertRowToString(int index, String cellValue) {
        String output = "";
        output += String.format("[%s]", cellValue);
        if ((index + 1) % dimension == 0) {
            output += "\n";
        }
        return output;
    }

    public boolean findWin() {
        if (getRowWin()) {
            return true;
        }
        if (getColumnWin()) {
            return true;
        }
        if (getDiaganolWin()) {
            return true;
        }

        return false;
    }

    private boolean getDiaganolWin() {
        return checkDiagonalWin(0, 1) || checkDiagonalWin(dimension - 1, -1);
    }

    private boolean checkDiagonalWin(int startIndex, int offset) {
        int diagonalOffset = dimension + offset;
        String counterToMatch = cells.get(startIndex);
        int indexLimit = (dimension * dimension) + offset;
        for (int i = startIndex + diagonalOffset; i < indexLimit; i += diagonalOffset) {
            if (!isMatch(counterToMatch, i)) {
                return false;
            }
        }
        return true;
    }

    private boolean isMatch(String counterToMatch, int indexToCompare) {
        return cells.get(indexToCompare) == counterToMatch;
    }

    private boolean getColumnWin() {
        for (int column = 0; column < (dimension); column++) {
            if (columnWin(column)) {
                return true;
            }
        }
        return false;
    }

    private boolean columnWin(int columnIndex) {
        String counter = cells.get(columnIndex);
        for (int i = columnIndex; i < dimension * dimension; i += dimension) {
            if (cells.get(i) != counter) {
                return false;
            }
        }
        return true;
    }

    private boolean getRowWin() {
        for (int row = 0; row < dimension * dimension; row += dimension) {
            if (rowWin(row)) {
                return true;
            }
        }
        return false;
    }

    private boolean rowWin(int rowIndex) {
        String counter = cells.get(rowIndex);
        for (int i = rowIndex; i < (rowIndex + dimension); i++) {
            if (cells.get(i) != counter) {
                return false;
            }
        }
        return true;
    }
}
