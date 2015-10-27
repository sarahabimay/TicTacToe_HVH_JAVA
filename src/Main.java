public class Main {
    public static void main(String[] args) {
        CommandLineUI ui = new CommandLineUI();
        int boardDimension = ui.requestBoardSize();
        Game game = new Game(ui, new Board(boardDimension),
                new Player(Counter.X, ui),
                new Player(Counter.O, ui));
        game.play();
    }
}
