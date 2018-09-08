package com.example.accounter;

import akka.http.javadsl.marshallers.jackson.Jackson;
import akka.http.javadsl.model.ContentTypes;
import akka.http.javadsl.model.HttpEntities;
import akka.http.javadsl.model.HttpEntity;
import akka.http.javadsl.model.HttpRequest;
import akka.http.javadsl.testkit.TestRoute;
import com.example.accounter.entity.Account;
import com.example.accounter.entity.Transfer;
import com.example.accounter.service.AccountService;
import com.example.accounter.service.TransferService;
import com.example.accounter.util.Dataset;

import static org.junit.Assert.assertEquals;

class TransferTestUtil {
    private TestRoute appRoute;

    TransferTestUtil(TestRoute appRoute) {
        this.appRoute = appRoute;
    }

    void createAccount(String name) {
        Long accountId = Long.parseLong(name);
        String jsonEntity = Dataset.getAsString("account", name);

        Account account = AccountService.instance().getAccount(accountId);
        if (account != null) {
            AccountService.instance().deleteAccount(accountId);
        }

        HttpEntity.Strict entity = HttpEntities.create(ContentTypes.APPLICATION_JSON, jsonEntity);
        appRoute.run(HttpRequest.PUT("/api/account").withEntity(entity))
                .assertStatusCode(200)
                .response();
    }

    Transfer executeTransfer(String name) {
        String jsonEntity = Dataset.getAsString("transfer", name);

        HttpEntity.Strict entity = HttpEntities.create(ContentTypes.APPLICATION_JSON, jsonEntity);
        return appRoute.run(HttpRequest.POST("/api/transfer").withEntity(entity))
                .assertStatusCode(200)
                .entity(Jackson.unmarshaller(Transfer.class));
    }

    void checkAccountBalance(String name, String expectedSum) {
        Long accountId = Long.parseLong(name);
        Account account = AccountService.instance().getAccount(accountId);

        assertEquals(expectedSum, account.getBalance());
    }

    void checkTransferExistence(Transfer expectedTransfer) {
        Transfer actualTransfer = TransferService.instance().getTransfer(expectedTransfer.getId());

        assertEquals(expectedTransfer.getAccountFrom(), actualTransfer.getAccountFrom());
        assertEquals(expectedTransfer.getAccountTo(), actualTransfer.getAccountTo());
        assertEquals(expectedTransfer.getSum(), actualTransfer.getSum());
    }
}
