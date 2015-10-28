public class Main {
    public static void main(String[] args) {
        CommandLineUI ui = new CommandLineUI();
        Game game = new Game(ui,
                new Player(Counter.X, ui),
                new Player(Counter.O, ui));
        game.play();
    }
}
