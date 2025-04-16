# 🔹 1단계: Gradle 빌드
FROM gradle:8.1.1-jdk17 AS build
WORKDIR /app

# Gradle 캐시 최적화
COPY settings.gradle build.gradle gradlew ./
COPY gradle/ ./gradle/
RUN ./gradlew --no-daemon build -x test || true

# 소스 코드 복사 후 빌드
COPY src/ ./src/
RUN ./gradlew --no-daemon build -x test --build-cache

# 🔹 2단계: 경량화된 실행 이미지
FROM eclipse-temurin:17-jre-jammy
WORKDIR /app

COPY --from=build /app/build/libs/*.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
