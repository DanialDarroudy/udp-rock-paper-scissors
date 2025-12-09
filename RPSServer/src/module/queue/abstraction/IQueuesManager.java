package module.queue.abstraction;

import module.service.model.ClientInfo;

public interface IQueuesManager {
    void addMessage(ClientInfo clientInfo, String message);
    String popMessage(ClientInfo clientInfo);
    void addWaitingClient(ClientInfo client);
    ClientInfo popWaitingClient();
    int waitingSize();
    void removeClient(ClientInfo client);
}
