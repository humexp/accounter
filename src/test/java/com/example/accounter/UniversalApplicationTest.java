package com.example.accounter;

import akka.http.javadsl.model.*;
import akka.http.javadsl.testkit.JUnitRouteTest;
import akka.http.javadsl.testkit.TestRoute;
import com.example.accounter.api.Controller;
import com.example.accounter.util.Dataset;
import org.junit.Test;


public class UniversalApplicationTest extends JUnitRouteTest {
    private final TestRoute appRoute = testRoute(new Controller().createRoute());

    @Test
    public void simpleTransferTest() {
        createAccount("20100123124");
        createAccount("20100123125");

        executeTransfer("transfer_1000_22");
        //checkAccountBalance("20100123124", "");
        //checkAccountBalance("20100123125", "");
    }

    @Test
    public void wrongBallanceTransferTest() {
        createAccount("20100123124");
        createAccount("20100123125");

        // check exception
        executeTransfer("transfer_100000_22");

        // check balance not changed
    }

    private HttpResponse createAccount(String name) {
        String jsonEntity = Dataset.getAsString("account", name);
        HttpEntity.Strict entity = HttpEntities.create(ContentTypes.APPLICATION_JSON, jsonEntity);

        // drop if exists

        return appRoute.run(HttpRequest.PUT("/api/account").withEntity(entity))
                .assertStatusCode(200)
                .response();
    }

    private HttpResponse executeTransfer(String name) {
        return null;
    }
}
