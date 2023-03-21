# Pokemon Type Retrieval Application

This application is a simple console application that interacts with the PokeAPI to retrieve information about Pokemons. It is built using Java and the Spring Boot framework, and follows the Hexagonal Architecture pattern.

## Prerequisites
* Docker
* Curl
* Jq (Not mandatory, just to beautify the curl response)
## Getting Started
### Docker build
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
* Get a pokemon's type:
    
    `[GET] http://localhost:9091/ms-ne-pokemon/v1/pokemon/type?name=<pokemon_name>`
  
    ```bash
    curl -s -X GET "http://localhost:9091/ms-ne-pokemon/v1/pokemon/type?name=lucario" | jq
    ```

* Create a new User:

    `[POST] http://localhost:9091/ms-ne-pokemon/v1/user/create` 
    
    `Body: { name: string }`
    ``` bash
    curl -s -X POST -H "Content-Type: application/json" -d '{"name": "John Doe"}' http://localhost:9091/ms-ne-pokemon/v1/user/create | jq
    ```
* Add a pokemon to favorites: 

  `[POST] http://localhost:9091/ms-ne-pokemon/v1/user/add-favorite-pokemon`

  `Body: { pokemonId: int }`

  `Header: user_id: string`
    ``` bash
    curl -s -X POST -H "Content-Type: application/json" -H "user_id: <user_id>" -d '{"pokemonId": 25}' http://localhost:9091/ms-ne-pokemon/v1/user/add-favorite-pokemon | jq
    ```
  Note that you may get `<user_id>` from the "Create a new  User" call response.
## Technologies Used

- Java
- Spring Boot
- Gradle

## Project Structure

The application follows the Hexagonal Architecture pattern and is divided into the following layers:

- Domain layer: contains the business logic and entities of the application.
- Application layer: contains the use cases of the application and orchestrates the interaction between the domain and infrastructure layers.
- Infrastructure layer: contains the implementation details of the application, such as the PokeAPI client and repository.
