package module.service.business;

import module.game.create.abstraction.IGameThreadCreator;
import module.packet.dispatch.abstraction.IPacketDispatcher;
import module.service.abstraction.IServerService;
import java.io.IOException;
import java.net.DatagramSocket;

public class ServerService implements IServerService {
    private static final int PORT = 8080;
    private final IPacketDispatcher packetDispatcher;
    private final IGameThreadCreator gameThreadCreator;

    public ServerService(IPacketDispatcher packetDispatcher, IGameThreadCreator gameThreadCreator) {
        this.packetDispatcher = packetDispatcher;
        this.gameThreadCreator = gameThreadCreator;
    }

    @Override
    public void startProgram() throws IOException {
        var socket = new DatagramSocket(PORT);
        System.out.println("UDP RPS Server started on port " + PORT);

        var dispatcherThread = packetDispatcher.dispatch(socket);
        var gameThread = gameThreadCreator.create(socket);

        try (socket) {
            dispatcherThread.join();
            gameThread.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
