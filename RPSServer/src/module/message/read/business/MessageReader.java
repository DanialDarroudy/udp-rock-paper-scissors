package module.message.read.business;

import module.message.read.abstraction.IMessageReader;

import java.net.DatagramPacket;

public class MessageReader implements IMessageReader {
    @Override
    public String read(DatagramPacket packet) {
        return new String(packet.getData(), 0, packet.getLength()).trim();
    }
}
