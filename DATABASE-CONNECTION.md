# 🔗 Conexão com Banco de Dados Externo

Este serviço agora se conecta ao banco de dados gerenciado pelo repositório `db-propagno`.

## 📋 Configuração

### Variáveis de Ambiente

Configure as seguintes variáveis para conectar ao banco de dados:

```bash
# Host do banco de dados
DB_HOST=localhost  # ou IP do servidor

# Porta do banco de dados
DB_PORT=1433       # dev: 1433, staging: 1434, prod: 1435

# Nome do banco de dados
DB_NAME=propagno_db  # dev: propagno_db, staging: propagno_db_staging, prod: propagno_db_prod

# Credenciais
DB_USERNAME=sa
DB_PASSWORD_DEV=YourStrong@Passw0rd
DB_PASSWORD_STAGING=YourStrong@Passw0rd
DB_PASSWORD_PROD=YourStrong@Passw0rd
```

### Docker Compose

O `docker-compose.yml` está configurado para conectar à network externa `db-propagno-network`.

**Importante:** O banco de dados deve estar rodando antes de iniciar este serviço.

## 🚀 Como Usar

### 1. Iniciar o Banco de Dados

Primeiro, inicie o banco de dados no repositório `db-propagno`:

```bash
cd db-propagno
./scripts/init.sh dev  # ou staging, prod
```

### 2. Iniciar o Serviço

Depois, inicie este serviço:

```bash
cd srv-fale-com-jesus
docker-compose -f docker-compose.dev.yml up -d
```

### 3. Verificar Conexão

Verifique se a aplicação está conectada:

```bash
curl http://localhost:8080/actuator/health
```

## 🔧 Configuração Local

Para desenvolvimento local, você pode:

### Opção 1: Usar a mesma network Docker

Se o banco estiver rodando no mesmo host:

```bash
# No db-propagno
docker-compose up -d db-dev

# No srv-fale-com-jesus
docker-compose -f docker-compose.dev.yml up -d
```

O serviço se conectará automaticamente via network Docker.

### Opção 2: Conectar via host externo

Se o banco estiver em outro servidor:

```bash
export DB_HOST=192.168.1.100
export DB_PORT=1433
docker-compose -f docker-compose.dev.yml up -d
```

## 📝 JDBC URL

A URL de conexão é construída automaticamente:

```
jdbc:sqlserver://${DB_HOST}:${DB_PORT};databaseName=${DB_NAME};encrypt=true;trustServerCertificate=true
```

## ⚠️ Observações

1. **Flyway desabilitado**: Este serviço não executa migrations. As migrations são gerenciadas pelo `db-propagno` com Liquibase.

2. **Network externa**: O `docker-compose.yml` referencia a network `db-propagno-network` como externa. Certifique-se de que ela existe antes de iniciar o serviço.

3. **Ordem de inicialização**: Sempre inicie o banco de dados antes do serviço.

## 🐛 Troubleshooting

### Erro: "Network db-propagno-network not found"

Crie a network manualmente:

```bash
docker network create db-propagno-network
```

Ou inicie o banco de dados primeiro (ele criará a network).

### Erro: "Cannot connect to database"

1. Verifique se o banco está rodando:
```bash
docker ps | grep db-propagno
```

2. Verifique as variáveis de ambiente:
```bash
docker-compose -f docker-compose.dev.yml config
```

3. Teste a conexão manualmente:
```bash
docker exec -it db-propagno-dev /opt/mssql-tools/bin/sqlcmd -S localhost -U sa -P YourStrong@Passw0rd -Q "SELECT 1"
```

