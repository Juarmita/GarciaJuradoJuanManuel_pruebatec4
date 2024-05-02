
# Prueba Técnica Spring Boot
Este proyecto se basa en una API REST de una agencia de viajes.
Se nos solicita la creacion de una aplicación para la gestion de hoteles, habitaciones, reservas de hotel, vuelos y reservas de vuelos.



## Funcionalidades

Para no hacer tan larga esta parte del readme voy a explicar los metodos de forma generica para todas las entidades.

**Creacion**: Mediante esta peticion POST en Postman creamos tanto el hotel, como las habitaciones, vuelos y reservas

**Listar todos**: Mediante un metodo GET listamos todos objetos creados en las entidades.

**Obtener por id**: Con una peticion generada con un GET obtenemos el objeto en cuestion que queremos buscar por su id.

**Borrar**: Para realizar el borrado con una peticion DELETE primero tenemos que buscar por la id el objeto que queremos borrar y después borrarlo

**Editar**: Al igual que el borrado, realizamos una peticion PUT y filtrando por medio de la id podemos editar el objeto especifico que queramos.

**Obtener listado por rango de fechas y destino**: Obtenemns todas las habitaciones disponibles en una ciudad y en un determinado rango de fechas.




## Running Tests

Se han realizado test a traves de postman.

Tambien se ha realizado sobre la creacion de hoteles un test con JUnit el cual ha pasado satisfactoriamente.


## Supuestos
- Se ha supuesto que se va a reservar la primera habitacion libre que se encuentre en la ciudad.
- Se ha supuesto el formato de fecha a "yyyy-MM-dd"
- Se ha supuesto que solo es necesario estar autenticados en los casos que se solicitan en el texto de la prueba tecnica, el resto es accesible para todos.






## Ejecucion

- Clona el repositorio en el direcorio que desees.
- Abre una terminal y navega hasta el directorio raíz del proyecto.
- Ejecuta la aplicación.
## Uso

- Ejecutar la api a través del IDE, tener la base de datos en marcha y probar a realizar las peticiones a traves de Postman
- Para acceder a algunos enlaces hay que estar autenticado, estos enlaces son:
- /agency/hotels/rooms
- /agency/hotels/{id}
- /agency/hotels
- /agency/flights
- /agency/flights/{id}
## Documentation

- [JPQL](https://www.tutorialspoint.com/es/jpa/jpa_jpql.htm)
- [JPQL](https://javaespanol.blogspot.com/2015/12/introduccion-jpa-parte-iii-lenguaje-jpql.html)
- [Jackson](https://www.tutorialspoint.com/jackson_annotations/jackson_annotations_jsonidentityinfo.htm)
- [Jackson](https://www.tutorialspoint.com/jackson_annotations/jackson_annotations_jsonmanagedreference.htm)
- [Jackson](https://www.tutorialspoint.com/jackson_annotations/jackson_annotations_jsonbackreference.htm)
- [Stream y filter](https://codingfactsblog.wordpress.com/2017/08/01/jugando-con-streams-y-predicates-en-java/)
- [JPA](https://github.com/juanmacintas/tallerJPASpringData)
- [Recursion](https://www.youtube.com/watch?v=GaCBrMvgY2A)
- [TimeUnit](https://www.geeksforgeeks.org/timeunit-class-in-java-with-examples/)
- [TimeUnit](https://www.w3resource.com/java-exercises/datetime/java-datetime-exercise-38.php)
- [TimeUnit](https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/TimeUnit.html)

## Authors

- Juan Manuel Garcia Jurado

