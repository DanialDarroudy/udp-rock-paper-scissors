import core.container.DIContainer;
import core.installer.DependencyInstaller;
import module.service.abstraction.IClientService;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        DependencyInstaller.install();
        var clientService = DIContainer.resolve(IClientService.class);
        clientService.startProgram();
    }
}
