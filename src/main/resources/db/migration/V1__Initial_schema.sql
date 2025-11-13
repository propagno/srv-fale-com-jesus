-- Migration inicial
-- Criação da estrutura base do banco de dados

IF NOT EXISTS (SELECT * FROM sys.databases WHERE name = 'srv_fale_com_jesus')
BEGIN
    CREATE DATABASE srv_fale_com_jesus;
END
GO

USE srv_fale_com_jesus;
GO

-- Exemplo de tabela (ajuste conforme sua necessidade)
IF NOT EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[example_table]') AND type in (N'U'))
BEGIN
    CREATE TABLE example_table (
        id BIGINT IDENTITY(1,1) PRIMARY KEY,
        name NVARCHAR(255) NOT NULL,
        description NVARCHAR(MAX),
        created_at DATETIME2 DEFAULT GETDATE(),
        updated_at DATETIME2 DEFAULT GETDATE()
    );
    
    CREATE INDEX idx_example_table_name ON example_table(name);
    CREATE INDEX idx_example_table_created_at ON example_table(created_at);
END
GO

