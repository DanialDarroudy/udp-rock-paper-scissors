package module.packet.dispatch.abstraction;

import java.net.DatagramSocket;

public interface IPacketDispatcher {
    Thread dispatch(DatagramSocket socket);
}
