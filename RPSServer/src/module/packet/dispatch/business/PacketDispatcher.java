package module.packet.dispatch.business;

import module.service.model.ClientInfo;
import module.message.read.abstraction.IMessageReader;
import module.message.send.abstraction.IMessageSender;
import module.packet.dispatch.abstraction.IPacketDispatcher;
import module.packet.receive.abstraction.IPacketReceiver;
import module.queue.abstraction.IQueuesManager;

import java.net.DatagramSocket;

public class PacketDispatcher implements IPacketDispatcher {
    private final IMessageReader messageReader;
    private final IQueuesManager queuesManager;
    private final IPacketReceiver packetReceiver;
    private final IMessageSender messageSender;

    public PacketDispatcher(IMessageReader messageReader, IQueuesManager queuesManager, IPacketReceiver packetReceiver, IMessageSender messageSender) {
        this.messageReader = messageReader;
        this.queuesManager = queuesManager;
        this.packetReceiver = packetReceiver;
        this.messageSender = messageSender;
    }

    @Override
    public Thread dispatch(DatagramSocket socket) {
        var dispatcherThread = new Thread(() -> {
            while (true) {
                try {
                    var packet = packetReceiver.receive(socket);
                    var message = messageReader.read(packet);
                    var client = new ClientInfo(packet.getAddress(), packet.getPort());

                    if (message.equalsIgnoreCase("connect")) {
                        queuesManager.addWaitingClient(client);
                        messageSender.send(socket, "Waiting for opponent...", client);
                    } else {
                        queuesManager.addMessage(client, message);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        dispatcherThread.start();
        return dispatcherThread;
    }
}
