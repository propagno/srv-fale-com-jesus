# 🚀 srv-fale-com-jesus

Microsserviço base desenvolvido com Spring Boot, arquitetura hexagonal e integrado com as pipelines de infraestrutura.

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

📖 **Guia completo:** Veja [QUICKSTART.md](QUICKSTART.md)

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

📖 **Detalhes:** Veja [ARCHITECTURE.md](ARCHITECTURE.md)

## 🗄️ Banco de Dados

O banco de dados é gerenciado pelo repositório **db-propagno**.

**Conexão:**
- Host: `db-dev` (Docker) ou `localhost` (externo)
- Porta: `1433` (dev), `1434` (staging), `1435` (prod)
- Database: `propagno_db`
- User: `sa`
- Password: Configurada em `.env.dev`

📖 **Detalhes:** Veja [DATABASE-CONNECTION.md](DATABASE-CONNECTION.md)

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

### Checklist Antes de PR

Consulte [CHECKLIST-DESENVOLVEDOR.md](CHECKLIST-DESENVOLVEDOR.md) para garantir que seu PR está completo.

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

## 🐛 Troubleshooting

Consulte [TROUBLESHOOTING.md](TROUBLESHOOTING.md) para resolução de problemas comuns.

## 📚 Documentação

- **[QUICKSTART.md](QUICKSTART.md)** - Início rápido
- **[ARCHITECTURE.md](ARCHITECTURE.md)** - Arquitetura hexagonal
- **[DATABASE-CONNECTION.md](DATABASE-CONNECTION.md)** - Conexão com banco
- **[CONTRIBUTING.md](CONTRIBUTING.md)** - Guia de contribuição
- **[CHECKLIST-DESENVOLVEDOR.md](CHECKLIST-DESENVOLVEDOR.md)** - Checklist para PRs
- **[TROUBLESHOOTING.md](TROUBLESHOOTING.md)** - Resolução de problemas
- **[DEPLOY-INFO.md](DEPLOY-INFO.md)** - Informações sobre deploy
- **[REFINAMENTO-TECNICO.md](REFINAMENTO-TECNICO.md)** - Análise técnica
- **[RESUMO-REFINAMENTO.md](RESUMO-REFINAMENTO.md)** - Resumo das melhorias

## 🔒 Segurança

- ✅ Dependabot configurado
- ✅ CodeQL scanning ativo
- ✅ Validação de secrets em PRs
- ✅ Pre-commit hooks para validação
- ✅ Coverage mínimo de 70%

## 🎯 Próximos Passos

1. Execute o setup: `./scripts/setup.sh`
2. Leia o [QUICKSTART.md](QUICKSTART.md)
3. Desenvolva suas features
4. Siga o [CHECKLIST-DESENVOLVEDOR.md](CHECKLIST-DESENVOLVEDOR.md)
5. Crie seu PR!

---

**Desenvolvido com ❤️ usando as pipelines de infraestrutura Propagno** 🚀
