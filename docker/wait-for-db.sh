#!/bin/bash
set -e

echo "Waiting for SQL Server to be ready..."

# Aguarda o SQL Server estar acessível via rede
# Usa netcat ou timeout para verificar se a porta está aberta
echo "Waiting for SQL Server to accept connections..."
for i in {1..60}; do
    # Verifica se a porta 1433 está aberta no host db-dev
    if timeout 2 bash -c "cat < /dev/null > /dev/tcp/db-dev/1433" 2>/dev/null; then
        echo "SQL Server is ready!"
        break
    fi
    if [ $i -eq 60 ]; then
        echo "WARNING: SQL Server did not become ready in time, but continuing anyway..."
        break
    fi
    echo "Attempt $i/60: Waiting for SQL Server..."
    sleep 2
done

echo "Database connection check completed!"
echo "Note: Database migrations are managed by db-propagno repository."

