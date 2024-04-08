package com.midas.app.providers.external.stripe;

import com.midas.app.exceptions.ApiException;
import com.midas.app.models.Account;
import com.midas.app.models.ProviderType;
import com.midas.app.providers.payment.CreateAccount;
import com.midas.app.providers.payment.PaymentProvider;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Customer;
import com.stripe.param.CustomerCreateParams;
import com.stripe.param.CustomerUpdateParams;
import jakarta.annotation.PostConstruct;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Getter
public class StripePaymentProvider implements PaymentProvider {
  private final Logger logger = LoggerFactory.getLogger(StripePaymentProvider.class);

  private final StripeConfiguration configuration;

  @PostConstruct
  public void init() {
    Stripe.apiKey = configuration.getApiKey();
  }

  /** providerName is the name of the payment provider */
  @Override
  public ProviderType providerName() {
    return ProviderType.stripe;
  }

  /**
   * createAccount creates a new account in the payment provider.
   *
   * @param details is the details of the account to be created.
   * @return Account
   */
  @Override
  public Account createAccount(CreateAccount details) throws ApiException {
    try {
      CustomerCreateParams customerCreateParams =
          CustomerCreateParams.builder()
              .setName(details.getFirstName() + " " + details.getLastName())
              .setEmail(details.getEmail())
              .build();

      Customer customer = Customer.create(customerCreateParams);

      return Account.builder()
          .firstName(details.getFirstName())
          .lastName(details.getLastName())
          .email(customer.getEmail())
          .providerType(this.providerName())
          .providerId(customer.getId())
          .build();
    } catch (StripeException e) {
      throw new ApiException(HttpStatus.valueOf((Integer) e.getStatusCode()), e.getMessage());
    }
  }

  /**
   * updateAccount updates account in the payment provider.
   *
   * @param details is the details of the account to be updated.
   * @param accountId is the providerId of account to be updated.
   * @return Account
   */
  @Override
  public Account updateAccount(CreateAccount details, String accountId) throws ApiException {
    try {
      Customer customer = Customer.retrieve(accountId);

      CustomerUpdateParams customerUpdateParams =
          CustomerUpdateParams.builder()
              .setName(details.getFirstName() + " " + details.getLastName())
              .setEmail(details.getEmail())
              .build();

      Customer updatedCustomer = customer.update(customerUpdateParams);

      return Account.builder()
          .firstName(details.getFirstName())
          .lastName(details.getLastName())
          .email(customer.getEmail())
          .providerType(this.providerName())
          .providerId(customer.getId())
          .build();
    } catch (StripeException e) {
      throw new ApiException(HttpStatus.valueOf((Integer) e.getStatusCode()), e.getMessage());
    }
  }
}
