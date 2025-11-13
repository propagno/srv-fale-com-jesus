#!/bin/bash

# Script de setup automatizado para desenvolvedores
# Uso: ./scripts/setup.sh

set -e

echo "🚀 Configurando ambiente de desenvolvimento..."

# Cores para output
RED='\033[0;31m'
GREEN='\033[0;32m'
YELLOW='\033[1;33m'
NC='\033[0m' # No Color

# Função para verificar pré-requisitos
check_prerequisite() {
    if command -v $1 &> /dev/null; then
        echo -e "${GREEN}✅ $1 instalado${NC}"
        return 0
    else
        echo -e "${RED}❌ $1 não encontrado${NC}"
        return 1
    fi
}

# Verificar pré-requisitos
echo ""
echo "📋 Verificando pré-requisitos..."
MISSING=0

check_prerequisite "java" || MISSING=1
check_prerequisite "mvn" || check_prerequisite "./mvnw" || MISSING=1
check_prerequisite "docker" || MISSING=1
check_prerequisite "docker-compose" || MISSING=1

if [ $MISSING -eq 1 ]; then
    echo -e "${RED}❌ Alguns pré-requisitos estão faltando. Por favor, instale-os antes de continuar.${NC}"
    exit 1
fi

# Verificar versão do Java
echo ""
echo "📋 Verificando versão do Java..."
JAVA_VERSION=$(java -version 2>&1 | head -n 1 | cut -d'"' -f2 | sed '/^1\./s///' | cut -d'.' -f1)
if [ "$JAVA_VERSION" -lt 17 ]; then
    echo -e "${YELLOW}⚠️  Java 17+ é recomendado. Versão atual: $JAVA_VERSION${NC}"
else
    echo -e "${GREEN}✅ Java $JAVA_VERSION OK${NC}"
fi

# Criar arquivo .env se não existir
echo ""
echo "📝 Configurando variáveis de ambiente..."
if [ ! -f ".env.dev" ]; then
    if [ -f ".env.example" ]; then
        cp .env.example .env.dev
        echo -e "${GREEN}✅ Arquivo .env.dev criado a partir de .env.example${NC}"
        echo -e "${YELLOW}⚠️  Por favor, edite .env.dev com suas configurações${NC}"
    else
        echo -e "${YELLOW}⚠️  Arquivo .env.example não encontrado${NC}"
    fi
else
    echo -e "${GREEN}✅ Arquivo .env.dev já existe${NC}"
fi

# Verificar se o banco de dados está rodando
echo ""
echo "🗄️  Verificando banco de dados..."
if docker ps | grep -q "db-dev\|db-propagno-dev"; then
    echo -e "${GREEN}✅ Banco de dados está rodando${NC}"
else
    echo -e "${YELLOW}⚠️  Banco de dados não está rodando${NC}"
    echo -e "${YELLOW}   Execute: cd ../db-propagno && ./scripts/init.sh dev${NC}"
fi

# Verificar conectividade com banco
echo ""
echo "🔌 Testando conectividade com banco..."
if timeout 2 bash -c "cat < /dev/null > /dev/tcp/localhost/1433" 2>/dev/null; then
    echo -e "${GREEN}✅ Banco de dados acessível na porta 1433${NC}"
else
    echo -e "${YELLOW}⚠️  Não foi possível conectar ao banco na porta 1433${NC}"
fi

# Build do projeto
echo ""
echo "🔨 Fazendo build do projeto..."
if [ -f "./mvnw" ]; then
    ./mvnw clean install -DskipTests
else
    mvn clean install -DskipTests
fi

if [ $? -eq 0 ]; then
    echo -e "${GREEN}✅ Build concluído com sucesso${NC}"
else
    echo -e "${RED}❌ Build falhou${NC}"
    exit 1
fi

# Resumo
echo ""
echo -e "${GREEN}✅ Setup concluído!${NC}"
echo ""
echo "📋 Próximos passos:"
echo "  1. Edite .env.dev com suas configurações"
echo "  2. Inicie o banco de dados (se ainda não iniciou):"
echo "     cd ../db-propagno && ./scripts/init.sh dev"
echo "  3. Execute a aplicação:"
echo "     ./mvnw spring-boot:run"
echo "     ou"
echo "     docker-compose -f docker-compose.dev.yml up -d"
echo ""
echo "📚 Documentação:"
echo "  - Quick Start: QUICKSTART.md"
echo "  - README: README.md"
echo ""

