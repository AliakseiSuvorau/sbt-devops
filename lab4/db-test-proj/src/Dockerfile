FROM gradle:jdk17-jammy

COPY ../ /app/

WORKDIR /app/

RUN gradle clean build

ENTRYPOINT ["gradle", ":run"]