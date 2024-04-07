package com.midas.app.activities;

import com.midas.app.exceptions.ApiException;
import com.midas.app.models.Account;
import com.midas.app.models.ProviderType;
import com.midas.app.providers.payment.CreateAccount;
import com.midas.app.providers.payment.PaymentProvider;
import com.midas.app.repositories.AccountRepository;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AccountActivityImpl implements AccountActivity {
  private PaymentProvider stripePaymentProvider;

  private AccountRepository accountRepository;

  private final Logger logger = LoggerFactory.getLogger(AccountActivityImpl.class);

  @Override
  public Account saveAccount(Account account) {
    logger.info("Saving Account details to DB for user with email: {}", account.getEmail());
    return accountRepository.save(account);
  }

  @Override
  public Account createPaymentAccount(Account account) {
    logger.info("Creating Payment Account initiated for user with email: {}", account.getEmail());
    var details = new CreateAccount();
    details.setFirstName(account.getFirstName());
    details.setLastName(account.getLastName());
    details.setEmail(account.getEmail());
    try {
      return stripePaymentProvider.createAccount(details);
    } catch (ApiException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public Account updateAccount(Account account) {
    logger.info("Updating Account details to DB for user with email: {}", account.getEmail());
    return accountRepository.save(account);
  }

  @Override
  public Account updatePaymentAccount(Account account) {
    logger.info("Updating Payment Account for user with email: {}", account.getEmail());
    var details = new CreateAccount();
    details.setFirstName(account.getFirstName());
    details.setLastName(account.getLastName());
    details.setEmail(account.getEmail());
    try {
      if (account.getProviderType().equals(ProviderType.stripe)) {
        return stripePaymentProvider.updateAccount(details, account.getProviderId());
      }
      return null;
    } catch (ApiException e) {
      throw new RuntimeException(e);
    }
  }
}
