# 🤝 Guia de Contribuição

Obrigado por considerar contribuir com o projeto! Este documento fornece diretrizes para contribuições.

## 📋 Como Contribuir

### 1. Fork e Clone

```bash
# Fork o repositório no GitHub
# Depois clone seu fork
git clone git@github.com:SEU-USUARIO/srv-fale-com-jesus.git
cd srv-fale-com-jesus
```

### 2. Crie uma Branch

```bash
# Crie uma branch para sua feature
git checkout -b feature/minha-feature

# Ou para correção de bug
git checkout -b fix/correcao-bug
```

### 3. Desenvolva

- Siga a arquitetura hexagonal
- Escreva testes para novas funcionalidades
- Mantenha a cobertura de testes acima de 70%
- Siga os padrões de código existentes

### 4. Commit

```bash
# Use mensagens de commit descritivas
git commit -m "feat: adiciona funcionalidade X"
git commit -m "fix: corrige bug Y"
git commit -m "docs: atualiza documentação"
```

**Convenção de Commits:**
- `feat:` Nova funcionalidade
- `fix:` Correção de bug
- `docs:` Documentação
- `style:` Formatação
- `refactor:` Refatoração
- `test:` Testes
- `chore:` Manutenção

### 5. Push e Pull Request

```bash
# Push para seu fork
git push origin feature/minha-feature

# Crie um Pull Request no GitHub
```

## ✅ Checklist de PR

Antes de criar um PR, verifique:

- [ ] Código segue os padrões do projeto
- [ ] Testes passam (`./mvnw test`)
- [ ] Cobertura de testes mantida ou aumentada
- [ ] Documentação atualizada (se necessário)
- [ ] Commits seguem a convenção
- [ ] Build passa no CI/CD
- [ ] Não há conflitos com a branch base

## 🧪 Testes

```bash
# Executar todos os testes
./mvnw test

# Com coverage
./mvnw test jacoco:report

# Verificar coverage
open target/site/jacoco/index.html
```

## 📝 Padrões de Código

- Use Java 17 features quando apropriado
- Siga SOLID principles
- Mantenha arquitetura hexagonal
- Use Lombok para reduzir boilerplate
- Documente classes e métodos públicos

## 🚫 O que NÃO fazer

- ❌ Commitar secrets ou senhas
- ❌ Commitar arquivos .env
- ❌ Quebrar testes existentes
- ❌ Ignorar feedback de code review
- ❌ Criar PRs muito grandes (divida em PRs menores)

## 📚 Recursos

- [Arquitetura Hexagonal](ARCHITECTURE.md)
- [README](README.md)
- [Quick Start](QUICKSTART.md)

---

**Obrigado por contribuir!** 🎉

