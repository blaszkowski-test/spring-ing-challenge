package com.blapiter.transactions;

import java.util.Objects;

public class Account implements Comparable<Account> {

    protected String account;
    protected Integer debitCount;
    protected Integer creditCount;
    protected Double balance;

    Account(String account, Integer debitCount, Integer creditCount, Double balance) {
      this.account = account;
      this.debitCount = debitCount;
      this.creditCount = creditCount;
      this.balance = balance;
    }

    public String getAccount() {
      return this.account;
    }

    public Integer getDebitCount() {
      return this.debitCount;
    }

    public Integer getCreditCount() {
      return this.creditCount;
    }

    public Double getBalance() {
      return this.balance;
    }

    public void incrementDebitCount() {
      ++this.debitCount;
    }

    public void incrementCreditCount() {
      ++this.creditCount;
    }

    public void addBalaceWithAmount(Double amount) {
      this.balance += amount;
    }

    public void substractBalaceWithAmount(Double amount) {
      this.balance -= amount;
    }

    @Override
    public int compareTo(Account other) {
      return this.account.compareTo(other.account);
    }

    @Override
    public int hashCode() {
      return Objects.hash(this.account);
    }

    @Override
    public boolean equals(Object object) {
      if (object == null) {
        return false;
      }

      if (this.getClass() != object.getClass()) {
        return false;
      }

      final Account other = (Account) object;

      return this.account.equals(other.account);
    }

    @Override
    public String toString() {
      return "Account [account=" + account + ", debitCount=" + debitCount + ",creditCount=" + creditCount + ",balance="
          + balance + "]";
    }
  }
