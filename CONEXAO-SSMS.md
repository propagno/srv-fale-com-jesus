# 🔌 Dados de Conexão - SQL Server Management Studio (SSMS)

## Ambiente de Desenvolvimento (DEV)

### Configurações de Conexão:

**Server name:**
```
localhost,1433
```
ou
```
127.0.0.1,1433
```

**Authentication:**
- SQL Server Authentication

**Login:**
```
sa
```

**Password:**
```
YourStrong@Passw0rd
```

**Database (opcional):**
```
srv_fale_com_jesus
```

## 📝 Passo a Passo para Conectar

1. Abra o **SQL Server Management Studio (SSMS)**

2. Na tela de conexão, preencha:
   - **Server type:** Database Engine
   - **Server name:** `localhost,1433`
   - **Authentication:** SQL Server Authentication
   - **Login:** `sa`
   - **Password:** `YourStrong@Passw0rd`

3. **IMPORTANTE:** Clique em **Options >>** (Opções) e na aba **Connection Properties** (Propriedades de Conexão):
   - Marque a opção **"Trust server certificate"** (Confiar no certificado do servidor)
   - Ou adicione na string de conexão: `TrustServerCertificate=True`

4. Clique em **Connect**

5. Se necessário, expanda **Databases** e procure por `srv_fale_com_jesus`

## 🔍 Verificar se o Container está Rodando

```bash
docker ps
```

Você deve ver o container `db-dev` rodando na porta `1433`.

## ⚠️ Problemas Comuns

### "A cadeia de certificação foi emitida por uma autoridade que não é de confiança"
**Solução:**
1. Na tela de conexão do SSMS, clique em **Options >>** (Opções)
2. Vá para a aba **Connection Properties** (Propriedades de Conexão)
3. Marque a opção **"Trust server certificate"** (Confiar no certificado do servidor)
4. Tente conectar novamente

**Alternativa (String de Conexão):**
Se preferir usar string de conexão, adicione `TrustServerCertificate=True`:
```
Server=localhost,1433;Database=srv_fale_com_jesus;User Id=sa;Password=YourStrong@Passw0rd;TrustServerCertificate=True;
```

### "Cannot connect to server"
- Verifique se o container está rodando: `docker ps`
- Verifique se a porta 1433 está livre
- Aguarde alguns segundos após iniciar o container (SQL Server leva tempo para inicializar)

### "Login failed"
- Verifique se a senha está correta: `YourStrong@Passw0rd`
- Verifique se o container está completamente inicializado (pode levar 30-60 segundos)

### "Database does not exist"
- O banco será criado automaticamente pelo script `wait-for-db.sh`
- Ou crie manualmente via SSMS após conectar

## 🗄️ Criar Banco Manualmente (se necessário)

Após conectar no SSMS, execute:

```sql
IF NOT EXISTS (SELECT * FROM sys.databases WHERE name = 'srv_fale_com_jesus')
BEGIN
    CREATE DATABASE srv_fale_com_jesus;
END
GO
```

## 📊 Outros Ambientes

### Staging
- **Server:** `localhost,1434`
- **Login:** `sa`
- **Password:** `YourStrong@Passw0rd` (ou valor de `DB_PASSWORD_STAGING`)

### Produção
- **Server:** `localhost,1435`
- **Login:** `sa`
- **Password:** `YourStrong@Passw0rd` (ou valor de `DB_PASSWORD_PROD`)

---

**Dados de conexão prontos para uso!** ✅

