package jttt.core.board;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;
import static java.util.stream.IntStream.range;

public class Board {
    private static final int POSITIVE_OFFSET = 1;
    private static final int NEGATIVE_OFFSET = -1;
    private static final int DISPLAY_POSITION_OFFSET = 1;
    private int dimension;
    private List<Mark> cells;

    public Board(int dimension) {
        this.dimension = dimension;
        this.cells = new ArrayList<>(generateEmptyCells());
    }

    public Board(int dimension, List<Mark> initialState) {
        this.dimension = dimension;
        this.cells = initialState;
    }

    public int getDimension() {
        return dimension;
    }

    public List<Mark> getCells() {
        return cells;
    }

    public int boardSize() {
        return dimension * dimension;
    }

    public Board playCounterInPosition(int displayPositionIndex, Mark mark) {
        if (validDisplayPosition(displayPositionIndex)) {
            cells.set(toZeroIndexedPosition(displayPositionIndex), mark);
        }
        return new Board(dimension, cells);
    }

    public Board createBoardCopy() {
        return new Board(dimension, copyOfCells());
    }

    public boolean isEmpty() {
        return remainingPositions().size() == boardSize();
    }

    public boolean isGameOver() {
        return hasAWinner() || !areEmptyPositions();
    }

    public boolean hasAWinner() {
        return foundWinInRow() || foundWinInColumn() || foundWinInDiagonal();
    }

    public Mark findWinner() {
        Optional<Line> lineOptional = getAllLines().stream().filter(Line::hasAWinner).findFirst();
        return (lineOptional.isPresent()) ? lineOptional.get().findWinner() : Mark.EMPTY;
    }

    public boolean validDisplayPosition(int displayPositionIndex) {
        return positionIsWithinRange(displayPositionIndex) && !cellIsOccupied(toZeroIndexedPosition(displayPositionIndex));
    }

    public boolean cellIsOccupied(int zeroIndexedPosition) {
        return cells.get(zeroIndexedPosition) == Mark.X || cells.get(zeroIndexedPosition) == Mark.O;
    }

    public List<Integer> remainingPositions() {
        return range(0, cells.size())
                .filter(p -> cells.get(p) == Mark.EMPTY)
                .mapToObj(i -> i)
                .collect(toList());
    }

    public Mark findNextCounter() {
        if (findPositions(Mark.X).size() > findPositions(Mark.O).size()) {
            return Mark.O;
        }
        return Mark.X;
    }

    public ArrayList<Integer> findPositions(Mark mark) {
        ArrayList<Integer> counterPositions = new ArrayList<>();
        for (int i = 0; i < cells.size(); i++) {
            if (findMarkAtIndex(i) == mark) {
                counterPositions.add(i);
            }
        }
        return counterPositions;
    }

    public Mark findMarkAtDisplayPosition(int displayPositionIndex) {
        return findMarkAtIndex(toZeroIndexedPosition(displayPositionIndex));
    }

    public Mark findMarkAtIndex(int zeroIndexedPosition) {
        return cells.get(zeroIndexedPosition);
    }

    public int numberOfOpenPositions() {
        return boardSize() - numberOfPositionsTaken();
    }

    boolean foundWinInRow() {
        return getRows().stream().anyMatch(Line::hasAWinner);
    }

    boolean foundWinInColumn() {
        return getColumns().stream().anyMatch(Line::hasAWinner);
    }

    boolean foundWinInDiagonal() {
        return getDiagonals().stream().anyMatch(Line::hasAWinner);
    }

    List<Line> getRows() {
        List<List<Mark>> rowLines = Lists.partition(cells, dimension);
        return rowLines.stream().map(Line::new).collect(toList());
    }

    List<Line> getColumns() {
        return range(0, dimension).mapToObj(i -> new Line(getColumnCells(i))).collect(toList());
    }

    List<Line> getDiagonals() {
        return Arrays.stream(new int[]{0, dimension - 1}).mapToObj(i -> new Line(getDiagonalCells(i))).collect(toList());
    }

    private int toZeroIndexedPosition(int displayPositionIndex) {
        return displayPositionIndex - DISPLAY_POSITION_OFFSET;
    }

    private List<Mark> copyOfCells() {
        List<Mark> newCells = new ArrayList<>(cells);
        return newCells;
    }

    private ArrayList<Line> getAllLines() {
        ArrayList<Line> allLines = new ArrayList<>();
        allLines.addAll(getRows());
        allLines.addAll(getColumns());
        allLines.addAll(getDiagonals());
        return allLines;
    }

    private List<Mark> getDiagonalCells(int diagonalIndex) {
        int nextCellIncrement = determineNextDiagonalCellIncrement(diagonalIndex);
        int lastCellIndex = determineLastIndexForDiagonal(diagonalIndex);
        ArrayList<Integer> indexesList = new ArrayList<>();
        for (int i = diagonalIndex; i < lastCellIndex; i += nextCellIncrement) {
            indexesList.add(i);
        }
        return indexesList.stream().map(i -> cells.get(i)).collect(toList());
    }

    private List<Mark> getColumnCells(int columnIndex) {
        return range(0, dimension).mapToObj(i -> cells.get(columnIndex + i * dimension)).collect(toList());
    }

    private boolean areEmptyPositions() {
        return boardSize() != numberOfPositionsTaken();
    }

    private int numberOfPositionsTaken() {
        return findPositions(Mark.X).size() + findPositions(Mark.O).size();
    }

    private boolean positionIsWithinRange(int displayPositionIndex) {
        return 0 < displayPositionIndex && displayPositionIndex <= boardSize();
    }

    private int determineLastIndexForDiagonal(int zeroIndexedPosition) {
        return boardSize() + determineDiagonalIndexDirection(zeroIndexedPosition);
    }

    private int determineNextDiagonalCellIncrement(int zeroIndexedPosition) {
        int directionalOffset = determineDiagonalIndexDirection(zeroIndexedPosition);
        return zeroIndexedPosition == 0 ? dimension + directionalOffset : dimension + (directionalOffset);
    }

    private int determineDiagonalIndexDirection(int zeroIndexedPosition) {
        return zeroIndexedPosition == 0 ? POSITIVE_OFFSET : NEGATIVE_OFFSET;
    }

    private List<Mark> generateEmptyCells() {
        List<Mark> initialCells = new ArrayList<>(boardSize());
        for (int i = 0; i < boardSize(); i++) {
            initialCells.add(Mark.EMPTY);
        }
        return initialCells;
    }
}
