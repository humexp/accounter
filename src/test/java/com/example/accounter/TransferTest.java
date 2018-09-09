package com.example.accounter;

import akka.http.javadsl.testkit.JUnitRouteTest;
import com.example.accounter.api.Controller;
import com.example.accounter.entity.Transfer;
import org.junit.Test;


public class TransferTest extends JUnitRouteTest {
    private TransferTestUtil transferUtil = new TransferTestUtil(testRoute(new Controller().createRoute()));

    @Test
    public void simpleTransferTest() {
        transferUtil.createAccount("20100123124");
        transferUtil.createAccount("20100123125");

        Transfer transfer = transferUtil.executeTransfer("transfer_1000_22");

        transferUtil.checkAccountBalance("20100123124", "13999.78");
        transferUtil.checkAccountBalance("20100123125", "19000.22");
        transferUtil.checkTransferExistence(transfer);
    }

    @Test
    public void multipleTransfersTest() {
        transferUtil.createAccount("20100123124");
        transferUtil.createAccount("20100123125");

        transferUtil.executeTransfer("transfer_1000_22");
        transferUtil.executeTransfer("transfer_1500");
        transferUtil.executeTransfer("transfer_2400_56");

        transferUtil.checkAccountBalance("20100123124", "14900.34");
        transferUtil.checkAccountBalance("20100123125", "18099.66");
    }
}
