package core.installer;

import core.container.DIContainer;
import module.game.create.abstraction.IGameThreadCreator;
import module.game.create.business.GameThreadCreator;
import module.game.evaluate.abstraction.IGameEvaluator;
import module.game.evaluate.business.RPSGameEvaluator;
import module.game.manage.abstraction.IGameManager;
import module.game.manage.business.RPSGameManager;
import module.game.move.abstraction.IPlayerMoveGetter;
import module.game.move.business.PlayerMoveGetter;
import module.message.send.abstraction.IMessageSender;
import module.message.send.business.MessageSender;
import module.packet.dispatch.abstraction.IPacketDispatcher;
import module.packet.receive.abstraction.IPacketReceiver;
import module.packet.dispatch.business.PacketDispatcher;
import module.packet.receive.business.PacketReceiver;
import module.queue.abstraction.IQueuesManager;
import module.queue.business.QueuesManager;
import module.service.abstraction.IServerService;
import module.service.business.ServerService;
import module.message.read.abstraction.IMessageReader;
import module.message.read.business.MessageReader;

public class DependencyInstaller {
    private static boolean installed = false;

    public static synchronized void install() {
        if (installed) {
            return;
        }

        DIContainer.register(IServerService.class, ServerService.class);
        DIContainer.register(IGameManager.class, RPSGameManager.class);
        DIContainer.register(IMessageReader.class, MessageReader.class);
        DIContainer.register(IPacketDispatcher.class, PacketDispatcher.class);
        DIContainer.register(IMessageSender.class, MessageSender.class);
        DIContainer.register(IQueuesManager.class, QueuesManager.class);
        DIContainer.register(IPacketReceiver.class, PacketReceiver.class);
        DIContainer.register(IGameThreadCreator.class, GameThreadCreator.class);
        DIContainer.register(IPlayerMoveGetter.class, PlayerMoveGetter.class);
        DIContainer.register(IGameEvaluator.class, RPSGameEvaluator.class);


        installed = true;
    }

}
