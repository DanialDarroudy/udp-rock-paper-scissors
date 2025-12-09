package module.game.common.model;

public enum FinalWinner {
    First_Player("You won this game.", "You lost this game."),
    Second_Player("You lost this game.", "You won this game.");
    private final String messageForFirst;
    private final String messageForSecond;

    FinalWinner(String messageForFirst, String messageForSecond) {
        this.messageForFirst = messageForFirst;
        this.messageForSecond = messageForSecond;
    }

    public String getMessageForFirst() {
        return messageForFirst;
    }

    public String getMessageForSecond() {
        return messageForSecond;
    }
}

