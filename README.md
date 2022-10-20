
# Virgin Money
Virgin Money - Technical Challenge

### Tech Stack:
| Technology | Version |
|--|--|
| **Java** | 11.0.3-2018-01-14 |
| **Spring Boot** | 2.5.0 |
| **Spring JPA Data** | 2.5.0 |
| **Project Lombok** | 1.18.20 |
| **JUnit 4** | 4.1.5 |
| **Mockito** | 2.0.9 |
| **OpenAPI** | 1.5.9 |
| **Gradle** | 7.0 |

### Acessing Swagger | Open API:
Once with the application running:
http://localhost:8080/swagger-ui.html

### How to run the application:
> IDE (IntelliJ, Eclipse, NetBeans):
- Importing the project as Maven project on your favourite IDE.
- Build project using Java 11
- Run/Debug project from Main Application Class :: VirginMoneyExerciseApplication

> Terminal:
- `gradle wrapper`
- `./gradlew build`
- `./gradlew bootRun`

APIs:
Basic URL path if you're running locally is: http://localhost:8080/virgin-money/transactions

#### Check out the open API documentation to see the JSON body example.

### How to run the tests:
- For the controller test: `co.uk.virginmoneyexercise.VirginMoneyExerciseApplicationTests`
- For the service test: `co.uk.virginmoneyexercise.service.TransactionServiceImplTest`
