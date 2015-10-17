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
        this.dimension = (int)Math.round(Math.sqrt(cells.size()));
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
        if( getRowWin()){
            return true;
        }
        if (getColumnWin()){
            return true;
        }
//        boolean isDiaganolWin = getDiaganolWin();

//        return isColumnWin || isRowWin || isDiaganolWin;
        return false;
    }

    private boolean getColumnWin() {
        for(int column = 0; column < (dimension); column++){
            System.out.println("Columns Start Index: " + column);
            if (columnWin(column)){
                return true;
            }
        }
        return false;
    }

    private boolean columnWin(int columnIndex) {
        String counter = cells.get(columnIndex);
        System.out.println("Searching For: " + counter + " in column: " + columnIndex);
        for (int i = columnIndex; i < dimension*dimension; i+=dimension) {
            System.out.println("Value in cell: " + cells.get(i));
            if(cells.get(i) != counter ) {
                System.out.println("No win in column: " + i);
                return false;
            }
        }
        return true;
    }

    private boolean getRowWin() {
        for (int row = 0; row < dimension*dimension; row+=dimension) {
            System.out.println("Row's Start Index: " + row);
            if (rowWin(row)) {
                System.out.println("Found a Win!");
                return true;
            }
        }
        return false;
    }

    private boolean rowWin(int rowIndex) {
        String counter = cells.get(rowIndex);
        System.out.println("Searching For: " + counter + " in row: " + rowIndex);
        for (int i = rowIndex; i < (rowIndex + dimension); i++) {
            System.out.println(cells.get(i));
            if (cells.get(i) != counter) {
                System.out.println("No win in row: " + rowIndex);
                return false;
            }
        }
        return true;
    }
}
