package module.interaction.abstraction;

import java.io.IOException;
import java.net.DatagramSocket;

public interface IServerInteractor {
    void interact(DatagramSocket socket) throws IOException;
}
