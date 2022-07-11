FROM openjdk:8-jre-alpine

ENV JVM_OPTS="-Xms512m -Xmx512m"
ENV APP_OPTS=""

COPY ./wait /wait
RUN chmod +x /wait

COPY target/service-message.jar /service-message.jar

EXPOSE 7080

ENTRYPOINT /wait && exec java $JVM_OPTS -jar /service-message.jar $APP_OPTS