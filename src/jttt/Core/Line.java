package jttt.Core;

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
}
