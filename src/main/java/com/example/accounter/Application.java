package com.example.accounter;

import akka.actor.ActorSystem;
import akka.http.javadsl.server.AllDirectives;
import com.example.accounter.config.ControllerConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.example.accounter.config.ControllerConfiguration.HOST;
import static com.example.accounter.config.ControllerConfiguration.PORT;


public class Application {
    private static final Logger logger = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        ActorSystem system = ActorSystem.create("accounter");
        ControllerConfiguration.init(system);

        logger.info("Server started at http://" + HOST + ":" + PORT);
    }
}
