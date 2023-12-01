FROM gradle:7.5.1 AS build
ENV APP_HOME=/usr/app/
WORKDIR $APP_HOME
COPY build.gradle settings.gradle ./
COPY src ./src
RUN gradle build

FROM openjdk:17
ENV ARTIFACT_NAME=backend-0.0.1-SNAPSHOT.jar
ENV APP_HOME=/usr/app/
COPY --from=build $APP_HOME/build/libs/$ARTIFACT_NAME .
EXPOSE 8080
ENTRYPOINT exec java -jar ${ARTIFACT_NAME}
