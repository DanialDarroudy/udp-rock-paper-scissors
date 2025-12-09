package module.queue.business;

import module.service.model.ClientInfo;
import module.queue.abstraction.IQueuesManager;

import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

public class QueuesManager implements IQueuesManager {
    private final Map<ClientInfo, Queue<String>> clientMessageQueues = new ConcurrentHashMap<>();
    private final Queue<ClientInfo> waitingClients = new ConcurrentLinkedQueue<>();

    @Override
    public void addMessage(ClientInfo clientInfo, String message) {
        clientMessageQueues.computeIfAbsent(clientInfo, k -> new ConcurrentLinkedQueue<>()).offer(message);
    }

    @Override
    public String popMessage(ClientInfo clientInfo) {
        var messageQueue = clientMessageQueues.get(clientInfo);
        return messageQueue.poll();
    }

    public void addWaitingClient(ClientInfo client) {
        waitingClients.offer(client);
        clientMessageQueues.computeIfAbsent(client, k -> new ConcurrentLinkedQueue<>());
    }

    public ClientInfo popWaitingClient() {
        return waitingClients.poll();
    }

    public int waitingSize() {
        return waitingClients.size();
    }

    public void removeClient(ClientInfo client) {
        clientMessageQueues.remove(client);
    }
}
