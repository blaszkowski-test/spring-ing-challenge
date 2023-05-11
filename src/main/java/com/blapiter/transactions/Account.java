package com.blapiter.transactions;

import java.math.BigDecimal;
import java.util.Objects;

public class Account implements Comparable<Account> {

    protected String account;
    protected Integer debitCount;
    protected Integer creditCount;
    protected BigDecimal balance;

    Account(String account, Integer debitCount, Integer creditCount, BigDecimal balance) {
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

    public BigDecimal getBalance() {
      return this.balance;
    }

    public void incrementDebitCount() {
      ++this.debitCount;
    }

    public void incrementCreditCount() {
      ++this.creditCount;
    }

    public void addAmountToBalance(BigDecimal amount) {
      this.balance = this.balance.add(amount);
    }

    public void subtractAmountFromBalance(BigDecimal amount) {
      this.balance = this.balance.subtract(amount);
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
