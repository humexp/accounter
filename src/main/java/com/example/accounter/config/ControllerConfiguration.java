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
    public static void init(ActorSystem system) {
        Http http = Http.get(system);
        ActorMaterializer materializer = ActorMaterializer.create(system);
        ConnectHttp connect = ConnectHttp.toHost("localhost", 8080);


        Controller accountController = new Controller();
        Flow<HttpRequest, HttpResponse, NotUsed> routeFlow = accountController.createRoute().flow(system, materializer);
        http.bindAndHandle(routeFlow, connect, materializer);
    }
}
