import java.util.ArrayList;
import java.util.List;

public class Board {
    private int dimension;
    private List<String> cells;

    public Board(int dimension) {
        this.dimension = dimension;
        this.cells = new ArrayList<>(generateEmptyCells(dimension));
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
}
