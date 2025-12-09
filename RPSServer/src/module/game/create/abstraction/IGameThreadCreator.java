package module.game.create.abstraction;

import java.net.DatagramSocket;

public interface IGameThreadCreator {
    Thread create(DatagramSocket socket);
}
