package com.example.accounter.config;

import akka.NotUsed;
import akka.actor.ActorSystem;
import akka.http.javadsl.ConnectHttp;
import akka.http.javadsl.Http;
import akka.http.javadsl.model.HttpRequest;
import akka.http.javadsl.model.HttpResponse;
import akka.stream.ActorMaterializer;
import akka.stream.javadsl.Flow;
import com.example.accounter.api.Controller;

public class ControllerConfiguration {
    public static final String HOST = "localhost";
    public static final int PORT = 8080;

    public static void init(ActorSystem system) {
        ActorMaterializer materializer = ActorMaterializer.create(system);
        ConnectHttp connect = ConnectHttp.toHost(HOST, PORT);

        Flow<HttpRequest, HttpResponse, NotUsed> routeFlow = new Controller().createRoute()
                .flow(system, materializer);

        Http.get(system).bindAndHandle(routeFlow, connect, materializer);
    }
}