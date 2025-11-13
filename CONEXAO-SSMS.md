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

3. Clique em **Connect**

4. Se necessário, expanda **Databases** e procure por `srv_fale_com_jesus`

## 🔍 Verificar se o Container está Rodando

```bash
docker ps
```

Você deve ver o container `db-dev` rodando na porta `1433`.

## ⚠️ Problemas Comuns

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

