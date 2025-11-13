# 📦 Informações sobre Deploy

## 🎯 Situação Atual

Atualmente, o workflow de CI/CD está configurado para:

1. ✅ **Build** - Compilar a aplicação Java com Maven
2. ✅ **Testes** - Executar testes automatizados
3. ✅ **Build Docker** - Criar imagem Docker da aplicação
4. ✅ **Push para Registry** - Enviar imagem para `ghcr.io/propagno/srv-fale-com-jesus`
5. ⚠️ **Deploy** - Apenas simulado (não faz deploy real ainda)

## 📍 Onde a Aplicação Está?

### Imagem Docker
A imagem Docker está sendo criada e armazenada no **GitHub Container Registry**:

```
ghcr.io/propagno/srv-fale-com-jesus:develop-latest
ghcr.io/propagno/srv-fale-com-jesus:develop-<sha>
```

**Como acessar:**
- GitHub: `https://github.com/propagno?tab=packages`
- Ou: `https://github.com/users/propagno/packages/container/srv-fale-com-jesus`

### Deploy Real
O deploy real **ainda não está configurado**. O workflow atual apenas imprime mensagens:

```yaml
- name: Deploy to Development
  run: |
    echo "🚀 Deploying to Development environment"
    echo "Image: ${{ needs.build.outputs.image-tag }}"
    # Comando real de deploy aqui (não implementado)
```

## 🚀 Opções para Deploy Real

### Opção 1: Deploy Local com Docker Compose (Mais Simples)

**Para desenvolvimento/testes locais:**

```bash
# Baixar a imagem do registry
docker pull ghcr.io/propagno/srv-fale-com-jesus:develop-latest

# Ou usar docker-compose que já está configurado
docker-compose -f docker-compose.dev.yml up -d
```

**Acesso:**
- Swagger: `http://localhost:8080/swagger-ui.html`
- Health: `http://localhost:8080/actuator/health`
- API: `http://localhost:8080/api/v1/health`

### Opção 2: Servidor VPS/Cloud (DigitalOcean, AWS EC2, etc.)

**Configuração necessária:**

1. **Criar servidor** com Docker instalado
2. **Configurar secrets** no GitHub Actions:
   - `DEPLOY_HOST` - IP do servidor
   - `DEPLOY_USER` - Usuário SSH
   - `DEPLOY_SSH_KEY` - Chave SSH privada

3. **Atualizar workflow** para fazer deploy via SSH:

```yaml
- name: Deploy to Server
  uses: appleboy/ssh-action@master
  with:
    host: ${{ secrets.DEPLOY_HOST }}
    username: ${{ secrets.DEPLOY_USER }}
    key: ${{ secrets.DEPLOY_SSH_KEY }}
    script: |
      docker pull ghcr.io/propagno/srv-fale-com-jesus:develop-latest
      docker-compose -f docker-compose.dev.yml up -d
```

**Acesso:**
- `http://<IP-DO-SERVIDOR>:8080/swagger-ui.html`

### Opção 3: Kubernetes (K8s)

**Configuração necessária:**

1. **Cluster Kubernetes** (GKE, EKS, AKS, ou local com minikube)
2. **Configurar kubectl** no workflow
3. **Criar manifests** (deployment.yaml, service.yaml)
4. **Atualizar workflow** para aplicar manifests

**Exemplo de workflow:**

```yaml
- name: Deploy to Kubernetes
  run: |
    kubectl set image deployment/srv-fale-com-jesus \
      app=${{ needs.build.outputs.image-tag }} \
      -n development
```

**Acesso:**
- Via ingress ou port-forward: `kubectl port-forward svc/srv-fale-com-jesus 8080:8080`

### Opção 4: Plataformas Gerenciadas

#### Heroku
```yaml
- name: Deploy to Heroku
  uses: akhileshns/heroku-deploy@v3.12.12
  with:
    heroku_api_key: ${{ secrets.HEROKU_API_KEY }}
    heroku_app_name: "srv-fale-com-jesus-dev"
    heroku_email: "seu-email@example.com"
```

