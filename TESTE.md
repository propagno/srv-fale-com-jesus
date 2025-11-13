# 🧪 Teste do Repositório srv-fale-com-jesus

Este documento registra os testes realizados no repositório base.

## ✅ Teste 1: Verificação de Estrutura

### Estrutura de Arquitetura Hexagonal
- [x] Domain criado (entities, exceptions)
- [x] Application criado (ports, services, DTOs)
- [x] Infrastructure criado (persistence adapters)
- [x] Adapters criado (REST controllers)

### Arquivos de Configuração
- [x] pom.xml configurado
- [x] application.yml configurado
- [x] Dockerfile criado
- [x] docker-compose.dev.yml criado
- [x] Migrations Flyway criadas

### GitHub Actions
- [x] Workflows copiados da infraestrutura
- [x] Workflows reutilizáveis configurados

## ✅ Teste 2: Build Local (Maven)

Execute para testar:

```bash
cd C:\Users\dudu-\srv-fale-com-jesus
mvn clean compile
```

**Resultado esperado:** Build bem-sucedido

## ✅ Teste 3: Ambiente Docker Local

### Pré-requisito
- Docker Desktop deve estar rodando

### Comando
```bash
docker-compose -f docker-compose.dev.yml up -d
```

### Verificações
1. **Banco de dados:**
   ```bash
   docker-compose -f docker-compose.dev.yml ps db-dev
   ```

2. **Aplicação:**
   ```bash
   docker-compose -f docker-compose.dev.yml ps app-dev
   ```

3. **Health Check:**
   ```bash
   curl http://localhost:8080/actuator/health
   ```

4. **Swagger:**
   - Abrir: `http://localhost:8080/swagger-ui.html`

5. **Banco via SSMS:**
   - Server: `localhost,1433`
   - Login: `sa`
   - Password: `YourStrong@Passw0rd`

## ✅ Teste 4: GitHub Actions

### Teste via Push
```bash
git add .
git commit -m "test: primeiro teste"
git push origin develop
```

### Verificações
1. Vá em **Actions** no GitHub
2. Verifique se o workflow **CI/CD - Development** iniciou
3. Aguarde conclusão
4. Verifique se a imagem foi criada em **Packages**

## 📊 Checklist de Testes

- [ ] Build Maven local funciona
- [ ] Docker Compose sobe corretamente
- [ ] Aplicação responde em `http://localhost:8080`
- [ ] Swagger acessível
- [ ] Banco de dados acessível via SSMS
- [ ] Migrations executam automaticamente
- [ ] GitHub Actions roda ao fazer push
- [ ] Imagem Docker é criada e publicada

## 🐛 Problemas Conhecidos

### Docker Desktop não está rodando
**Solução:** Inicie o Docker Desktop antes de executar docker-compose

### Maven Wrapper não encontrado
**Solução:** Execute `mvn wrapper:wrapper` ou use Maven instalado

### Banco de dados não conecta
**Solução:** 
1. Verifique se o container está rodando: `docker ps`
2. Aguarde o health check (pode levar 30-60 segundos)
3. Verifique logs: `docker-compose logs db-dev`

## 📝 Próximos Testes

1. Testar endpoints da API via Swagger
2. Testar CRUD completo de Example
3. Testar tratamento de exceções
4. Testar deploy em ambiente remoto

---

**Status:** Estrutura criada e pronta para testes

