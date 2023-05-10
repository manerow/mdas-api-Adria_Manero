## Hace todos los puntos pedidos (40%)

#### El nombre del repositorio es el correcto (mdas-api-${nombre}_${apellido})

OK

#### Permite obtener los detalles de un pokemon vía endpoint (datos + número de veces que se ha marcado como favorito)

OK

#### Actualiza el contador de favoritos vía eventos

OK

#### ¿Se controlan las excepciones de dominio? Y si se lanza una excepción desde el dominio, ¿se traduce en infraestructura a un código HTTP?

No aplica. Por la forma en la que está hecha no se lanzan excepciones de dominio.

#### Tests unitarios

KO

#### Tests de aceptación

OK, aunque únicamente existe el happy path del caso de uso de añadir un pokemon como
favorito. El test `UserAddFavoritePokemonIntegrationTest` es de aceptación, no de
integración.

#### Tests de integración

KO

**Puntuación: 32/40**

## Se aplican conceptos explicados (50%)

#### Separación correcta de capas (application, domain, infrastructure + BC/module/layer)

- El encargado de publicar los eventos es la capa de aplicación (el orquestador).
- El evento debería crearse en el dominio en el método de `addFavoritePokemon`. Lo que estás haciendo es emitir un VO en
  vez de un evento de dominio.
- El caso de uso `IncrementPokemonFavoritesUseCase` o el servicio de dominio `AddPokemonFavorite` no debería tener la
  lógica del contador. Lo que debería hacerse es con ayuda del repositorio (puerto) obtener el agregado (pokemon) y
  sobre este, hacer un +1 en el atributo `favorites`. Y una vez que se ha hecho el +1, se guarda en el repositorio.

#### Aggregates + VOs

El agregado `Pokemon` no tiene ninguna lógica :-(

#### No se trabajan con tipos primitivos en dominio

OK

#### Hay use cases en aplicación reutilizables

OK

#### Se aplica el patrón repositorio

OK, aunque debería tener un método `save()` y no tener el método `addPokemonFavorite()`. Así, si se modifica cualquier
otro atributo podrías reutilizar el método 😜

#### Se usan subscribers

OK

#### Se lanzan eventos de dominio

KO, se publica un evento en rabbit con un pokemon id. No es un evento de dominio.

#### Se utilizan object mothers

OK

**Puntuación: 30/50**

## Facilidad setup + README (10%)

#### El README contiene al menos los apartados ""cómo ejecutar la aplicación"", ""cómo usar la aplicación""

OK

#### Es sencillo seguir el apartado ""cómo ejecutar la aplicación""

OK, aunque no tengo el comando `jq` por lo que los cURLs no funcionan (indicas que no es obligatorio el comando).

**Puntuación: 8/10**

## Observaciones

**PUNTUACIÓN FINAL: 70/100**
