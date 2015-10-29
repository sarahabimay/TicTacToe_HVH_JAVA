public class Main {
    public static void main(String[] args) {
        CommandLineUI ui = new CommandLineUI();
        Game game = new Game(ui,
                new HumanPlayer(Counter.X, ui),
                new HumanPlayer(Counter.O, ui));
        game.play();
    }
}
