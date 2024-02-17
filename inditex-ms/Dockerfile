FROM gradle:jdk17 as builder
WORKDIR /builder
COPY . /builder
RUN ./gradlew clean build -x test

FROM openjdk:17-jre-alpine
RUN mkdir /code

COPY --from=builder /builder/inditex-ms-app/build/libs/inditex-ms-boot.jar /code/inditex-ms-boot.jar

ENTRYPOINT [ "sh", "-c", "exec java -jar -Dnetworkaddress.cache.ttl=60 /code/inditex-ms-boot.jar" ]
