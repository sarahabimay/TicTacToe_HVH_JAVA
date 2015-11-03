import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class GameTest {

    public FakeCommandLineUI fakeUI;
    public Game game;

    @Before
    public void setUp() {
        fakeUI = new FakeCommandLineUI();
        game = new Game(fakeUI);
    }


    @Test
    public void choose3x3Game() {
        List<Integer> initialState = new ArrayList<>(Arrays.asList(
                1, 2, 3,
                4, 5, 6,
                7, 8, 9));
        fakeUI.addDummyDimension(3);
        fakeUI.addDummyHumanMoves(initialState);
        fakeUI.setGameType(1);
        game.play();
        assertEquals(true, fakeUI.hasAskedUserForDimension());
    }

    @Test
    public void choose4x4Game() {
        List<Integer> initialState = new ArrayList<>(Arrays.asList(
                1, 2, 3, 4,
                5, 6, 7, 8,
                9, 10, 11, 12,
                13, 14, 15, 16));
        fakeUI.addDummyDimension(4);
        fakeUI.addDummyHumanMoves(initialState);
        fakeUI.setGameType(1);
        game.play();
        assertEquals(true, fakeUI.hasAskedUserForDimension());
    }

    @Test
    public void checkUserHasBeenAskedForTypeOfGame() {
        List<Integer> initialState = new ArrayList<>(Arrays.asList(
                1, 2, 3,
                4, 5, 6,
                7, 8, 9));
        fakeUI.addDummyDimension(3);
        fakeUI.addDummyHumanMoves(initialState);
        fakeUI.setGameType(1);
        Game newGame = new Game(fakeUI);
        newGame.play();
        assertEquals(true, fakeUI.hasAskedUserForGameType());
    }

    @Test
    public void getNextPlayerMove() {
        List<Integer> initialState = new ArrayList<>(Arrays.asList(
                1, 2, 3,
                4, 5, 6,
                7, 8, 9));
        fakeUI.addDummyDimension(3);
        fakeUI.addDummyHumanMoves(initialState);
        fakeUI.setGameType(1);
        game.play();
        assertEquals(true, fakeUI.hasAskedUserForNextPosition());
    }

    @Test
    public void playNextMoveAndSeeResult() {
        List<Integer> initialState = new ArrayList<>(Arrays.asList(1, 0, 0, 0, 0, 0, 0, 0, 0));
        fakeUI.addDummyDimension(3);
        fakeUI.addDummyHumanMoves(initialState);
        fakeUI.setGameType(1);
        game.requestBoardSize();
//        game.selectPlayers(game.requestGameType());
//        Board board = game.nextPlayerMakesMove(Counter.X);
//        assertEquals("" +
//                        "[X][2][3]\n" +
//                        "[4][5][6]\n" +
//                        "[7][8][9]\n",
//                fakeUI.displayBoard(board));
    }

//    @Test
//    public void checkPlayerCounterGetsSwitched() {
//        List<Integer> initialState = new ArrayList<>(Arrays.asList(1, 0, 0, 0, 0, 0, 0, 0, 0));
//        fakeUI.addDummyDimension(3);
//        fakeUI.addDummyHumanMoves(initialState);
//        fakeUI.setGameType("HVH");
//        game.requestBoardSize();
//        game.nextPlayerMakesMove(Counter.X);
//        assertEquals(Counter.O, game.getNextCounter(Counter.X));
//    }

    @Test
    public void displayBoardAfterEachMove() {
        List<Integer> initialState = new ArrayList<>(Arrays.asList(
                1, 2, 3,
                4, 5, 6,
                7, 8, 9));
        fakeUI.addDummyDimension(3);
        fakeUI.addDummyHumanMoves(initialState);
        fakeUI.setGameType(1);
        game.play();
        assertEquals(true, fakeUI.hasDisplayedBoardToUser());
    }

    @Test
    public void displayResultAtEndOfGame() {
        List<Integer> initialState = new ArrayList<>(Arrays.asList(
                1, 2, 3,
                4, 5, 6,
                7, 8, 9));
        fakeUI.addDummyDimension(3);
        fakeUI.addDummyHumanMoves(initialState);
        fakeUI.setGameType(1);
        game.play();
        assertEquals(true, fakeUI.hasDisplayedResultToUser());
    }

    @Test
    public void requestToQuitAfterGameOver() {
        List<Integer> initialState = new ArrayList<>(Arrays.asList(
                1, 2, 3,
                4, 5, 6,
                7, 8, 9));
        fakeUI.addDummyDimension(3);
        fakeUI.addDummyHumanMoves(initialState);
        fakeUI.setGameType(1);
        fakeUI.addDummyPlayAgainChoice(1);
        game.play();
        assertEquals(true, fakeUI.hasAskedUserToQuitGame());
    }

    @Test
    public void checkGameIsOver() {
        List<Integer> initialState = new ArrayList<>(Arrays.asList(
                1, 2, 3,
                4, 5, 6,
                7, 8, 9));
        fakeUI.addDummyDimension(3);
        fakeUI.addDummyHumanMoves(initialState);
        fakeUI.setGameType(1);
        game.play();
        assertEquals(true, game.isGameOver());
    }

    @Test
    public void checkResultIsADraw() {
        List<Integer> initialState = new ArrayList<>(Arrays.asList(
                2, 1, 4,
                5, 6, 3,
                7, 8, 9));
        fakeUI.addDummyDimension(3);
        fakeUI.addDummyHumanMoves(initialState);
        fakeUI.setGameType(1);
        game.play();
        assertEquals(true, game.isGameOver());
        assertEquals(true, fakeUI.isADraw());
    }

    @Test
    public void checkResultIsAWin() {
        List<Integer> initialState = new ArrayList<>(Arrays.asList(
                1, 2, 3,
                4, 5, 6,
                7, 8, 9));
        fakeUI.addDummyDimension(3);
        fakeUI.addDummyHumanMoves(initialState);
        fakeUI.setGameType(1);
        game.play();
        assertEquals(true, game.isGameOver());
        assertEquals(false, fakeUI.isADraw());
        assertEquals(Counter.X, fakeUI.getWinner());
    }

    @Test
    public void humanVsComputer() {
        List<Integer> initialState = new ArrayList<>(Arrays.asList(
                2, 1, 4,
                5, 6, 3,
                7, 8, 9));
        fakeUI.addDummyDimension(3);
        fakeUI.addDummyHumanMoves(initialState);
        fakeUI.setGameType(2);
        game.play();
        assertEquals(Player.Type.HUMAN, game.getPlayerType(Counter.X));
        assertEquals(Player.Type.COMPUTER, game.getPlayerType(Counter.O));
    }

    @Test
    public void computerVsHuman() {
        List<Integer> initialState = new ArrayList<>(Arrays.asList(
                2, 1, 4,
                5, 6, 3,
                7, 8, 9));
        fakeUI.addDummyDimension(3);
        fakeUI.addDummyHumanMoves(initialState);
        fakeUI.setGameType(3);
        game.play();
        assertEquals(Player.Type.COMPUTER, game.getPlayerType(Counter.X));
        assertEquals(Player.Type.HUMAN, game.getPlayerType(Counter.O));
    }

//    @Test
//    public void hvcWithInvalidMove() {
//        fakeUI = generateFakeUI(Arrays.asList(1, 2, 4, 3, 9), 3, 2);
//
//        FakeComputerPlayer fakeAI = generateFakeComputerPlayer(Arrays.asList(2, 7, 8, 5));
//        FakePlayerFactory fakePlayerFactory = generateFakePlayerFactory(fakeAI, fakeUI);
//
//        Game fakeAIGame = new Game(fakeUI, fakePlayerFactory);
//        fakeAIGame.play();
//        assertEquals(Player.Type.HUMAN, fakeAIGame.getPlayerType(Counter.X));
//        assertEquals(Player.Type.FAKE, fakeAIGame.getPlayerType(Counter.O));
//        assertEquals("" +
//                        "[X][O][X]\n" +
//                        "[X][O][6]\n" +
//                        "[O][O][X]\n",
//                fakeAIGame.displayBoard());
//    }

    private FakeCommandLineUI generateFakeUI(List<Integer> moves, int dimension, Integer gameType) {
        List<Integer> initialState = new ArrayList<>(moves);
        fakeUI.addDummyDimension(dimension);
        fakeUI.addDummyHumanMoves(initialState);
        fakeUI.setGameType(gameType);
        return fakeUI;
    }

    private FakePlayerFactory generateFakePlayerFactory(FakeComputerPlayer fakeAI, UserInterface ui) {
        FakePlayerFactory fakePlayerFactory = new FakePlayerFactory(ui);
        fakePlayerFactory.addFakeComputerPlayer(fakeAI);
        return fakePlayerFactory;
    }

    private FakeComputerPlayer generateFakeComputerPlayer(List<Integer> moves) {
        List<Integer> computerMoves = new ArrayList<>(moves);
        FakeComputerPlayer fakeAI = new FakeComputerPlayer(Counter.O, fakeUI);
        fakeAI.setDummyAIMoves(computerMoves);
        return fakeAI;
    }
}
