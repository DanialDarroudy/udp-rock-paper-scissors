package module.game.manage.abstraction;

import module.service.model.ClientInfo;

import java.io.IOException;
import java.net.DatagramSocket;

public interface IGameManager {
    void startGame(DatagramSocket socket, ClientInfo firstPlayer, ClientInfo secondPlayer) throws IOException;
}