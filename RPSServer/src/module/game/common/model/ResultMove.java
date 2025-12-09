package module.game.common.model;

public enum ResultMove {
    First_Player_WIN("You won this round.", "You lost this round."),
    Second_Player_WIN("You lost this round.", "You won this round."),
    DRAW("You tied this round.", "You tied this round.");

    private final String messageForFirstPlayer;
    private final String messageForSecondPlayer;

    ResultMove(String messageForFirstPlayer, String messageForSecondPlayer) {
        this.messageForFirstPlayer = messageForFirstPlayer;
        this.messageForSecondPlayer = messageForSecondPlayer;
    }

    public String getMessageForFirstPlayer() {
        return messageForFirstPlayer;
    }

    public String getMessageForSecondPlayer() {
        return messageForSecondPlayer;
    }
}