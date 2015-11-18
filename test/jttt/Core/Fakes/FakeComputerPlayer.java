package jttt.Core.Fakes;

import jttt.Core.Board;
import jttt.Core.Mark;
import jttt.Core.Players.Player;
import jttt.UI.UserInterface;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FakeComputerPlayer extends Player {
    private boolean hasGeneratedNextMove;
    private List<Integer> dummyPositions;

    public FakeComputerPlayer(Mark mark, UserInterface userInterface) {
        super(mark, Type.AI, userInterface);
        this.hasGeneratedNextMove = false;
        this.dummyPositions = new ArrayList<>();

    }

    public Board playTurn(Board board, int newPosition) {
        return board;
    }

    public Board playTurn(Board board) {
        hasGeneratedNextMove = true;
        int nextPosition = calculateNextMove(board);
        return board.playCounterInPosition(nextPosition, this.mark);
    }

    public void setDummyPosition(int dummyPosition) {
        this.dummyPositions = new ArrayList<>(
                Arrays.asList(dummyPosition));
    }

    public boolean computerHasGeneratedNextMove() {
        return hasGeneratedNextMove;
    }

    public void setDummyAIMoves(List<Integer> computerMoves) {
        this.dummyPositions = computerMoves;
    }

    private int calculateNextMove(Board board) {
        if (dummyPositions.size() > 0) {
            return dummyPositions.remove(0);
        }
        System.out.println("No computer positions pre-loaded");
        return -1;
    }
}
