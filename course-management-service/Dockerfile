FROM openjdk:8-jre-alpine


ENV JVM_OPTS="-Xms512m -Xmx512m"
ENV APP_OPTS=""

COPY ./wait /wait
RUN chmod +x /wait


COPY target/course.jar /course.jar



EXPOSE 9999

ENTRYPOINT /wait && exec java $JVM_OPTS -jar /course.jar $APP_OPTS