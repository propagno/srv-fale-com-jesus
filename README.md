# 🚀 srv-fale-com-jesus

Microsserviço base desenvolvido com Spring Boot, arquitetura hexagonal e integrado com as pipelines de infraestrutura.

> **Nota**: Workflows de PR foram consolidados para melhorar eficiência do CI/CD. Cada PR agora executa apenas **1 workflow** em vez de 2.

## 📋 Tecnologias

- **Java 17**
- **Spring Boot 3.2.0**
- **SQL Server 2022** (via db-propagno)
- **Docker** & **Docker Compose**
- **GitHub Actions** (CI/CD)
- **Arquitetura Hexagonal** (Ports and Adapters)

## 🚀 Quick Start

### Setup Automatizado (Recomendado)

```bash
# Execute o script de setup
./scripts/setup.sh        # Linux/Mac
.\scripts\setup.ps1       # Windows
```

O script irá:
- ✅ Verificar pré-requisitos
- ✅ Criar arquivo .env.dev
- ✅ Verificar conectividade com banco
- ✅ Fazer build do projeto

### Setup Manual

1. **Clone e configure:**
```bash
git clone git@github.com:propagno/srv-fale-com-jesus.git
cd srv-fale-com-jesus
cp .env.example .env.dev
# Edite .env.dev com suas configurações
```

2. **Inicie o banco de dados:**
```bash
cd ../db-propagno
./scripts/init.sh dev
```

3. **Execute a aplicação:**
```bash
cd srv-fale-com-jesus
docker-compose -f docker-compose.dev.yml up -d
# ou
./mvnw spring-boot:run
```

4. **Acesse:**
- Swagger: http://localhost:8080/swagger-ui/index.html
- Health: http://localhost:8080/actuator/health

## 🏗️ Arquitetura

Este projeto utiliza **Arquitetura Hexagonal** (Ports and Adapters).

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

**Princípios:**
- **Domain**: Sem dependências externas, apenas lógica de negócio
- **Application**: Depende apenas do Domain, define interfaces (Ports)
- **Infrastructure**: Implementa Ports de saída (persistência, integrações)
- **Adapters**: Implementa Ports de entrada (REST, CLI, etc.)

## 🗄️ Banco de Dados

O banco de dados é gerenciado pelo repositório **db-propagno**.

**Conexão:**
- Host: `db-dev` (Docker) ou `localhost` (externo)
- Porta: `1433` (dev), `1434` (staging), `1435` (prod)
- Database: `propagno_db`
- User: `sa`
- Password: Configurada em `.env.dev`

**JDBC URL:**
```
jdbc:sqlserver://${DB_HOST}:${DB_PORT};databaseName=${DB_NAME};encrypt=true;trustServerCertificate=true
```

**Importante:**
- O banco deve estar rodando antes de iniciar este serviço
- Migrations são gerenciadas pelo `db-propagno` com Liquibase
- Este serviço não executa migrations (Flyway desabilitado)

## 🔄 CI/CD

### Workflows Disponíveis

- **PR Check:** Validação em Pull Requests
  - Testes
  - Build
  - Validação de coverage (>= 70%)
  - Validação de secrets
  - Validação de .env files

- **CI/CD Development:** Deploy automático em `develop`
- **CI/CD Staging:** Deploy em `staging` ou `release/*`
- **CI/CD Production:** Deploy em `main`

### Validações Automáticas

- ✅ Dependabot para atualizações de dependências
- ✅ CodeQL para análise de segurança
- ✅ Validação de secrets em PRs
- ✅ Validação de coverage de testes
- ✅ Validação de documentação

## 📝 Desenvolvimento

### Convenção de Commits

```
feat: adiciona nova funcionalidade
fix: corrige bug
docs: atualiza documentação
refactor: refatora código
test: adiciona testes
chore: manutenção
```

### Testes

```bash
# Executar testes
./mvnw test

# Com coverage
./mvnw test jacoco:report

# Verificar coverage (deve ser >= 70%)
./mvnw jacoco:check
```

### Checklist Antes de PR

- [ ] Testes passam (`./mvnw test`)
- [ ] Cobertura de testes >= 70%
- [ ] Build funciona (`./mvnw clean package`)
- [ ] Docker build funciona
- [ ] Nenhum arquivo `.env` no commit
- [ ] Mensagem de commit segue a convenção
- [ ] Documentação atualizada (se necessário)
- [ ] Código segue os padrões do projeto
- [ ] Arquitetura hexagonal respeitada

## 🐛 Troubleshooting

### Erro: "Cannot connect to database"

```bash
# Verifique se o banco está rodando
docker ps | grep db-dev

# Se não estiver, inicie:
cd ../db-propagno
docker-compose up -d db-dev
./scripts/init.sh dev
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

### Erro: "Network db-propagno-network not found"

```bash
# Crie a network manualmente
docker network create db-propagno-network

# Ou inicie o banco primeiro (ele criará a network)
cd ../db-propagno
docker-compose up -d db-dev
```

### Erro: "Dependency resolution failed"

```bash
# Limpe o cache do Maven
./mvnw clean
rm -rf ~/.m2/repository

# Force update
./mvnw clean install -U
```

## 🔒 Segurança

- ✅ Dependabot configurado
- ✅ CodeQL scanning ativo
- ✅ Validação de secrets em PRs
- ✅ Pre-commit hooks para validação
- ✅ Coverage mínimo de 70%
- ✅ Nenhum secret hardcoded
- ✅ Inputs validados
- ✅ SQL injection prevenido (JPA/PreparedStatements)

## 📚 Contribuindo

1. Fork o repositório
2. Crie uma branch: `git checkout -b feature/minha-feature`
3. Desenvolva seguindo a arquitetura hexagonal
4. Escreva testes (cobertura >= 70%)
5. Commit: `git commit -m "feat: adiciona funcionalidade X"`
6. Push: `git push origin feature/minha-feature`
7. Abra um Pull Request

**O que NÃO fazer:**
- ❌ Commitar secrets ou senhas
- ❌ Commitar arquivos .env
- ❌ Quebrar testes existentes
- ❌ Ignorar feedback de code review
- ❌ Criar PRs muito grandes (divida em PRs menores)

## 🎯 Próximos Passos

1. Execute o setup: `./scripts/setup.sh`
2. Desenvolva suas features
3. Siga o checklist antes de criar PR
4. Crie seu PR!

---

**Desenvolvido com ❤️ usando as pipelines de infraestrutura Propagno** 🚀
