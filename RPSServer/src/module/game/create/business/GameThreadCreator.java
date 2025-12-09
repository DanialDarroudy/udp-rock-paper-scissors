package module.game.create.business;

import module.game.manage.abstraction.IGameManager;
import module.game.create.abstraction.IGameThreadCreator;
import module.queue.abstraction.IQueuesManager;

import java.io.IOException;
import java.net.DatagramSocket;

public class GameThreadCreator implements IGameThreadCreator {
    private final IQueuesManager queuesManager;
    private final IGameManager gameManager;

    public GameThreadCreator(IQueuesManager queuesManager, IGameManager gameManager) {
        this.queuesManager = queuesManager;
        this.gameManager = gameManager;
    }

    @Override
    public Thread create(DatagramSocket socket) {
        var gameThread = new Thread(() -> {
            while (true) {
                if (queuesManager.waitingSize() >= 2) {
                    var firstClientInfo = queuesManager.popWaitingClient();
                    var secondClientInfo = queuesManager.popWaitingClient();
                    new Thread(() -> {
                        try {
                            gameManager.startGame(socket, firstClientInfo, secondClientInfo);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }).start();
                }
            }
        });
        gameThread.start();
        return gameThread;
    }
}
