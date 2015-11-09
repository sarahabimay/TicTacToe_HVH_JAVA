import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.counting;

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

    public Integer score(Counter aiCounter) {
        Counter oppCounter = aiCounter.opponentCounter();
        if (bothCountersPresent()) {
            return 0;
        }
        long count = countOfCounter(aiCounter);
        if (count > 0) {
            return (int) Math.pow(10, count - 1);
        }
        count = countOfCounter(oppCounter);
        if (count > 0) {
            return -1 * (int) Math.pow(10, count - 1);
        }
        return 0;
    }

    private Long countOfCounter(Counter counter) {
        return lineElements.stream().filter(c -> c == counter).collect(counting());
    }

    private boolean bothCountersPresent() {
        return lineElements.indexOf(Counter.X) >= 0 && lineElements.indexOf(Counter.O) >= 0;
    }
}
