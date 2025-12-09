package core.installer;

import core.container.DIContainer;
import module.interaction.abstraction.IServerInteractor;
import module.interaction.business.ServerInteractor;
import module.message.read.abstraction.IMessageReader;
import module.message.read.business.MessageReader;
import module.message.send.abstraction.IMessageSender;
import module.message.send.business.MessageSender;
import module.packet.receive.abstraction.IPacketReceiver;
import module.packet.receive.business.PacketReceiver;
import module.service.abstraction.IClientService;
import module.service.business.ClientService;

public class DependencyInstaller {
    private static boolean installed = false;

    public static synchronized void install() {
        if (installed) {
            return;
        }

        DIContainer.register(IClientService.class, ClientService.class);
        DIContainer.register(IServerInteractor.class, ServerInteractor.class);
        DIContainer.register(IMessageSender.class, MessageSender.class);
        DIContainer.register(IMessageReader.class, MessageReader.class);
        DIContainer.register(IPacketReceiver.class, PacketReceiver.class);


        installed = true;
    }

}
