package com.blapiter.transactions;

import java.util.ArrayList;
import java.util.List;

public interface TransactionService {
    public List<Account> getPrioritizeAccounts(ArrayList<Transaction> collection);
}
