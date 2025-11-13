# ✅ Checklist para Desenvolvedores

Use este checklist antes de criar um PR ou fazer deploy.

## 🚀 Antes de Começar

- [ ] Li o [README.md](README.md)
- [ ] Li o [QUICKSTART.md](QUICKSTART.md)
- [ ] Executei o script de setup: `./scripts/setup.sh` ou `.\scripts\setup.ps1`
- [ ] Configurei o arquivo `.env.dev`
- [ ] Testei a aplicação localmente
- [ ] Entendi a arquitetura hexagonal (veja [ARCHITECTURE.md](ARCHITECTURE.md))

## 💻 Durante o Desenvolvimento

- [ ] Código segue os padrões do projeto
- [ ] Arquitetura hexagonal respeitada
- [ ] Nenhum secret hardcoded no código
- [ ] Variáveis de ambiente usadas para configurações sensíveis
- [ ] Logs apropriados adicionados
- [ ] Tratamento de erros implementado

## 🧪 Antes de Commitar

- [ ] Testes passam: `./mvnw test`
- [ ] Cobertura de testes >= 70%
- [ ] Build funciona: `./mvnw clean package`
- [ ] Docker build funciona: `docker build -f docker/Dockerfile .`
- [ ] Nenhum arquivo `.env` no commit
- [ ] Mensagem de commit segue a convenção (feat:, fix:, etc.)

## 📝 Antes de Criar PR

- [ ] Todos os testes passam
- [ ] Documentação atualizada (se necessário)
- [ ] PR tem título descritivo seguindo convenção
- [ ] PR tem descrição clara do que foi feito
- [ ] Código revisado por você mesmo
- [ ] Nenhum warning do linter
- [ ] Build do CI/CD passa

## 🚢 Antes de Fazer Deploy

- [ ] Testado localmente
- [ ] Testado em ambiente de desenvolvimento
- [ ] Migrations do banco testadas (se houver)
- [ ] Health checks funcionando
- [ ] Logs sendo gerados corretamente
- [ ] Variáveis de ambiente configuradas no ambiente de destino

## 🔒 Segurança

- [ ] Nenhum secret no código
- [ ] Nenhum secret no histórico do Git
- [ ] Dependências atualizadas (sem vulnerabilidades conhecidas)
- [ ] Inputs validados
- [ ] SQL injection prevenido (usando JPA/PreparedStatements)

## 📚 Documentação

- [ ] README atualizado (se necessário)
- [ ] Comentários em código complexo
- [ ] Javadoc em métodos públicos
- [ ] Exemplos de uso (se nova feature)

## 🐛 Se Encontrar Problemas

1. Consulte [TROUBLESHOOTING.md](TROUBLESHOOTING.md)
2. Verifique os logs
3. Teste localmente
4. Abra uma issue se necessário

---

**Lembre-se:** Qualidade > Velocidade. Um PR bem feito é melhor que um PR rápido com problemas.

