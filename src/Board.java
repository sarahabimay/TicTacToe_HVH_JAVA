import java.util.ArrayList;
import java.util.List;

public class Board {
    private static final int POSITIVE_OFFSET = 1;
    private static final int NEGATIVE_OFFSET = -1;
    private int dimension;
    private List<Counter> cells;

    public Board(int dimension) {
        this.dimension = dimension;
        this.cells = new ArrayList<>(generateEmptyCells());
    }

    public Board(int dimension, List<Counter> initialState) {
        this.dimension = dimension;
        this.cells = initialState;
    }

    public Board(List<Counter> cells) {
        this.dimension = (int) Math.sqrt(cells.size());
        this.cells = cells;
    }

    public void clearBoard() {
        this.cells = new ArrayList<>(generateEmptyCells());
    }

    public int getDimension() {
        return dimension;
    }

    public List<Counter> getCells() {
        return cells;
    }

    public int boardSize() {
        return dimension * dimension;
    }

    public Counter cellValue(int startIndex) {
        return cells.get(startIndex);
    }

    public Board playCounterInPosition(int position, Counter counter) {
        if (validPosition(position) && !cellIsOccupied(position + (NEGATIVE_OFFSET))) {
            cells.set(position + (NEGATIVE_OFFSET), counter);
            return new Board(cells);
        }
        return this;
    }

    private boolean validPosition(int position) {
        return position >= 0 && position <= boardSize();
    }

    public ArrayList<String> findPositions(Counter counter) {
        ArrayList<String> counterPositions = new ArrayList<>();
        for (int i = 0; i < cells.size(); i++) {
            if (cellValue(i) == counter) {
                counterPositions.add(String.valueOf(i));
            }
        }
        return counterPositions;
    }

    public boolean gameOver() {
        return isAWinner() || noEmptyPositions();
    }


    public Counter getWinner() {
        if (findWin(Counter.X)) {
            return Counter.X;
        }
        if (findWin(Counter.O)) {
            return Counter.O;
        }
        return Counter.EMPTY;
    }

    public boolean findWin() {
        return findColumnWin(Counter.X) || findColumnWin(Counter.O) ||
                findRowWin(Counter.X) || findRowWin(Counter.O) ||
                findDiagonalWin(Counter.X) || findDiagonalWin(Counter.O);
    }

    public boolean findWin(Counter searchCounter) {
        return findRowWin(searchCounter) || findColumnWin(searchCounter) || findDiagonalWin(searchCounter);
    }

    public boolean findRowWin(Counter searchCounter) {
        for (int row = 0; row < boardSize(); row += dimension) {
            if (rowWin(row, searchCounter)) {
                return true;
            }
        }
        return false;
    }

    public boolean findColumnWin(Counter searchCounter) {
        for (int column = 0; column < dimension; column++) {
            if (columnWin(column, searchCounter)) {
                return true;
            }
        }
        return false;
    }

    public boolean findDiagonalWin(Counter searchCounter) {
        return checkDiagonalWin(searchCounter, 0, POSITIVE_OFFSET) ||
                checkDiagonalWin(searchCounter, dimension - 1, NEGATIVE_OFFSET);
    }

    private boolean noEmptyPositions() {
        return boardSize() == numberOfPositionsTaken();
    }

    public int numberOfOpenPositions() {
        return boardSize() - numberOfPositionsTaken();
    }

    private int numberOfPositionsTaken() {
        return findPositions(Counter.X).size() + findPositions(Counter.O).size();
    }

    private boolean isAWinner() {
        return getWinner() != Counter.EMPTY;
    }

    private List<Counter> generateEmptyCells() {
        List<Counter> initialCells = new ArrayList<>(boardSize());
        for (int i = 0; i < boardSize(); i++) {
            initialCells.add(Counter.EMPTY);
        }
        return initialCells;
    }

    private boolean rowWin(int rowIndex, Counter searchCounter) {
        Counter counter = cellValue(rowIndex);
        if (counter == searchCounter) {
            for (int i = rowIndex; i < (rowIndex + dimension); i++) {
                if (cellValue(i) != counter) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    private boolean columnWin(int columnIndex, Counter searchCounter) {
        Counter counter = cellValue(columnIndex);
        if (counter == searchCounter) {
            for (int i = columnIndex; i < boardSize(); i += dimension) {
                if (cellValue(i) != counter) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    private boolean checkDiagonalWin(Counter searchCounter, int startIndex, int offset) {
        Counter counterToMatch = cellValue(startIndex);
        if (counterToMatch == searchCounter) {
            int diagonalOffset = determineDiagonalOffset(offset);
            int indexLimit = boardSize() + offset;
            for (int i = startIndex + diagonalOffset; i < indexLimit; i += diagonalOffset) {
                if (!isMatch(counterToMatch, i)) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    private int determineDiagonalOffset(int offset) {
        return dimension + offset;
    }

    private boolean isMatch(Counter counterToMatch, int indexToCompare) {
        return cellValue(indexToCompare) == counterToMatch;
    }

    private boolean cellIsOccupied(int cellIndex) {
        return cells.get(cellIndex) == Counter.X || cells.get(cellIndex) == Counter.O;
    }

}
