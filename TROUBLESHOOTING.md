# 🐛 Troubleshooting Guide

Guia de resolução de problemas comuns.

## 🔴 Problemas de Build

### Erro: "Maven not found"

**Solução:**
```bash
# Use o Maven Wrapper incluído
./mvnw clean install

# Ou instale o Maven
# Windows: choco install maven
# Linux: sudo apt install maven
# Mac: brew install maven
```

### Erro: "Java version mismatch"

**Solução:**
```bash
# Verifique a versão
java -version  # Deve ser 17+

# Configure JAVA_HOME
# Windows:
set JAVA_HOME=C:\Program Files\Java\jdk-17

# Linux/Mac:
export JAVA_HOME=/usr/lib/jvm/java-17-openjdk
```

### Erro: "Dependency resolution failed"

**Solução:**
```bash
# Limpe o cache do Maven
./mvnw clean
rm -rf ~/.m2/repository

# Force update
./mvnw clean install -U
```

## 🗄️ Problemas de Banco de Dados

### Erro: "Cannot connect to database"

**Sintomas:**
```
com.microsoft.sqlserver.jdbc.SQLServerException: 
The TCP/IP connection to the host has failed.
```

**Soluções:**

1. **Verifique se o banco está rodando:**
```bash
docker ps | grep db-dev
```

2. **Inicie o banco se necessário:**
```bash
cd ../db-propagno
docker-compose up -d db-dev
./scripts/init.sh dev
```

3. **Verifique as variáveis de ambiente:**
```bash
# Verifique .env.dev
cat .env.dev

# Verifique se DB_HOST está correto
# Para Docker Compose: use 'db-dev'
# Para conexão externa: use 'localhost' ou IP
```

4. **Teste a conexão manualmente:**
```bash
# Via Docker
docker exec -it db-dev /opt/mssql-tools/bin/sqlcmd \
  -S localhost -U sa -P YourStrong@Passw0rd \
  -Q "SELECT 1"
```

### Erro: "Login failed for user 'sa'"

**Solução:**
```bash
# Verifique a senha no .env.dev
# Certifique-se de que DB_PASSWORD corresponde à senha do banco

# Reinicie o banco com a senha correta
cd ../db-propagno
docker-compose down
docker-compose up -d db-dev
```

### Erro: "Database does not exist"

**Solução:**
```bash
# O banco deve ser criado pelo Liquibase
# Verifique se as migrations foram executadas:
cd ../db-propagno
docker-compose up liquibase-dev
```

## 🐳 Problemas de Docker

### Erro: "Port already in use"

**Sintomas:**
```
Error: bind: address already in use
```

**Solução:**
```bash
# Encontre o processo usando a porta
# Windows:
netstat -ano | findstr :8080

# Linux/Mac:
lsof -i :8080

# Mate o processo ou mude a porta no .env.dev
SERVER_PORT=8081
```

### Erro: "Container não inicia"

**Solução:**
```bash
# Ver logs
docker-compose logs app-dev

# Verifique se há erros
docker-compose ps

# Reinicie
docker-compose down
docker-compose up -d
```

### Erro: "Network not found"

**Sintomas:**
```
network db-propagno-network not found
```

**Solução:**
```bash
# Crie a network manualmente
docker network create db-propagno-network

# Ou inicie o banco primeiro (ele cria a network)
cd ../db-propagno
docker-compose up -d db-dev
```

## 🚀 Problemas de Aplicação

### Erro: "Application failed to start"

**Soluções:**

1. **Verifique os logs:**
```bash
docker-compose logs -f app-dev
# ou
./mvnw spring-boot:run
```

2. **Verifique a configuração:**
```bash
# Verifique application.yml
cat src/main/resources/application.yml

# Verifique variáveis de ambiente
cat .env.dev
```

3. **Verifique dependências:**
```bash
./mvnw dependency:tree
```

### Erro: "Health check failed"

**Solução:**
```bash
# Verifique se a aplicação está rodando
curl http://localhost:8080/actuator/health

# Verifique logs
docker-compose logs app-dev | grep -i error
```

### Erro: "Swagger não carrega"

**Solução:**
```bash
# Verifique se está habilitado
# application.yml: springdoc.swagger-ui.enabled=true

# Acesse: http://localhost:8080/swagger-ui/index.html
# (não /swagger-ui.html)
```

## 🔄 Problemas de CI/CD

### Erro: "Workflow failed"

**Soluções:**

1. **Verifique os logs do workflow no GitHub Actions**

2. **Verifique secrets configurados:**
   - Settings > Secrets and variables > Actions

3. **Verifique permissões:**
   - O workflow precisa de `contents: read` e `packages: write`

### Erro: "Image push failed"

**Solução:**
```bash
# Verifique se GITHUB_TOKEN tem permissão
# Verifique se o registry está correto
# Verifique se a imagem foi buildada corretamente
```

## 📝 Problemas de Migrations

### Erro: "Migration failed"

**Solução:**
```bash
# Verifique o changelog
cd ../db-propagno
docker-compose up liquibase-dev

# Veja o histórico
docker-compose exec liquibase-dev liquibase history

# Valide o changelog
docker-compose exec liquibase-dev liquibase validate
```

### Erro: "Changeset already executed"

**Solução:**
```bash
# Isso é normal se a migration já foi aplicada
# Crie uma nova migration com novo ID/timestamp
```

## 🔍 Debugging

### Ver logs completos

```bash
# Docker Compose
docker-compose logs -f

# Aplicação Spring Boot
./mvnw spring-boot:run --debug

# Banco de dados
docker-compose logs db-dev
```

### Verificar conectividade

```bash
# Teste conexão com banco
docker exec -it db-dev /opt/mssql-tools/bin/sqlcmd \
  -S localhost -U sa -P YourStrong@Passw0rd \
  -Q "SELECT @@VERSION"

# Teste health da aplicação
curl -v http://localhost:8080/actuator/health
```

### Verificar variáveis de ambiente

```bash
# Docker
docker-compose config

# Aplicação
# Adicione ao application.yml:
logging:
  level:
    org.springframework.web: DEBUG
```

## 📞 Ainda com Problemas?

1. Verifique os logs completos
2. Consulte a documentação:
   - [README.md](README.md)
   - [QUICKSTART.md](QUICKSTART.md)
   - [ARCHITECTURE.md](ARCHITECTURE.md)
3. Abra uma issue no GitHub
4. Consulte os logs do GitHub Actions

---

**Lembre-se:** A maioria dos problemas está relacionada a configuração de ambiente ou conectividade com o banco de dados.

