package module.game.common.model;

public enum PlayerMove {
    ROCK(1),
    PAPER(2),
    SCISSORS(3);
    private final int code;

    PlayerMove(int code) {
        this.code = code;
    }

    public static PlayerMove fromMessage(String input) {
        return switch (input) {
            case "1" -> ROCK;
            case "2" -> PAPER;
            case "3" -> SCISSORS;
            default -> null;
        };
    }

    public int getCode() {
        return code;
    }
}
