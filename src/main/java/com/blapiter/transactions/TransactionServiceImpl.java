package com.blapiter.transactions;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@Qualifier("TransactionsImpl")
public class TransactionServiceImpl implements TransactionService {
    @Override
    public List<Account> getSortedAccounts(ArrayList<Transaction> transactions) {
        Map<String, Account> accounts = new TreeMap<>();
        for (Transaction transaction : transactions) {
            this.addAccount(accounts, transaction.creditAccount());
            this.addAccount(accounts, transaction.debitAccount());
            this.updateCredit(accounts.get(transaction.creditAccount()), transaction);
            this.updateDebit(accounts.get(transaction.debitAccount()), transaction);
        }
        return accounts.entrySet().stream().map(item -> item.getValue()).toList();
    }

    protected void addAccount(Map<String, Account> accounts, String accountNumber) {
        if (!accounts.containsKey(accountNumber)) {
            accounts.put(accountNumber, new Account(accountNumber));
        }
    }

    protected void updateCredit(Account account, Transaction transaction) {
        account.incrementCreditCount();
        account.addAmountToBalance(transaction.amount());
    }

    protected void updateDebit(Account account, Transaction transaction) {
        account.incrementDebitCount();
        account.subtractAmountFromBalance(transaction.amount());
    }
}
