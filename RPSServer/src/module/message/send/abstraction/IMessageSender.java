package module.message.send.abstraction;

import module.service.model.ClientInfo;

import java.io.IOException;
import java.net.DatagramSocket;

public interface IMessageSender {
    void send(DatagramSocket socket, String message, ClientInfo clientInfo) throws IOException;
}
