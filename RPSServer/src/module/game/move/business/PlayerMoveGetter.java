package module.game.move.business;

import module.game.common.model.PlayerMove;
import module.game.move.abstraction.IPlayerMoveGetter;
import module.message.send.abstraction.IMessageSender;
import module.queue.abstraction.IQueuesManager;
import module.service.model.ClientInfo;

import java.io.IOException;
import java.net.DatagramSocket;

public class PlayerMoveGetter implements IPlayerMoveGetter {
    private final IMessageSender messageSender;
    private final IQueuesManager queuesManager;

    public PlayerMoveGetter(IMessageSender messageSender, IQueuesManager queuesManager) {
        this.messageSender = messageSender;
        this.queuesManager = queuesManager;
    }

    @Override
    public PlayerMove fetch(DatagramSocket socket, ClientInfo player) throws IOException {
        String playerMessage = null;
        PlayerMove playerMove = null;
        while (playerMessage == null) {
            playerMessage = queuesManager.popMessage(player);
            if (playerMessage != null){
                playerMove = PlayerMove.fromMessage(playerMessage);
                if (playerMove == null){
                    messageSender.send(socket, "Please write 1 or 2 or 3", player);
                    playerMessage = null;
                }
            }
        }
        return playerMove;
    }
}
