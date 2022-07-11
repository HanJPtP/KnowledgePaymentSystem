FROM openjdk:8-jre-alpine

ENV JVM_OPTS="-Xms512m -Xmx512m"
ENV APP_OPTS=""

COPY ./wait /wait
RUN chmod +x /wait

COPY target/users-service.jar /users-service.jar

EXPOSE 8080

ENTRYPOINT /wait && exec java $JVM_OPTS -jar /users-service.jar $APP_OPTS