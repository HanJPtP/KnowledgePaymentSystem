FROM openjdk:8-jre-alpine

ENV JVM_OPTS="-Xms512m -Xmx512m"
ENV APP_OPTS=""

COPY ./wait /wait
RUN chmod +x /wait

COPY target/auth-server.jar auth-server.jar

EXPOSE 8082

ENTRYPOINT /wait && exec java $JVM_OPTS -jar /auth-server.jar $APP_OPTS