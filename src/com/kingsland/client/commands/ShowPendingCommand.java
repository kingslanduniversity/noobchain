package com.kingsland.client.commands;

import com.kingsland.blockchain.Blockchain;
import com.kingsland.blockchain.Transaction;
import com.kingsland.client.annotations.Command;

import java.util.List;

@Command("show pending")
public class ShowPendingCommand implements Executable {
    @Override
    public void execute(Blockchain blockchain) {
        List<Transaction> txs = blockchain.getPendingTransactions();
        if (txs.isEmpty()) {
            System.out.println("There are no pending transactions.");
        }
        for (Transaction tx : txs) {
            System.out.println(tx);
        }
    }
}
