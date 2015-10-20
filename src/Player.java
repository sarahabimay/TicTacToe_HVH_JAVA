public class Player {
    private Counter counter;

    public Player(Counter counter) {
        this.counter = counter;
    }

    public Counter opponentMarker() {
        if (counter == Counter.X){
            return Counter.O;
        }
        if (counter == Counter.O){
            return Counter.X;
        }
        return Counter.EMPTY;
    }

    public Board playTurn(Board board) {
        int nextPosition = 3;
        board.playCounterInPosition(nextPosition, counter);
        return board;
    }

    public Board playTurn(Board board, int position) {
        board.playCounterInPosition(position, counter);
        return board;
    }
}
