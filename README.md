# Introduction

Welcome to the Midas Labs Backend Engineer Assessment repository! This project is part of the assessment process for backend engineers at Midas Labs. It is forked from the original assessment repository to develop and showcase the required skills.

This repository contains a pre-existing Spring Boot Application developed with Java 21 and leveraging the Temporal Workflow Engine for orchestrating business logic. The main task involves integrating the Stripe payment processing service to manage customer data upon user signup.


## Tasks Completed

- Integrated the Stripe payment processing service into the Spring Boot Application, facilitating seamless handling of customer transactions.
- Implemented temporal workflows, activities, and workers to execute essential tasks, including customer creation and updates, ensuring efficient business logic orchestration.
- Created a multi-stage Dockerfile to streamline the deployment process, incorporating build and run stages for efficient containerization.
- Updated the compose.yaml file to align with Docker deployment requirements.
- [Postman Workspace for API Testing](https://www.postman.com/solar-water-291838-1/workspace/midas-labs-backend-engineer-assessment/overview)

For detailed instructions on setting up the project, please refer to the following section.


## Setup Instructions 

To run the application you would require:

- Java
- Temporal
- Docker
- Postman

You are required to first start the temporal server using the following command

```sh
temporal server start-dev
```

and then run the application using the following command or using your IDE.

```sh
./gradlew bootRun
```

Test APIs using the workspace:-
[Postman Workspace for API Testing](https://www.postman.com/solar-water-291838-1/workspace/midas-labs-backend-engineer-assessment/overview)


## Implementation Approach and Assumptions 

### Stripe Integration:

- Utilize the Stripe Java SDK to integrate Stripe payment processing service into the Spring Boot Application.
- StripePaymentProvider class to make Stripe API calls for customer creation and updates upon user signup.
- Change Account model to store additional fields like providerId and providerType returned by Stripe.

### Temporal Workflow Engine Integration:

- Leverage the Temporal Workflow Engine to orchestrate business logic efficiently.
- Define 2 temporal workflows:
  - Create Account Workflow: This workflow will handle account creation and consists of following 2 activities
    - Create Payment Account Activity: To create account with Stripe.
    - Save Account to DB Activity: To save account with updated fields like providerType and providerId to PostgreSQL DB.
  - Update Account Workflow: This workflow will handle account updation and consists of following 2 activities
    - Update Payment Account Activity: To update account with Stripe.
    - Update Account to DB Activity: To update account in PostgreSQL DB.
- Create Account Workers listening to _create-account-workflow_ and _update-account-workflow_ task queues to handle tasks such as customer creation and updates seamlessly.
- Implement retry and error handling mechanisms within temporal workflows to ensure fault tolerance and reliability.

### Dockerization:

- Create a multi-stage Dockerfile to streamline the deployment process.
- Incorporate build and run stages within the Dockerfile to optimize containerization.
- Update the compose.yaml file to align with Docker deployment requirements.

### OpenAPI Specification:

- Create a new route /accounts/{accountId} to handle **PATCH** request for updating account details.
- Add a new file accountId.yml in the OpenAPI specification.
- Include details for the /accounts/{accountId} route, such as parameters, request/response formats, and any other relevant information.
- Modify the parameters.yml file to include the accountId parameter definition.
- Define the accountId parameter with appropriate properties, such as name, description, type, format, etc.


## Code Walkthrough

[Code Walkthrough]()


## References

- [Accessing Data with JPA](https://spring.io/guides/gs/accessing-data-jpa/)
- [Temporal Quick Start](https://docs.temporal.io/docs/quick-start)
- [Temporal Java SDK Quick Guide](https://docs.temporal.io/dev-guide/java)
- [Stripe Quick Start](https://stripe.com/docs/quickstart)
- [Stripe Java SDK](https://stripe.com/docs/api/java)
