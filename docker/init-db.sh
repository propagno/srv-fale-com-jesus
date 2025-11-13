#!/bin/bash
set -e

echo "Initializing SQL Server database..."

# Aguarda o SQL Server estar pronto
until /opt/mssql-tools/bin/sqlcmd -S localhost -U sa -P "$MSSQL_SA_PASSWORD" -Q "SELECT 1" -C &> /dev/null; do
  echo "Waiting for SQL Server to be ready..."
  sleep 2
done

echo "SQL Server is ready!"

# Cria o banco de dados se não existir
/opt/mssql-tools/bin/sqlcmd -S localhost -U sa -P "$MSSQL_SA_PASSWORD" -C -Q "
IF NOT EXISTS (SELECT * FROM sys.databases WHERE name = 'srv_fale_com_jesus')
BEGIN
    CREATE DATABASE srv_fale_com_jesus;
    PRINT 'Database srv_fale_com_jesus created successfully';
END
ELSE
BEGIN
    PRINT 'Database srv_fale_com_jesus already exists';
END
" || echo "Database might already exist or error occurred"

echo "Database initialization completed!"

