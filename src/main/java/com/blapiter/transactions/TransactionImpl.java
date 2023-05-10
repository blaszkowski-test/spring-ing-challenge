package com.blapiter.transactions;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@Qualifier("TransactionsImpl")
public class TransactionImpl implements TransactionService {
    @Override
    public List<Account> getPrioritizeAccounts(ArrayList<Transaction> transactions) {
        Map<String, Account> accounts = new TreeMap<>();
        Account currentAccount = null;
        for (Transaction transaction : transactions) {
            this.addCreditAccount(accounts, transaction);
            this.addDepitAccount(accounts, transaction);

            currentAccount = accounts.get(transaction.creditAccount());
            currentAccount.incrementCreditCount();
            currentAccount.addBalaceWithAmount(transaction.amount());

            currentAccount = accounts.get(transaction.debitAccount());
            currentAccount.incrementDebitCount();
            currentAccount.substractBalaceWithAmount(transaction.amount());
        }
        return accounts.entrySet().stream().map(item -> item.getValue()).toList();
    }

    protected void addCreditAccount(Map<String, Account> accounts, Transaction transaction) {
        if (!accounts.containsKey(transaction.creditAccount())) {
            accounts.put(
                    transaction.creditAccount(),
                    new Account(transaction.creditAccount(), 0, 0, 0.0));
        }
    }

    protected void addDepitAccount(Map<String, Account> accounts, Transaction transaction) {
        if (!accounts.containsKey(transaction.debitAccount())) {
            accounts.put(
                    transaction.debitAccount(),
                    new Account(transaction.debitAccount(), 0, 0, 0.0));
        }
    }
}
