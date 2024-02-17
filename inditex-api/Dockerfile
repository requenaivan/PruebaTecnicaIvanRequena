FROM gradle:jdk17 as builder
WORKDIR /builder
COPY . /builder
RUN ./gradlew clean build -x test

FROM openjdk:17-jdk-slim
RUN mkdir /code

COPY --from=builder /builder/inditex-app/build/libs/inditex-app-boot.jar /code/inditex-app-boot.jar

ENTRYPOINT [ "sh", "-c", "exec java -jar -Dnetworkaddress.cache.ttl=60 -Dspring.profiles.active=prod /code/inditex-app-boot.jar" ]
