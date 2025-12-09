package module.interaction.business;

import module.interaction.abstraction.IServerInteractor;
import module.message.read.abstraction.IMessageReader;
import module.message.send.abstraction.IMessageSender;
import module.packet.receive.abstraction.IPacketReceiver;

import java.io.IOException;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class ServerInteractor implements IServerInteractor {
    private static final int SERVER_PORT = 8080;
    private static final String SERVER_DOMAIN = "localhost";
    private final IMessageSender messageSender;
    private final IPacketReceiver packetReceiver;
    private final IMessageReader messageReader;

    public ServerInteractor(IMessageSender messageSender, IPacketReceiver packetReceiver, IMessageReader messageReader) {
        this.messageSender = messageSender;
        this.packetReceiver = packetReceiver;
        this.messageReader = messageReader;
    }

    @Override
    public void interact(DatagramSocket socket) throws IOException {
        var scanner = new Scanner(System.in);
        var serverAddress = InetAddress.getByName(SERVER_DOMAIN);
        messageSender.send(socket, "connect", serverAddress, SERVER_PORT);

        while (true) {
            var packet = packetReceiver.receive(socket);
            var message = messageReader.read(packet);
            System.out.println("Server: " + message);

            if (message.contains("Waiting")) {
                continue;
            }

            if (message.contains("let's play") || message.equals("Please write 1 or 2 or 3")) {
                System.out.print("Enter your move (rock=1/paper=2/scissors=3): ");
                var move = scanner.nextLine();
                messageSender.send(socket, move, serverAddress, SERVER_PORT);
            }

            if (message.equals("You won this game.") || message.equals("You lost this game.")) {
                break;
            }
        }
    }
}
