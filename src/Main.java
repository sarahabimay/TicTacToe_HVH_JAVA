public class Main {
    public static void main(String[] args) {
        CommandLineUI ui = new CommandLineUI();
        Game game = new Game(ui);
        game.play();
    }
}
