FROM gradle:jdk17 as builder
WORKDIR /builder
COPY . /builder
RUN ./gradlew clean build -x test

FROM openjdk:17-jre-alpine
RUN mkdir /code

COPY --from=builder /builder/inditex-app/build/libs/inditex-app-boot.jar /code/inditex-app-boot.jar

ENTRYPOINT [ "sh", "-c", "exec java -jar -Dnetworkaddress.cache.ttl=60 /code/inditex-app-boot.jar" ]
