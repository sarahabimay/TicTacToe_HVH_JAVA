import javafx.event.ActionEvent;
import javafxgui.Controller;
import jttt.Core.Counter;
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
        assertEquals(HumanPlayer.class, controller.getGame().getPlayer(Counter.X).getClass());
        assertEquals(HumanPlayer.class, controller.getGame().getPlayer(Counter.O).getClass());
    }

    @Test
    @Ignore
    public void guiBoardGetsUpdatedByAI() {
        Controller controller = new Controller();
        // FakeController fc = new FakeController();
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
//        javafxgui.FakeController fakeer = new javafxgui.FakeController();
        controller.start(event);

    }
}
