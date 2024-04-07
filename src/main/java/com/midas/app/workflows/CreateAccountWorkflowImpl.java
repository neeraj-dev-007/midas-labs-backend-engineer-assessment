package com.midas.app.workflows;

import com.midas.app.activities.AccountActivity;
import com.midas.app.models.Account;
import io.temporal.activity.ActivityOptions;
import io.temporal.common.RetryOptions;
import io.temporal.workflow.Workflow;
import java.time.Duration;

public class CreateAccountWorkflowImpl implements CreateAccountWorkflow {

  private AccountActivity accountActivity;

  @Override
  public Account createAccount(Account details) {
    initialize();
    details = accountActivity.createPaymentAccount(details);
    return accountActivity.saveAccount(details);
  }

  private void initialize() {
    RetryOptions retryOptions =
        RetryOptions.newBuilder()
            .setInitialInterval(Duration.ofSeconds(1))
            .setMaximumInterval(Duration.ofSeconds(100))
            .setBackoffCoefficient(1)
            .setMaximumAttempts(5)
            .build();

    ActivityOptions options =
        ActivityOptions.newBuilder()
            .setStartToCloseTimeout(Duration.ofSeconds(60))
            .setRetryOptions(retryOptions)
            .build();

    accountActivity = Workflow.newActivityStub(AccountActivity.class, options);
  }
}
