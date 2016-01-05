package jttt.UI;

public enum BinaryChoice {
    YES(1),
    NO(2);

    private int choiceOption;

    BinaryChoice(int choiceOption) {
        this.choiceOption = choiceOption;
    }

    public int getChoiceOption() {
        return choiceOption;
    }

    public boolean equalsChoice(int choice) {
        return choiceOption == choice;
    }
}
