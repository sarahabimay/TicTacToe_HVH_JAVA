public class TTTHvHGame {
    private final String PLAYER1 = "Player1";
    private final String PLAYER2 = "Player2";
    private final String X = "X";
    private final String O = "O";
    private String currentPlayer;
    private Board board;

    public TTTHvHGame() {
        currentPlayer = PLAYER1;
    }

    public TTTHvHGame(Board board, String currentPlayer) {
        this.currentPlayer = currentPlayer;
        this.board = board;
    }

    public String restartGame(Board board) {
        this.board = board;
        return String.format("%s\nPlayer1(X) please select a cell[1-%d]:\n", board.boardAsString(), board.size());
    }

    public String playMove(int cellNumber) {
        board.setCellValue(cellNumber - 1, currentCounter());
        switchCurrentPlayer();
        return nextPlayPrompt();
    }

    private String nextPlayPrompt() {
        return String.format("%s\n%s(%s) please select a cell[1-%d]:\n",
                board.boardAsString(),
                currentPlayer,
                currentCounter(),
                board.size());
    }

    private String currentCounter() {
        return currentPlayer == PLAYER1 ? X : O;
    }

    private void switchCurrentPlayer() {
        currentPlayer = currentPlayer == PLAYER1 ? PLAYER2 : PLAYER1;
    }

    public int boardSize() {
        return board.size();
    }

    public boolean isGameOver() {
        return board.findWin();
    }
}
