package service;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@ApplicationPath("/rest")
public class AppConfig extends Application {
    // Classe vazia - o Jersey faz scanning automático
    // das classes com anotações @Path
}