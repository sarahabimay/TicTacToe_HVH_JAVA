package jttt.core.board;

import java.util.ArrayList;
import java.util.List;

public class Line {
    private List<Mark> lineElements = new ArrayList<>();

    public Line(List<Mark> lineElements) {
        this.lineElements = lineElements;

    }

    public Line(int numberOfElements) {
        for (int i = 0; i < numberOfElements; i++) {
            lineElements.add(Mark.EMPTY);
        }
    }

    public List<Mark> getElements() {
        return lineElements;
    }

    public void addElementAtIndex(int index, Mark mark) {
        lineElements.add(index, mark);
    }

    public boolean hasAWinner() {
        return !lineElements.contains(Mark.EMPTY) && lineElements.stream().allMatch(counter -> counter.equals(lineElements.get(0)));
    }

    public boolean hasCounterWin(Mark targetMark) {
        return lineElements.stream().allMatch(counter -> counter.equals(targetMark));
    }

    public Mark findWinner() {
        return hasAWinner() ? lineElements.get(0) : Mark.EMPTY;
    }
}
