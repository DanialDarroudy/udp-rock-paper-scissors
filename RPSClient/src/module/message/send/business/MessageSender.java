package module.message.send.business;

import module.message.send.abstraction.IMessageSender;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class MessageSender implements IMessageSender {
    public void send(DatagramSocket socket, String message, InetAddress address, int port) throws IOException {
        var data = message.getBytes();
        var packet = new DatagramPacket(data, data.length, address, port);
        socket.send(packet);
    }
}
