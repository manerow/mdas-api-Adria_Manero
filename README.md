# Pokemon Type Retrieval Application

This application is a simple console application that interacts with the PokeAPI to retrieve information about Pokemons. It is built using Java and the Spring Boot framework, and follows the Hexagonal Architecture pattern.

## Prerequisites

- JDK 11 or higher
- Gradle

## Getting Started
## Docker build
This application can be built and executed using Docker. In order to do so, use the following commands:
```
docker build -t mdas-api-g6:1.0.0 .
```

```
docker run -it --rm --name ctr-api-g6 -p 9091:9091 mdas-api-g6:1.0.0
```

### Console

To run the application on the console, follow the steps below:

After running the above Docker interactive run command, the application should have started and prompt the user to enter a command.

Enter `pokemon type <pokemon_name>` to obtain the types of the specified Pokemon.

### Rest API

When the application start, test the follow url (Type: GET):

Paramater: (pokemonName)

Test Local
``` bash
http://localhost:9091/ms-ne-pokemon/v1/pokemon/getType?pokemonName=lucario
```

## Technologies Used

- Java
- Spring Boot
- Gradle

## Project Structure

The application follows the Hexagonal Architecture pattern and is divided into the following layers:

- Domain layer: contains the business logic and entities of the application.
- Application layer: contains the use cases of the application and orchestrates the interaction between the domain and infrastructure layers.
- Infrastructure layer: contains the implementation details of the application, such as the PokeAPI client and repository.
