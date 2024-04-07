package com.midas.app.workers;

import com.midas.app.activities.AccountActivityImpl;
import com.midas.app.workflows.CreateAccountWorkflowImpl;
import io.temporal.client.WorkflowClient;
import io.temporal.serviceclient.WorkflowServiceStubs;
import io.temporal.worker.Worker;
import io.temporal.worker.WorkerFactory;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AccountWorker {
  @Autowired private AccountActivityImpl accountActivity;

  @PostConstruct
  public void createWorker() {
    WorkflowServiceStubs service = WorkflowServiceStubs.newLocalServiceStubs();

    WorkflowClient client = WorkflowClient.newInstance(service);

    WorkerFactory factory = WorkerFactory.newInstance(client);

    Worker worker = factory.newWorker("create-account-workflow");

    worker.registerWorkflowImplementationTypes(CreateAccountWorkflowImpl.class);

    worker.registerActivitiesImplementations(accountActivity);

    factory.start();
  }
}
