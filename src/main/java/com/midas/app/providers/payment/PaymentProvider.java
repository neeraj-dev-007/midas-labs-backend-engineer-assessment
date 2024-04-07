package com.midas.app.providers.payment;

import com.midas.app.exceptions.ApiException;
import com.midas.app.models.Account;
import com.midas.app.models.ProviderType;

public interface PaymentProvider {
  /** providerName is the name of the payment provider */
  ProviderType providerName();

  /**
   * createAccount creates a new account in the payment provider.
   *
   * @param details is the details of the account to be created.
   * @return Account
   */
  Account createAccount(CreateAccount details) throws ApiException;

  /**
   * updateAccount creates a new account in the payment provider.
   *
   * @param details is the details of the account to be created.
   * @param accountId is the accountId of the account to be updated.
   * @return Account
   */
  Account updateAccount(CreateAccount details, String accountId) throws ApiException;
}
