package module.game.common.model;

import module.service.model.ClientInfo;

public class RPSGame {
    private final ClientInfo firstPlayer;
    private final ClientInfo secondPlayer;
    private int currentRound = 0;
    private int firstPlayerScore = 0;
    private int secondPlayerScore = 0;

    public RPSGame(ClientInfo firstPlayer, ClientInfo secondPlayer) {
        this.firstPlayer = firstPlayer;
        this.secondPlayer = secondPlayer;
    }

    public boolean isFinished() {
        return Math.max(firstPlayerScore, secondPlayerScore) >= 3;
    }

    public void nextRound(ResultMove result) {
        currentRound++;
        switch (result) {
            case First_Player_WIN -> firstPlayerScore++;
            case Second_Player_WIN -> secondPlayerScore++;
            case DRAW -> { /* no score change */ }
        }
    }

    public ClientInfo getFirstPlayer() {
        return firstPlayer;
    }

    public ClientInfo getSecondPlayer() {
        return secondPlayer;
    }

    public int getCurrentRound() {
        return currentRound;
    }

    public void setCurrentRound(int currentRound) {
        this.currentRound = currentRound;
    }

    public FinalWinner getFinalWinner() {
        return (firstPlayerScore > secondPlayerScore) ? FinalWinner.First_Player : FinalWinner.Second_Player;
    }
}
