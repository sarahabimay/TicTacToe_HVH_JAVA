public class FakePlayerFactory extends PlayerFactory {
    private FakeComputerPlayer fakeComputerPlayer = null;

    public FakePlayerFactory(UserInterface userInterface) {
        super(userInterface);
    }

    public void addFakeComputerPlayer(FakeComputerPlayer fakeAI) {
        this.fakeComputerPlayer = fakeAI;
    }
}
