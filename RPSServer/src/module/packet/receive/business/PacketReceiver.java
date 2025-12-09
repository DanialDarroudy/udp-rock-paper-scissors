package module.packet.receive.business;

import module.packet.receive.abstraction.IPacketReceiver;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class PacketReceiver implements IPacketReceiver {
    private static final int BUF_SIZE = 2048;

    @Override
    public DatagramPacket receive(DatagramSocket socket) throws IOException {
        var buffer = new byte[BUF_SIZE];
        var packet = new DatagramPacket(buffer, BUF_SIZE);
        socket.receive(packet);
        return packet;
    }
}
