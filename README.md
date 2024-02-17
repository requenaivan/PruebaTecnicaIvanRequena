
# Prueba Tecnica Ivan Requena Inditex

Repositorio que contiene el proyecto para Inditex, El proyecto fue desarrollado usando Java 17, Gradle con Spring Boot, aplicando  arquitectura orientada a eventos y arquitectura hexagonal dentro de cada microservicio asi como tambien aplicando principios SOLID y clean code. Con la posibilidad de dockerizar los microservicios.

## Arquitectura

Solucion planteada separando la responsabilidad de los microservicios y manteniendo un límite claro entre la capa de api, dominio y la infraestructura. Dentro de cada microservicio se encuentra un endpoint 
**`http://{{ip_or_domain}}:{{port}}/health`** que nos indica que el servicio se encuentra UP.

```bash
{
  "status": "OK"
}
```


* **`inditex-api`**: Microservicio encargado de recibir todas las peticiones de los clientes actua como un api-layer

* **`inditex-ms`**: Microservicio encargado de manejar la conexion a la base de datos y realizar logica de negocio

Podrian exisitir otros microservicios como orquestadores que reciban informacion para actualizar precios de los microservicio en ese caso se crearia otro que se comunicaria al inditex-ms ya sea por http o algun broker de mensajeria como kafka o sqs.

![](https://github.com/requenaivan/PruebaTecnicaIvanRequena/blob/main/Diagrama.png?raw=true)

### Seguridad

Se construyo un basic auth para el microservicio inditex-ms el cual va a tener la conexion a la bd, el microservicio inditex-api seria el api layer por el cual entraria todas la peticiones(Se le colocaria una seguridad mas alta por ejemplo (keycloack o cognito)).

#### Token no valido

![](https://github.com/requenaivan/PruebaTecnicaIvanRequena/blob/main/token_not_valid.png?raw=true)

#### Token valido

![](https://github.com/requenaivan/PruebaTecnicaIvanRequena/blob/main/getPrice.png?raw=true)

## Requisitos

* Java (JDK 17)
* Git
* Gradle
* Docker

## Configuracion
Clonar el repositorio e instalar las dependencias.
```bash
git clone https://github.com/requenaivan/PruebaTecnicaIvanRequena.git
```

## Construcción del proyecto

* Ejecutar los Tests

```bash
./gradlew check
```
* **Resultados de los test inditex-api**
```bash
inditex-api/inditex-test/build/reports/tests/test/index.html
```
![](https://github.com/requenaivan/PruebaTecnicaIvanRequena/blob/main/test_api.png?raw=true)

![](https://github.com/requenaivan/PruebaTecnicaIvanRequena/blob/main/test_api_price.png?raw=true)

* **Resultados de los test inditex-ms**
```bash
inditex-ms/inditex-ms-test/build/reports/tests/test/index.html
```
![](https://github.com/requenaivan/PruebaTecnicaIvanRequena/blob/main/test_ms.png?raw=true)

![](https://github.com/requenaivan/PruebaTecnicaIvanRequena/blob/main/test_ms_price.png?raw=true)

* Construcción del proyecto omitiendo la ejecución de los test

```bash
./gradlew -x test build 
```

* Construir el artefacto
```bash
./gradlew build 
```

# Ejecutar de manera local
Importar por separado cada carpeta(`inditex-ms` y `inditex-api`) en su IDE preferido y buscar las clases Application y ejecutarlas desde el run del IDE, en esta ejecución busca el archivo  `application.properties` porque el `application-prod.properties` es usado para dockerizar el proyecto o correrlo en ambiente productivo.


# Ejecutar Docker `inditex-ms`

* Abrir la terminal y moverse a la raiz de `inditex-ms` y ejecutar la siguiente linea de comandos.:

```bash
docker build --tag 'inditex-ms' . 
```

* Ejecutar la imagen creada `inditex-ms`:
```bash
docker run -p 8081:8081 -e PORT_SERVER=8081 -e USERNAME_AUTH=inditex-price -e PASSWORD_AUTH=Ca23Wvs212 inditex-ms
```


# Ejecutar Docker `inditex-api`

* Abril la terminal y moverse a la raiz de `inditex-api` y ejecutar la siguiente linea de comandos:

```bash
docker build --tag 'inditex-api' . 
```

* Ejecutar la imagen docker `inditex-api`:
  
**`ip_your_machine`**: ip de su maquina
```bash
docker run -p 8080:8080 -e USERNAME_AUTH=inditex-price -e PASSWORD_AUTH=Ca23Wvs212 -e URL_BASE_INDITEX_MS=http://{{ip_your_machine}}:8081/inditex-ms inditex-api
```

