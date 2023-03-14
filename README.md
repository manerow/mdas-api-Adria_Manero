# Pokemon Type Retrieval Application

This application is a simple console application that interacts with the PokeAPI to retrieve information about Pokemons. It is built using Java and the Spring Boot framework, and follows the Hexagonal Architecture pattern.

## Prerequisites

- JDK 11 or higher
- Gradle

## Getting Started
### Console

To run the application on the console, follow the steps below:

1. Clone the repository using the following command:
``` bash
git clone https://github.com/manerow/mdas-api-g6.git
```
2. Navigate to the project's root directory using the command prompt.

3. Run the application using the following command:
``` bash
./gradlew clean bootRun --console=plain
```

The application should start and prompt the user to enter a command.

4. Enter `pokemon type <pokemon_name>` to obtain the types of the specified Pokemon.

5. Enter `q` to exit the application.


## Technologies Used

- Java
- Spring Boot
- Gradle

## Project Structure

The application follows the Hexagonal Architecture pattern and is divided into the following layers:

- Domain layer: contains the business logic and entities of the application.
- Application layer: contains the use cases of the application and orchestrates the interaction between the domain and infrastructure layers.
- Infrastructure layer: contains the implementation details of the application, such as the PokeAPI client and repository.
