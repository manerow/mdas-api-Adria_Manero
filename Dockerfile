FROM openjdk:11-jre-slim as builder
WORKDIR application
COPY build/libs/mdas-api-g6.jar .
RUN java -Djarmode=layertools -jar mdas-api-g6.jar extract

FROM openjdk:11-jre-slim
WORKDIR application
COPY --from=builder dependencies/ .
COPY --from=builder spring-boot-loader/ .
COPY --from=builder internal-dependencies/ .
COPY --from=builder snapshot-dependencies/ .
COPY --from=builder application/ .

ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "org.springframework.boot.loader.JarLauncher"]