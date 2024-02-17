
# Prueba Tecnica Ivan Requena Inditex

Repositorio que contiene el proyecto para Inditex, El proyecto fue desarrollado usando Java 17, Gradle con Spring Boot, aplicando  arquitectura orientada a eventos y arquitectura hexagonal dentro de cada microservicio asi como tambien aplicando principios SOLID y clean code. Con la posibilidad de dockerizar los microservicios.

## Arquitectura

Solucion planteada separando la responsabilidad de los microservicios y manteniendo un límite claro entre la capa de api, dominio y la infraestructura.

* inditex-api: Microservicio encargado de recibir todas las peticiones de los clientes actua como un api-layer

* inditex-ms: Microservicio encargado de manejar la conexion a la base de datos y realizar logica de negocio

Podrian exisitir otros microservicios como orquestadores que reciban informacion para actualizar precios de los microservicio en ese caso se crearia otro que se comunicaria al inditex-ms ya sea por http o algun broker de mensajeria como kafka o sqs.

![](https://github.com/requenaivan/PruebaTecnicaIvanRequena/blob/main/Diagrama.png?raw=true)

### Seguridad

Se construyo un basic auth para el microservicio inditex-ms el cual va a tener la conexion a la bd, el microservicio inditex-api seria el api layer por el cual entraria todas la peticiones(Se le colocaria una seguridad mas alta por ejemplo (keycloack o cognito)).

#### Token no valido

![](https://github.com/requenaivan/PruebaTecnicaIvanRequena/blob/main/token_not_valid.png?raw=true)

#### Token valido

![](https://github.com/requenaivan/PruebaTecnicaIvanRequena/blob/main/getPrice.png?raw=true)

## Requirements

* Java (JDK 17)
* Git
* Gradle
* Docker

## Common setup
Clone the repo and install the dependencies.
```bash
git clone https://github.com/requenaivan/PruebaTecnicaIvanRequena.git
```

## Build

* Run Tests

```bash
./gradlew check
```
* **Result test inditex-api**
```bash
inditex-api/inditex-test/build/reports/tests/test/index.html
```
![](https://github.com/requenaivan/PruebaTecnicaIvanRequena/blob/main/test_api.png?raw=true)

![](https://github.com/requenaivan/PruebaTecnicaIvanRequena/blob/main/test_api_price.png?raw=true)

* **Result test inditex-ms**
```bash
inditex-ms/inditex-ms-test/build/reports/tests/test/index.html
```
![](https://github.com/requenaivan/PruebaTecnicaIvanRequena/blob/main/test_ms.png?raw=true)

![](https://github.com/requenaivan/PruebaTecnicaIvanRequena/blob/main/test_ms_price.png?raw=true)

* Build the project skipping tests execution

```bash
./gradlew -x test build 
```

* Build the artifact
```bash
./gradlew build 
```

# Run locally
Open project in your IDE, and find class Application and run, in this case run with `application.properties` because the `application-prod.properties` is used by docker o run in production environment with parameters custom.


# Run Docker inditex-ms

* Open terminal and move to inditex-ms and run next command line:

```bash
docker build --tag 'inditex-ms' . 
```

* Run image created:
```bash
docker run -p 8081:8081 -e PORT_SERVER=8081 -e USERNAME_AUTH=inditex-price -e PASSWORD_AUTH=Ca23Wvs212 inditex-ms
```


# Run Docker inditex-api

* Open terminal and move to inditex-ms and run next command line:

```bash
docker build --tag 'inditex-api' . 
```

* Run image created: 
**ip_your_machine**: ip de su maquina
```bash
docker run -p 8080:8080 -e USERNAME_AUTH=inditex-price -e PASSWORD_AUTH=Ca23Wvs212 -e URL_BASE_INDITEX_MS=http://{{ip_your_machine}}:8081/inditex-ms inditex-api
```

