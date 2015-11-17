import javafx.event.ActionEvent;
import javafxgui.Controller;
import jttt.Core.HumanPlayer;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ControllerTest {

    private int HVH;

    @Before
    public void setUp() throws Exception {
        HVH = 1;
    }

    @Test
    public void userProvidesDimensionAndGameType(){
        Controller controller = new Controller();
        controller.createNewGame(3, HVH);
        assertEquals(9, controller.getGame().getBoardSize());
        assertEquals(HumanPlayer.class, controller.getGame().getPlayers().get(0).getClass());
        assertEquals(HumanPlayer.class, controller.getGame().getPlayers().get(1).getClass());
    }

    @Test
    public void guiBoardGetsUpdatedByAI() {
        Controller controller = new Controller();
        ActionEvent event = new ActionEvent();
        controller.start(event);
        controller.positionSelected(event);
        assertEquals(controller.zero.getText(), "X");

    }

    @Test
    @Ignore
    public void testStartEvent() {
        ActionEvent event = new ActionEvent();
        Controller controller = new Controller();
//        FakeController fakeer = new FakeController();
        controller.start(event);

    }
}
