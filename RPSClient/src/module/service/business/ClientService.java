package module.service.business;

import module.interaction.abstraction.IServerInteractor;
import module.service.abstraction.IClientService;

import java.io.IOException;
import java.net.DatagramSocket;

public class ClientService implements IClientService {
    private final IServerInteractor serverInteractor;

    public ClientService(IServerInteractor serverInteractor) {
        this.serverInteractor = serverInteractor;
    }

    @Override
    public void startProgram() throws IOException {
        var socket = new DatagramSocket();
        serverInteractor.interact(socket);
        socket.close();
    }
}
