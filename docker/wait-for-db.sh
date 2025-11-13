#!/bin/bash
set -e

echo "Waiting for SQL Server to be ready..."

# Tenta diferentes caminhos do sqlcmd
SQLCMD=""
for path in /opt/mssql-tools18/bin/sqlcmd /opt/mssql-tools/bin/sqlcmd /opt/mssql/bin/sqlcmd; do
    if [ -f "$path" ]; then
        SQLCMD="$path"
        break
    fi
done

if [ -z "$SQLCMD" ]; then
    echo "ERROR: sqlcmd not found!"
    exit 1
fi

echo "Using sqlcmd at: $SQLCMD"

# Aguarda o SQL Server estar pronto
echo "Waiting for SQL Server to accept connections..."
for i in {1..30}; do
    if $SQLCMD -S db-dev -U sa -P "$DB_PASSWORD" -C -Q "SELECT 1" &> /dev/null; then
        echo "SQL Server is ready!"
        break
    fi
    if [ $i -eq 30 ]; then
        echo "ERROR: SQL Server did not become ready in time!"
        exit 1
    fi
    echo "Attempt $i/30: Waiting for SQL Server..."
    sleep 2
done

# Cria o banco de dados se não existir
echo "Creating database if not exists..."
$SQLCMD -S db-dev -U sa -P "$DB_PASSWORD" -C -Q "
IF NOT EXISTS (SELECT * FROM sys.databases WHERE name = 'srv_fale_com_jesus')
BEGIN
    CREATE DATABASE srv_fale_com_jesus;
    PRINT 'Database srv_fale_com_jesus created successfully';
END
ELSE
BEGIN
    PRINT 'Database srv_fale_com_jesus already exists';
END
" || echo "Note: Database might already exist or error occurred (this is OK)"

echo "Database initialization completed!"

