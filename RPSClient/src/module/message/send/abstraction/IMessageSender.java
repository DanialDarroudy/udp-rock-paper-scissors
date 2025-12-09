package module.message.send.abstraction;

import java.io.IOException;
import java.net.DatagramSocket;
import java.net.InetAddress;

public interface IMessageSender {
    void send(DatagramSocket socket, String message, InetAddress address, int port) throws IOException;
}
