FROM openjdk:8-jre-alpine

ENV JVM_OPTS="-Xms512m -Xmx512m"
ENV APP_OPTS=""

COPY ./wait /wait
RUN chmod +x /wait

COPY target/service-order.jar /service-order.jar

EXPOSE 7080

ENTRYPOINT /wait && exec java $JVM_OPTS -jar /service-order.jar $APP_OPTS