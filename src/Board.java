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

    public void resetBoard() {
        this.cells = generateEmptyCells();
    }

    public Board playCounterInPosition(Integer position, Counter counter) {
        if (validPosition(position)) {
            cells.set(position - POSITIVE_OFFSET, counter);
        }
        return new Board(cells);
    }

    public boolean isGameOver() {
        return hasAWinner() || !areEmptyPositions();
    }

    public boolean hasAWinner() {
        return foundWinInRow() || foundWinInColumn() || foundWinInDiagonal();
    }

    protected boolean foundWinInRow() {
        return getRows().stream().anyMatch(Line::hasAWinner);
    }

    protected boolean foundWinInColumn() {
        return getColumns().stream().anyMatch(Line::hasAWinner);
    }

    public boolean foundWinInDiagonal() {
        return getDiagonals().stream().anyMatch(Line::hasAWinner);
    }

    protected List<Line> getRows() {
        List<List<Counter>> rowLines = Lists.partition(cells, dimension);
        return rowLines.stream().map(Line::new).collect(toList());
    }

    protected List<Line> getColumns() {
        return range(0, dimension).mapToObj(i -> new Line(getColumnCells(i))).collect(toList());
    }

    protected List<Line> getDiagonals() {
        return Arrays.stream(new int[]{0, dimension - 1}).mapToObj(i -> new Line(getDiagonalCells(i))).collect(toList());
    }

    protected boolean isAWinner() {
        return findWinner() != Counter.EMPTY;
    }

    public Counter findWinner() {
        Optional<Line> lineOptional = getAllLines().stream().filter(Line::hasAWinner).findFirst();
        return (lineOptional.isPresent()) ? lineOptional.get().findWinner() : Counter.EMPTY;
    }

    public boolean validPosition(Integer position) {
        return position != null && positionIsWithinRange(position) && !cellIsOccupied(position - POSITIVE_OFFSET);
    }

    public boolean cellIsOccupied(int cellIndex) {
        return cells.get(cellIndex) == Counter.X || cells.get(cellIndex) == Counter.O;
    }

    protected int numberOfOpenPositions() {
        return boardSize() - numberOfPositionsTaken();
    }

    protected ArrayList<String> findPositions(Counter counter) {
        ArrayList<String> counterPositions = new ArrayList<>();
        for (int i = 0; i < cells.size(); i++) {
            if (cellValue(i) == counter) {
                counterPositions.add(String.valueOf(i));
            }
        }
        return counterPositions;
    }

    protected List<Counter> getCells() {
        return cells;
    }

    protected int boardSize() {
        return dimension * dimension;
    }

    protected Counter cellValue(int startIndex) {
        return cells.get(startIndex);
    }

    private ArrayList<Line> getAllLines() {
        ArrayList<Line> allLines = new ArrayList<>();
        allLines.addAll(getRows());
        allLines.addAll(getColumns());
        allLines.addAll(getDiagonals());
        return allLines;
    }

    private List<Counter> getDiagonalCells(int diagonalIndex) {
        int nextCellIncrement = determineNextDiagonalCellIncrement(diagonalIndex);
        int lastCellIndex = determineLastIndexForDiagonal(diagonalIndex);
        ArrayList<Integer> indexesList = new ArrayList<>();
        for (int i = diagonalIndex; i < lastCellIndex; i += nextCellIncrement) {
            indexesList.add(i);
        }
        return indexesList.stream().map(i -> cells.get(i)).collect(toList());
    }

    private List<Counter> getColumnCells(int columnIndex) {
        return range(0, dimension).mapToObj(i -> cells.get(columnIndex + i * dimension)).collect(toList());
    }

    private boolean areEmptyPositions() {
        return boardSize() != numberOfPositionsTaken();
    }

    private int numberOfPositionsTaken() {
        return findPositions(Counter.X).size() + findPositions(Counter.O).size();
    }

    private boolean positionIsWithinRange(int position) {
        return 0 < position && position <= boardSize();
    }

    private int determineLastIndexForDiagonal(int startIndex) {
        return boardSize() + determineDiagonalIndexDirection(startIndex);
    }

    private int determineNextDiagonalCellIncrement(int startIndex) {
        int directionalOffset = determineDiagonalIndexDirection(startIndex);
        return startIndex == 0 ? dimension + directionalOffset : dimension + (directionalOffset);
    }

    private int determineDiagonalIndexDirection(int startIndex) {
        return startIndex == 0 ? POSITIVE_OFFSET : NEGATIVE_OFFSET;
    }

    private List<Counter> generateEmptyCells() {
        List<Counter> initialCells = new ArrayList<>(boardSize());
        for (int i = 0; i < boardSize(); i++) {
            initialCells.add(Counter.EMPTY);
        }
        return initialCells;
    }

    public int calculateBoardScore(Counter aiCounter) {
        return findWinner() == aiCounter ? 10 : -10;
//        return getAllLines().stream().reduce(0, (sum, line) -> sum += line.score(), (sum1, sum2) -> sum1 + sum2);
    }
}
