from gradle:8-jdk21 as build
WORKDIR /app
copy . .
RUN gradle build --no-daemon

FROM bellsoft/liberica-runtime-container:jdk-21-musl

WORKDIR /app
COPY --from=build /app/build/libs/*.jar /app/user.jar

EXPOSE 8080

CMD ["java", "-jar", "/app/user.jar"]
