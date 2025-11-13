# 🚀 Quick Start - srv-fale-com-jesus

Guia rápido para começar a desenvolver em 5 minutos.

## ⚡ Setup Rápido

### 1. Pré-requisitos

Verifique se você tem instalado:
```bash
# Java
java -version  # Deve ser 17+

# Maven
mvn -version   # Deve ser 3.8+

# Docker
docker --version
docker-compose --version
```

### 2. Clone e Configure

```bash
# Clone o repositório
git clone git@github.com:propagno/srv-fale-com-jesus.git
cd srv-fale-com-jesus

# Configure variáveis de ambiente
cp .env.example .env.dev
# Edite .env.dev com suas configurações
```

### 3. Inicie o Banco de Dados

```bash
# No repositório db-propagno (se ainda não iniciou)
cd ../db-propagno
docker-compose up -d db-dev
./scripts/init.sh dev

# Ou se o banco já está rodando, apenas verifique
docker ps | grep db-dev
```

### 4. Execute a Aplicação

**Opção A: Docker Compose (Recomendado)**
```bash
cd srv-fale-com-jesus
docker-compose -f docker-compose.dev.yml up -d
```

**Opção B: Maven Direto**
```bash
./mvnw spring-boot:run
```

### 5. Verifique se Está Funcionando

```bash
# Health check
curl http://localhost:8080/actuator/health

# Swagger UI
# Abra no navegador: http://localhost:8080/swagger-ui/index.html
```

## ✅ Pronto!

Agora você pode:
- Acessar Swagger: http://localhost:8080/swagger-ui/index.html
- Ver health: http://localhost:8080/actuator/health
- Desenvolver suas features!

## 🐛 Problemas Comuns

### Erro: "Cannot connect to database"
```bash
# Verifique se o banco está rodando
docker ps | grep db-dev

# Se não estiver, inicie:
cd ../db-propagno
docker-compose up -d db-dev
```

### Erro: "Port 8080 already in use"
```bash
# Altere a porta no .env.dev
SERVER_PORT=8081
```

### Erro: "Maven not found"
```bash
# Use o wrapper incluído
./mvnw spring-boot:run
```

## 📚 Próximos Passos

- Leia [README.md](README.md) para mais detalhes
- Veja [ARCHITECTURE.md](ARCHITECTURE.md) para entender a arquitetura
- Consulte [DATABASE-CONNECTION.md](DATABASE-CONNECTION.md) para conexão com banco

---

**Dúvidas?** Abra uma issue ou consulte a documentação completa.

