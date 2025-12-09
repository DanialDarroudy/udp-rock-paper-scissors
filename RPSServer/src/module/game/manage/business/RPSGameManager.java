package module.game.manage.business;

import module.game.common.model.FinalWinner;
import module.game.common.model.RPSGame;
import module.game.common.model.ResultMove;
import module.game.evaluate.abstraction.IGameEvaluator;
import module.game.move.abstraction.IPlayerMoveGetter;
import module.game.manage.abstraction.IGameManager;
import module.message.send.abstraction.IMessageSender;
import module.queue.abstraction.IQueuesManager;
import module.service.model.ClientInfo;

import java.io.IOException;
import java.net.DatagramSocket;

public class RPSGameManager implements IGameManager {
    private final IMessageSender messageSender;
    private final IQueuesManager queuesManager;
    private final IPlayerMoveGetter playerMoveGetter;
    private final IGameEvaluator gameEvaluator;

    public RPSGameManager(IMessageSender messageSender, IQueuesManager queuesManager, IPlayerMoveGetter playerMoveGetter, IGameEvaluator gameEvaluator) {
        this.messageSender = messageSender;
        this.queuesManager = queuesManager;
        this.playerMoveGetter = playerMoveGetter;
        this.gameEvaluator = gameEvaluator;
    }

    @Override
    public void startGame(DatagramSocket socket, ClientInfo firstPlayer, ClientInfo secondPlayer) throws IOException {
        var rpsGame = new RPSGame(firstPlayer, secondPlayer);

        while (!rpsGame.isFinished()) {
            var round = rpsGame.getCurrentRound();
            sendLetsPlayMessage(round, socket, firstPlayer, secondPlayer);

            var firstPlayerMove = playerMoveGetter.fetch(socket, firstPlayer);
            var secondPlayerMove = playerMoveGetter.fetch(socket, secondPlayer);
            var resultMove = gameEvaluator.evaluate(firstPlayerMove, secondPlayerMove);
            rpsGame.nextRound(resultMove);

            sendResultMoveMessages(resultMove, socket, firstPlayer, secondPlayer);
        }
        var finalWinner = rpsGame.getFinalWinner();

        sendFinalMessage(finalWinner, socket, firstPlayer, secondPlayer);
        removePlayers(firstPlayer, secondPlayer);
    }

    private void sendLetsPlayMessage(int round, DatagramSocket socket, ClientInfo firstPlayer, ClientInfo secondPlayer) throws IOException {
        var letsPlayMessage = "Round " + round + " - let's play";
        messageSender.send(socket, letsPlayMessage, firstPlayer);
        messageSender.send(socket, letsPlayMessage, secondPlayer);
    }

    private void sendResultMoveMessages(ResultMove resultMove, DatagramSocket socket, ClientInfo firstPlayer, ClientInfo secondPlayer) throws IOException {
        messageSender.send(socket, resultMove.getMessageForFirstPlayer(), firstPlayer);
        messageSender.send(socket, resultMove.getMessageForSecondPlayer(), secondPlayer);
    }

    private void sendFinalMessage(FinalWinner finalWinner, DatagramSocket socket, ClientInfo firstPlayer, ClientInfo secondPlayer) throws IOException {
        messageSender.send(socket, finalWinner.getMessageForFirst(), firstPlayer);
        messageSender.send(socket, finalWinner.getMessageForSecond(), secondPlayer);
    }
    private void removePlayers(ClientInfo firstPlayer, ClientInfo secondPlayer){
        queuesManager.removeClient(firstPlayer);
        queuesManager.removeClient(secondPlayer);
    }
}
