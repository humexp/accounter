package com.example.accounter.api;

import akka.http.javadsl.marshallers.jackson.Jackson;
import akka.http.javadsl.server.AllDirectives;
import akka.http.javadsl.server.Route;
import com.example.accounter.entity.Account;
import com.example.accounter.entity.Transfer;
import com.example.accounter.service.AccountService;

import static akka.http.javadsl.server.PathMatchers.longSegment;

public class Controller extends AllDirectives {
    public Route createRoute() {
        return route(
                createAccount(),
                getAccount(),
                getAccountList(),
                executeTransfer()
        );
    }

    private Route createAccount() {
        return put(() ->
                path("account/create", () ->
                        entity(Jackson.unmarshaller(Account.class), account ->
                                completeOK(AccountService.instance().createAccount(account), Jackson.marshaller())
                        )
                )
        );
    }

    private Route getAccount() {
        return get(() ->
                pathPrefix("account/get", () ->
                        path(longSegment(), (Long id) ->
                                    entity(Jackson.unmarshaller(Transfer.class), order ->
                                            completeOK(AccountService.instance().getAccount(id), Jackson.marshaller())
                                    )
                        )
                )
        );
    }

    private Route getAccountList() {
        return get(() ->
                path("account/all", () ->
                        entity(Jackson.unmarshaller(Transfer.class), order ->
                                completeOK(AccountService.instance().getAccountList(), Jackson.marshaller())
                        )
                )
        );
    }

    private Route executeTransfer() {
        return post(() ->
                path("transfer", () ->
                        entity(Jackson.unmarshaller(Transfer.class), transfer ->
                                completeOK(AccountService.instance().executeTransfer(transfer), Jackson.marshaller())
                        )
                )
        );
    }
}
