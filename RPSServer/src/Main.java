import core.container.DIContainer;
import core.installer.DependencyInstaller;
import module.service.abstraction.IServerService;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        DependencyInstaller.install();
        var serverService = DIContainer.resolve(IServerService.class);
        serverService.startProgram();
    }
}
