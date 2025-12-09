package module.message.send.business;

import module.service.model.ClientInfo;
import module.message.send.abstraction.IMessageSender;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;


public class MessageSender implements IMessageSender {

    @Override
    public void send(DatagramSocket socket, String message, ClientInfo clientInfo) throws IOException {
        var data = message.getBytes();
        var packet = new DatagramPacket(data, data.length, clientInfo.address(), clientInfo.port());
        socket.send(packet);
    }
}
