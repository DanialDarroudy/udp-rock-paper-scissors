package module.packet.receive.abstraction;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public interface IPacketReceiver {
    DatagramPacket receive(DatagramSocket socket) throws IOException;
}