#### Railway
```yaml
- name: Deploy to Railway
  uses: bervProject/railway-deploy@main
  with:
    railway_token: ${{ secrets.RAILWAY_TOKEN }}
    service: srv-fale-com-jesus
```

#### Fly.io
```yaml
- name: Deploy to Fly.io
  uses: superfly/flyctl-actions/setup-flyctl@master
- run: flyctl deploy --remote-only
  env:
    FLY_API_TOKEN: ${{ secrets.FLY_API_TOKEN }}
```

## 🔧 Como Implementar Deploy Real

### Passo 1: Escolher Opção
Decida qual opção você quer usar (VPS, Kubernetes, Plataforma gerenciada, etc.)

### Passo 2: Configurar Secrets
No GitHub, vá em **Settings > Secrets and variables > Actions** e adicione:
- Credenciais de acesso ao ambiente de deploy
- Chaves SSH (se usar servidor próprio)
- Tokens de API (se usar plataforma gerenciada)

### Passo 3: Atualizar Workflow
Substitua o step de deploy simulado por comandos reais:

```yaml
- name: Deploy to Development
  run: |
    # Comandos reais de deploy aqui
    # Exemplo para servidor SSH:
    ssh user@server "docker pull ${{ needs.build.outputs.image-tag }} && docker-compose up -d"
```

### Passo 4: Atualizar URL do Environment
Atualize a URL no workflow:

```yaml
environment:
  name: development
  url: http://seu-servidor-real.com  # URL real onde a aplicação estará
```

## 📊 Status Atual vs. Esperado

| Etapa | Status Atual | Onde Está |
|-------|-------------|-----------|
| Build | ✅ Funcionando | GitHub Actions |
| Testes | ✅ Funcionando | GitHub Actions |
| Imagem Docker | ✅ Criada | `ghcr.io/propagno/srv-fale-com-jesus` |
| Deploy | ⚠️ Simulado | Nenhum lugar (apenas echo) |

## 🎯 Próximos Passos Recomendados

1. **Para testes locais:**
   - Use `docker-compose.dev.yml` localmente
   - Ou faça pull da imagem do registry

2. **Para deploy em servidor:**
   - Configure servidor com Docker
   - Adicione secrets no GitHub
   - Atualize workflow com comandos SSH

3. **Para produção:**
   - Use Kubernetes ou plataforma gerenciada
   - Configure CI/CD completo
   - Adicione monitoramento e logs

## 📝 Exemplo: Deploy em Servidor VPS

Se você quiser implementar deploy em um servidor VPS, aqui está um exemplo completo:

```yaml
- name: Deploy to Development Server
  uses: appleboy/ssh-action@master
  with:
    host: ${{ secrets.DEPLOY_HOST }}
    username: ${{ secrets.DEPLOY_USER }}
    key: ${{ secrets.DEPLOY_SSH_KEY }}
    script: |
      # Login no GitHub Container Registry
      echo ${{ secrets.GITHUB_TOKEN }} | docker login ghcr.io -u ${{ github.actor }} --password-stdin
      
      # Pull da imagem mais recente
      docker pull ${{ needs.build.outputs.image-tag }}
      
      # Para containers antigos
      docker-compose -f /opt/srv-fale-com-jesus/docker-compose.dev.yml down
      
      # Atualiza imagem no compose
      export IMAGE_TAG=${{ needs.build.outputs.image-tag }}
      
      # Inicia novos containers
      docker-compose -f /opt/srv-fale-com-jesus/docker-compose.dev.yml up -d
      
      # Health check
      sleep 10
      curl -f http://localhost:8080/actuator/health || exit 1
```

---

**Resumo:** A aplicação está sendo buildada e a imagem Docker está no GitHub Container Registry, mas o deploy real ainda precisa ser configurado conforme sua necessidade (servidor, Kubernetes, plataforma gerenciada, etc.).

