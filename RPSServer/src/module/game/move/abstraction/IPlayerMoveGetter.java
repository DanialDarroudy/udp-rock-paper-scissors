package module.game.move.abstraction;

import module.game.common.model.PlayerMove;
import module.service.model.ClientInfo;

import java.io.IOException;
import java.net.DatagramSocket;

public interface IPlayerMoveGetter {
    PlayerMove fetch(DatagramSocket socket, ClientInfo player) throws IOException;
}
