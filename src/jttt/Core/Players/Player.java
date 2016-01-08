package jttt.Core.Players;

import jttt.Core.Board.Board;
import jttt.Core.Board.Mark;

public abstract class Player {
    public enum Type {
        AI,
        HUMAN,
        GUI
    }

    protected Mark mark;
    Type playerType;

    public Player(Mark mark, Type type) {
        this.playerType = type;
        this.mark = mark;
    }

    public Type getType(){
        return playerType;
    }

    public Mark getMark() {
        return mark;
    }

    public Mark opponentCounter() {
        if (mark == Mark.X) {
            return Mark.O;
        }
        if (mark == Mark.O) {
            return Mark.X;
        }
        return Mark.EMPTY;
    }

    public boolean isReady(){
        return true;
    }

    abstract public int getNextPosition(Board board);
}
