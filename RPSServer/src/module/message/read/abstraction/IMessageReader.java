package module.message.read.abstraction;

import java.net.DatagramPacket;

public interface IMessageReader {
    String read(DatagramPacket packet);
}
