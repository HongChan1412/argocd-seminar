# 🔹 1단계: Gradle 빌드
FROM gradle:8.1.1-jdk17 AS build
WORKDIR /app

# Gradle 캐시 최적화
COPY build.gradle settings.gradle gradlew ./
COPY gradle/ ./gradle/
RUN ./gradlew dependencies

# 소스 코드 복사 후 빌드
COPY src/ ./src/
RUN ./gradlew build -x test

# 🔹 2단계: 경량화된 실행 이미지
FROM openjdk:17-jdk-slim
WORKDIR /app

COPY --from=build /app/build/libs/*.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
