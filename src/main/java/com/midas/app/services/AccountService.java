package com.midas.app.services;

import com.midas.app.models.Account;
import java.util.List;
import java.util.UUID;

public interface AccountService {
  /**
   * createAccount creates a new account in the system or provider.
   *
   * @param details is the details of the account to be created.
   * @return Account
   */
  Account createAccount(Account details);

  /**
   * getAccounts returns a list of accounts.
   *
   * @return List<Account>
   */
  List<Account> getAccounts();

  /**
   * getAccountById returns the details of account with given Id
   *
   * @return Account
   */
  Account getAccountById(UUID id);

  /**
   * updateAccount updates account in the system or provider.
   *
   * @param details is the details of the account to be updated.
   * @return Account
   */
  Account updateAccount(Account details);
}
