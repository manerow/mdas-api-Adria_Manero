# Pokemon Type Retrieval Application

A simple Spring Boot application that interacts with the PokeAPI to retrieve information about Pokemons. It is built using Domain Driven Design, Hexagonal Architecture and has inter-context communication via RabbitMQ events.

## Prerequisites
* Docker
* Docker compose
* Curl
* Jq (Not mandatory, just to beautify the curl response)
## Getting Started
### Build the project
The application can be built using the graddle wrapper in the project root.
```
./gradlew clean build
```
### Test suite run
Although the test suite present on this application is run on every project build, it can be specifically run using the following gradle wrapper command:
```
./gradlew test
```
### Deploy the project
To deploy the application and all the services it requires (i.e the RabbitMQ queue) use the following command:
```
docker compose up -d
```

### Console

To run the application on the console, follow the steps below:

After running the above Docker compose command, we must attach a shell to the application service in order to provide commands through stdin.
```
docker attach mdas-api-Adria-Manero
```
Since the application has already started on detached mode, it wont display any instructions message when attaching.  
When attached, just enter `pokemon type <pokemon_name>` to obtain the types of the specified Pokemon.  
Bear in mind that if the application is closed through the shell, the docker container will crash and therefore will stop. In order to close the attached shell properly, use `Ctrl + P` and then `Ctrl + Q`
### Rest API
* Get a pokemon's type:
    
    `[GET] http://localhost:8080/api/pokemon/type?name=<pokemon_name>`
  
    ```bash
    curl -s -X GET "http://localhost:8080/api/pokemon/type?name=lucario" | jq
    ```

* Create a new User:

    `[POST] http://localhost:8080/api/user/create` 
    
    `Body: { name: string }`
    ``` bash
    curl -s -X POST -H "Content-Type: application/json" -d '{ "id": 1, "name": "John Doe"}' http://localhost:8080/api/user/create | jq
    ```
* Add a pokemon to favorites: 

  `[POST] http://localhost:8080/api/user/add-favorite-pokemon`

  `Body: { pokemonId: int }`

  `Header: user_id: string`
    ``` bash
    curl -s -X POST -H "Content-Type: application/json" -H "user_id: 1" -d '{"pokemonId": 448}' http://localhost:8080/api/user/add-favorite-pokemon | jq
    ```
  Note that you may use the same `user_id` from the "Create a new  User" call.


* Get a pokemon details (ID, Name, Height, Weight and Favorites):

  `[GET] http://localhost:8080/api/pokemon/detail?id=<pokemon_id>`

    ```bash
    curl -s -X GET "http://localhost:8080/api/pokemon/detail?id=448" | jq
    ```
## Technologies Used

- Java
- Spring Boot
- RabbitMQ
- Gradle

## Project Structure

The application follows the Hexagonal Architecture pattern and is divided into the following layers:

- Domain layer: contains the business logic and entities of the application.
- Application layer: contains the use cases of the application and orchestrates the interaction between the domain and infrastructure layers.
- Infrastructure layer: contains the implementation details of the application, such as the PokeAPI client and repository.
