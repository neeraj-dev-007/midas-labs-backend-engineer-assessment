package com.midas.app.workers;

import com.midas.app.activities.AccountActivity;
import com.midas.app.workflows.CreateAccountWorkflowImpl;
import com.midas.app.workflows.UpdateAccountWorkflowImpl;
import io.temporal.client.WorkflowClient;
import io.temporal.serviceclient.WorkflowServiceStubs;
import io.temporal.worker.Worker;
import io.temporal.worker.WorkerFactory;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AccountWorker {
  @Autowired private AccountActivity accountActivity;

  @PostConstruct
  public void accountWorker() {
    WorkflowServiceStubs service = WorkflowServiceStubs.newLocalServiceStubs();

    WorkflowClient client = WorkflowClient.newInstance(service);

    WorkerFactory factory = WorkerFactory.newInstance(client);

    Worker createWorker = factory.newWorker("create-account-workflow");

    Worker updateWorker = factory.newWorker("update-account-workflow");

    createWorker.registerWorkflowImplementationTypes(CreateAccountWorkflowImpl.class);

    createWorker.registerActivitiesImplementations(accountActivity);

    updateWorker.registerWorkflowImplementationTypes(UpdateAccountWorkflowImpl.class);

    updateWorker.registerActivitiesImplementations(accountActivity);

    factory.start();
  }
}
