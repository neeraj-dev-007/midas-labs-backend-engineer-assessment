# Introduction

Welcome to the Midas Labs Backend Engineer Assessment repository! This project is part of the assessment process for backend engineers at Midas Labs. It is forked from the original assessment repository to develop and showcase the required skills.

This repository contains a pre-existing Spring Boot Application developed with Java 21 and leveraging the Temporal Workflow Engine for orchestrating business logic. The main task involves integrating the Stripe payment processing service to manage customer data upon user signup.


## Tasks Completed

- Integrated the Stripe payment processing service into the Spring Boot Application, facilitating seamless handling of customer transactions.
- Implemented temporal workflows, activities, and workers to execute essential tasks, including customer creation and updates, ensuring efficient business logic orchestration.
- Created a multi-stage Dockerfile to streamline the deployment process, incorporating build and run stages for efficient containerization.
- Updated the compose.yaml file to align with Docker deployment requirements.

For detailed instructions on setting up the project, please refer to the following section.


## Setup Instructions 

To run the application you would require:

- Java
- Temporal
- Docker

You are required to first start the temporal server using the following command

```sh
temporal server start-dev
```

and then run the application using the following command or using your IDE.

```sh
./gradlew bootRun
```


## Implementation Approach and Assumptions 




## Code Walkthrough

[Code Walkthrough]()


## References

- [Accessing Data with JPA](https://spring.io/guides/gs/accessing-data-jpa/)
- [Temporal Quick Start](https://docs.temporal.io/docs/quick-start)
- [Temporal Java SDK Quick Guide](https://docs.temporal.io/dev-guide/java)
- [Stripe Quick Start](https://stripe.com/docs/quickstart)
- [Stripe Java SDK](https://stripe.com/docs/api/java)
