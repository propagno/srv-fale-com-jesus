# 🏗️ Arquitetura Hexagonal

Este projeto segue os princípios da **Arquitetura Hexagonal** (também conhecida como Ports and Adapters).

## 📐 Estrutura de Camadas

```
src/main/java/br/com/propagno/falecomjesus/
│
├── domain/                          # NÚCLEO - Regras de Negócio
│   ├── entity/                      # Entidades de domínio
│   │   ├── BaseEntity.java
│   │   └── ExampleEntity.java
│   └── exception/                   # Exceções de domínio
│       ├── DomainException.java
│       └── EntityNotFoundException.java
│
├── application/                     # CASOS DE USO
│   ├── port/
│   │   ├── input/                   # Ports de entrada (interfaces)
│   │   │   ├── HealthCheckUseCase.java
│   │   │   └── ExampleUseCase.java
│   │   └── output/                  # Ports de saída (interfaces)
│   │       └── ExampleRepositoryPort.java
│   ├── service/                     # Implementação dos casos de uso
│   │   ├── HealthCheckService.java
│   │   └── ExampleService.java
│   └── dto/                         # DTOs da camada de aplicação
│       └── HealthCheckDTO.java
│
├── infrastructure/                  # ADAPTADORES DE SAÍDA
│   └── persistence/
│       ├── entity/                  # Entidades JPA
│       │   └── ExampleEntityJpa.java
│       ├── repository/               # Repositories JPA
│       │   └── ExampleJpaRepository.java
│       ├── adapter/                  # Adaptadores que implementam ports
│       │   └── ExampleRepositoryAdapter.java
│       └── mapper/                  # Mappers entre domínio e JPA
│           └── ExampleEntityMapper.java
│
└── adapter/                         # ADAPTADORES DE ENTRADA
    └── input/
        └── rest/
            ├── controller/          # Controllers REST
            │   ├── HealthController.java
            │   └── ExampleController.java
            ├── dto/                 # DTOs REST
            │   ├── HealthCheckResponse.java
            │   ├── ExampleRequest.java
            │   └── ExampleResponse.java
            └── exception/           # Tratamento de exceções REST
                ├── GlobalExceptionHandler.java
                └── ErrorResponse.java
```

## 🎯 Princípios

### 1. Domain (Núcleo)
- **Sem dependências externas**
- Contém apenas lógica de negócio
- Entidades puras (sem anotações JPA)
- Exceções de domínio

### 2. Application (Casos de Uso)
- **Depende apenas do Domain**
- Define interfaces (Ports)
- Implementa casos de uso
- DTOs para comunicação

### 3. Infrastructure (Adaptadores de Saída)
- **Implementa Ports de saída**
- Persistência (JPA, SQL Server)
- Integrações externas
- Mappers entre domínio e infraestrutura

### 4. Adapters (Adaptadores de Entrada)
- **Implementa Ports de entrada**
- REST Controllers
- Message Listeners
- CLI

## 🔄 Fluxo de Dados

```
REST Controller (Adapter)
    ↓
Use Case Interface (Port Input)
    ↓
Service (Application)
    ↓
Repository Interface (Port Output)
    ↓
Repository Adapter (Infrastructure)
    ↓
JPA Repository
    ↓
Database
```

## 📝 Exemplo de Uso

### Criar uma nova funcionalidade

1. **Domain:**
   - Criar entidade em `domain/entity/`
   - Criar exceções se necessário

2. **Application:**
   - Criar Port de entrada em `application/port/input/`
   - Criar Port de saída em `application/port/output/`
   - Implementar Service em `application/service/`

3. **Infrastructure:**
   - Criar entidade JPA em `infrastructure/persistence/entity/`
   - Criar Repository JPA em `infrastructure/persistence/repository/`
   - Criar Adapter em `infrastructure/persistence/adapter/`
   - Criar Mapper em `infrastructure/persistence/mapper/`

4. **Adapter:**
   - Criar Controller em `adapter/input/rest/controller/`
   - Criar DTOs em `adapter/input/rest/dto/`

## ✅ Vantagens

- **Testabilidade:** Fácil criar mocks dos ports
- **Independência:** Domain não depende de frameworks
- **Flexibilidade:** Trocar implementações sem afetar o núcleo
- **Manutenibilidade:** Separação clara de responsabilidades

## 🔧 Configuração

A aplicação está configurada para:
- Escanear apenas os adapters para REST
- Mapear entidades JPA separadas do domínio
- Injetar dependências via interfaces (ports)

---

**Arquitetura limpa e testável!** 🎯

