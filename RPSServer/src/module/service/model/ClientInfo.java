package module.service.model;

import java.net.InetAddress;
import java.util.Objects;

public record ClientInfo(InetAddress address, int port) {

    @Override
    public boolean equals(Object clientInfo) {
        if (this == clientInfo) {
            return true;
        }
        if (!(clientInfo instanceof ClientInfo that)) {
            return false;
        }
        return port == that.port && Objects.equals(address, that.address);
    }

    @Override
    public String toString() {
        return address.getHostAddress() + ":" + port;
    }
}
