package com.example.accounter.api;

import akka.http.javadsl.marshallers.jackson.Jackson;
import akka.http.javadsl.server.AllDirectives;
import akka.http.javadsl.server.PathMatcher0;
import akka.http.javadsl.server.PathMatchers;
import akka.http.javadsl.server.Route;
import com.example.accounter.entity.Account;
import com.example.accounter.entity.Transfer;
import com.example.accounter.service.AccountService;

import static akka.http.javadsl.server.PathMatchers.longSegment;

public class Controller extends AllDirectives {
    private static final PathMatcher0 ROOT = PathMatchers.segment("api");

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
                path(ROOT.slash("account"), () ->
                        entity(Jackson.unmarshaller(Account.class), account ->
                                completeOK(AccountService.instance().createAccount(account), Jackson.marshaller())
                        )
                )
        );
    }

    private Route getAccount() {
        return get(() ->
                pathPrefix(ROOT.slash("account"), () ->
                        path(longSegment(), (Long id) ->

                                completeOK(AccountService.instance().getAccount(id), Jackson.marshaller())
                        )
                )
        );
    }

    private Route getAccountList() {
        return get(() ->
                path(ROOT.slash("account").slash("all"), () ->
                        completeOK(AccountService.instance().getAccountList(), Jackson.marshaller())
                )
        );
    }

    private Route executeTransfer() {
        return post(() ->
                path(ROOT.slash("transfer"), () ->
                        entity(Jackson.unmarshaller(Transfer.class), transfer ->
                                completeOK(AccountService.instance().executeTransfer(transfer), Jackson.marshaller())
                        )
                )
        );
    }
}
