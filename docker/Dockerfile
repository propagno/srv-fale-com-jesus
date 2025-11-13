# Multi-stage build para otimizar tamanho da imagem
FROM eclipse-temurin:25-jdk-jammy AS builder

WORKDIR /app

# Copia apenas os arquivos necessários para build
COPY pom.xml .
COPY .mvn .mvn
COPY mvnw .
COPY src ./src

# Dá permissão de execução ao mvnw
RUN chmod +x mvnw

# Build da aplicação
RUN ./mvnw clean package -DskipTests

# Extrai as camadas do Spring Boot
# O nome do JAR é definido no pom.xml (artifactId)
RUN mkdir -p /app/extracted && \
    java -Djarmode=layertools -jar target/srv-fale-com-jesus.jar extract --destination /app/extracted

# Stage de runtime
FROM eclipse-temurin:25-jre-jammy

WORKDIR /app

# Instala curl para health check
RUN apt-get update && \
    apt-get install -y curl && \
    rm -rf /var/lib/apt/lists/*

# Cria usuário não-root para segurança
RUN groupadd -r spring && useradd -r -g spring spring

# Copia as camadas extraídas
COPY --from=builder /app/extracted/dependencies/ ./
COPY --from=builder /app/extracted/spring-boot-loader/ ./
COPY --from=builder /app/extracted/snapshot-dependencies/ ./
COPY --from=builder /app/extracted/application/ ./

# Define propriedades do usuário
USER spring:spring

# Variáveis de ambiente
ENV JAVA_OPTS="-Xmx512m -Xms256m" \
    SPRING_PROFILES_ACTIVE=default \
    TZ=America/Sao_Paulo

# Health check
HEALTHCHECK --interval=30s --timeout=3s --start-period=40s --retries=3 \
  CMD curl -f http://localhost:8080/actuator/health || exit 1

# Porta da aplicação
EXPOSE 8080

# Entrypoint otimizado
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS org.springframework.boot.loader.launch.JarLauncher"]

