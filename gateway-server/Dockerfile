FROM openjdk:8-jre-alpine

ENV JVM_OPTS="-Xms512m -Xmx512m"
ENV APP_OPTS=""

COPY ./wait /wait
RUN chmod +x /wait

COPY target/gateway.jar /gateway.jar

EXPOSE 8080

ENTRYPOINT /wait && exec java $JVM_OPTS -jar /gateway.jar $APP_OPTS