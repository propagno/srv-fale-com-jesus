# Script de setup automatizado para desenvolvedores (PowerShell)
# Uso: .\scripts\setup.ps1

$ErrorActionPreference = "Stop"

Write-Host "🚀 Configurando ambiente de desenvolvimento..." -ForegroundColor Cyan

# Verificar pré-requisitos
Write-Host ""
Write-Host "📋 Verificando pré-requisitos..." -ForegroundColor Cyan

$missing = $false

# Java
try {
    $javaVersion = java -version 2>&1 | Select-String -Pattern "version" | ForEach-Object { $_.Line }
    Write-Host "✅ Java instalado: $javaVersion" -ForegroundColor Green
} catch {
    Write-Host "❌ Java não encontrado" -ForegroundColor Red
    $missing = $true
}

# Maven
if (Test-Path ".\mvnw.cmd") {
    Write-Host "✅ Maven Wrapper encontrado" -ForegroundColor Green
} elseif (Get-Command mvn -ErrorAction SilentlyContinue) {
    Write-Host "✅ Maven instalado" -ForegroundColor Green
} else {
    Write-Host "❌ Maven não encontrado" -ForegroundColor Red
    $missing = $true
}

# Docker
try {
    $dockerVersion = docker --version
    Write-Host "✅ Docker instalado: $dockerVersion" -ForegroundColor Green
} catch {
    Write-Host "❌ Docker não encontrado" -ForegroundColor Red
    $missing = $true
}

# Docker Compose
try {
    $composeVersion = docker-compose --version
    Write-Host "✅ Docker Compose instalado: $composeVersion" -ForegroundColor Green
} catch {
    Write-Host "❌ Docker Compose não encontrado" -ForegroundColor Red
    $missing = $true
}

if ($missing) {
    Write-Host ""
    Write-Host "❌ Alguns pré-requisitos estão faltando. Por favor, instale-os antes de continuar." -ForegroundColor Red
    exit 1
}

# Criar arquivo .env se não existir
Write-Host ""
Write-Host "📝 Configurando variáveis de ambiente..." -ForegroundColor Cyan
if (-not (Test-Path ".env.dev")) {
    if (Test-Path ".env.example") {
        Copy-Item ".env.example" ".env.dev"
        Write-Host "✅ Arquivo .env.dev criado a partir de .env.example" -ForegroundColor Green
        Write-Host "⚠️  Por favor, edite .env.dev com suas configurações" -ForegroundColor Yellow
    } else {
        Write-Host "⚠️  Arquivo .env.example não encontrado" -ForegroundColor Yellow
    }
} else {
    Write-Host "✅ Arquivo .env.dev já existe" -ForegroundColor Green
}

# Verificar se o banco de dados está rodando
Write-Host ""
Write-Host "🗄️  Verificando banco de dados..." -ForegroundColor Cyan
$dbRunning = docker ps --format "{{.Names}}" | Select-String -Pattern "db-dev|db-propagno-dev"
if ($dbRunning) {
    Write-Host "✅ Banco de dados está rodando: $dbRunning" -ForegroundColor Green
} else {
    Write-Host "⚠️  Banco de dados não está rodando" -ForegroundColor Yellow
    Write-Host "   Execute: cd ..\db-propagno && .\scripts\init.sh dev" -ForegroundColor Yellow
}

# Build do projeto
Write-Host ""
Write-Host "🔨 Fazendo build do projeto..." -ForegroundColor Cyan
if (Test-Path ".\mvnw.cmd") {
    .\mvnw.cmd clean install -DskipTests
} else {
    mvn clean install -DskipTests
}

if ($LASTEXITCODE -eq 0) {
    Write-Host "✅ Build concluído com sucesso" -ForegroundColor Green
} else {
    Write-Host "❌ Build falhou" -ForegroundColor Red
    exit 1
}

# Resumo
Write-Host ""
Write-Host "✅ Setup concluído!" -ForegroundColor Green
Write-Host ""
Write-Host "📋 Próximos passos:" -ForegroundColor Cyan
Write-Host "  1. Edite .env.dev com suas configurações"
Write-Host "  2. Inicie o banco de dados (se ainda não iniciou):"
Write-Host "     cd ..\db-propagno && .\scripts\init.sh dev"
Write-Host "  3. Execute a aplicação:"
Write-Host "     .\mvnw.cmd spring-boot:run"
Write-Host "     ou"
Write-Host "     docker-compose -f docker-compose.dev.yml up -d"
Write-Host ""
Write-Host "📚 Documentação:" -ForegroundColor Cyan
Write-Host "  - Quick Start: QUICKSTART.md"
Write-Host "  - README: README.md"
Write-Host ""

