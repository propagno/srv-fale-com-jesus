# 🚀 srv-fale-com-jesus

Microsserviço desenvolvido com Spring Boot e integrado com as pipelines de infraestrutura.

## 📋 Tecnologias

- **Java 17**
- **Spring Boot 3.2.0**
- **SQL Server 2022**
- **Flyway** (Migrations)
- **Docker** & **Docker Compose**
- **GitHub Actions** (CI/CD)

## 🏗️ Arquitetura

Este projeto utiliza **Arquitetura Hexagonal** (Ports and Adapters).

### Estrutura de Camadas

```
src/main/java/br/com/propagno/falecomjesus/
│
├── domain/                    # NÚCLEO - Regras de Negócio
│   ├── entity/               # Entidades de domínio
│   └── exception/            # Exceções de domínio
│
├── application/              # CASOS DE USO
│   ├── port/
│   │   ├── input/           # Ports de entrada (interfaces)
│   │   └── output/          # Ports de saída (interfaces)
│   ├── service/             # Implementação dos casos de uso
│   └── dto/                 # DTOs da camada de aplicação
│
├── infrastructure/           # ADAPTADORES DE SAÍDA
│   └── persistence/         # Persistência (JPA, SQL Server)
│
└── adapter/                  # ADAPTADORES DE ENTRADA
    └── input/rest/          # Controllers REST
```

📖 Veja [ARCHITECTURE.md](ARCHITECTURE.md) para detalhes completos da arquitetura.

## 🚀 Como Executar Localmente

### Pré-requisitos

- Java 17+
- Maven 3.8+
- Docker & Docker Compose

### Opção 1: Docker Compose (Recomendado)

```bash
# Iniciar ambiente completo (app + banco)
docker-compose -f docker-compose.dev.yml up -d

# Ver logs
docker-compose -f docker-compose.dev.yml logs -f app-dev

# Parar
docker-compose -f docker-compose.dev.yml down
```

### Opção 2: Maven Direto

```bash
# Executar aplicação
./mvnw spring-boot:run

# Ou
mvn spring-boot:run
```

## 🗄️ Banco de Dados

### Conexão via SQL Server Management Studio

- **Server:** `localhost,1433`
- **Authentication:** SQL Server Authentication
- **Login:** `sa`
- **Password:** `YourStrong@Passw0rd` (ou valor de `DB_PASSWORD_DEV`)

### Migrations

As migrations são executadas automaticamente via Flyway na inicialização da aplicação.

Localização: `src/main/resources/db/migration/`

## 🔄 CI/CD

O projeto está configurado com workflows GitHub Actions:

- **PR Check:** Validação em Pull Requests
- **CI/CD Development:** Deploy automático em `develop`
- **CI/CD Staging:** Deploy em `staging` ou `release/*`
- **CI/CD Production:** Deploy em `main`

### Workflows Reutilizáveis

Os workflows utilizam os templates reutilizáveis do repositório de infraestrutura.

## 📝 Endpoints

- **Health Check (Actuator):** `http://localhost:8080/actuator/health`
- **API Health:** `http://localhost:8080/api/v1/health`
- **Examples API:**
  - `GET /api/v1/examples` - Listar todos
  - `GET /api/v1/examples/{id}` - Buscar por ID
  - `POST /api/v1/examples` - Criar novo
  - `DELETE /api/v1/examples/{id}` - Remover
- **Swagger UI:** `http://localhost:8080/swagger-ui/index.html`
- **API Docs:** `http://localhost:8080/v3/api-docs`

## 🔧 Configuração

### Variáveis de Ambiente

Crie um arquivo `.env.dev` (opcional):

```env
DB_PASSWORD_DEV=YourStrong@Passw0rd
DB_HOST=localhost
DB_PORT=1433
DB_NAME=srv_fale_com_jesus
DB_USERNAME=sa
```

### Application Properties

As configurações estão em `src/main/resources/application.yml`

## 🧪 Testes

```bash
# Executar testes
./mvnw test

# Com coverage
./mvnw test jacoco:report
```

## 📦 Build

```bash
# Build da aplicação
./mvnw clean package

# Build da imagem Docker
docker build -t srv-fale-com-jesus:latest -f docker/Dockerfile .
```

## 🚀 Deploy

### Development

```bash
# Push para branch develop
git push origin develop

# O workflow GitHub Actions fará:
# 1. Build
# 2. Testes
# 3. Build Docker image
# 4. Push para registry
# 5. Deploy (se configurado)
```

## 📚 Próximos Passos

1. Implementar suas funcionalidades
2. Adicionar testes
3. Configurar deploy real (Kubernetes, etc.)
4. Adicionar monitoramento e logs

---

**Desenvolvido com ❤️ usando as pipelines de infraestrutura**

