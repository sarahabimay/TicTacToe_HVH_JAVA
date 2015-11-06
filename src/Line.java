import java.util.ArrayList;
import java.util.List;

public class Line {
    private List<Counter> lineElements = new ArrayList<>();

    public Line(List<Counter> lineElements) {
        this.lineElements = lineElements;

    }

    public Line(int numberOfElements) {
        for (int i = 0; i < numberOfElements; i++) {
            lineElements.add(Counter.EMPTY);
        }
    }

    public List<Counter> getElements() {
        return lineElements;
    }

    public void addElementAtIndex(int index, Counter counter) {
        lineElements.add(index, counter);
    }

    public boolean hasAWinner() {
        return !lineElements.contains(Counter.EMPTY) && lineElements.stream().allMatch(counter -> counter.equals(lineElements.get(0)));
    }

    public boolean hasCounterWin(Counter targetCounter) {
        return lineElements.stream().allMatch(counter -> counter.equals(targetCounter));
    }

    public Counter findWinner() {
        return hasAWinner() ? lineElements.get(0) : Counter.EMPTY;
    }

    public void printLine() {
        for (Counter c : lineElements) {
            System.out.println(c + "\t");
        }
    }

//    public int score() {
//        Counter aiCounter = Counter.X;
//        System.out.println(Collections.frequency(lineElements, aiCounter));
//            if (counter == aiCounter)
//        }
//        lineElements.stream().reduce(0, (sum, counter) -> sum += )
//        return 1;
//    }
}
