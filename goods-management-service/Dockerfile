FROM openjdk:8-jre-alpine

ENV JVM_OPTS="-Xms512m -Xmx512m"
ENV APP_OPTS=""

COPY ./wait /wait
RUN chmod +x /wait

COPY target/goods-management-service.jar /goods-management-service.jar

EXPOSE 8082

ENTRYPOINT /wait && exec java $JVM_OPTS -jar /goods-management-service.jar $APP_OPTS