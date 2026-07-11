# Guilda API

API REST desenvolvida em Java para a disciplina, utilizando banco de dados MySQL.

## Tecnologias utilizadas

- Java 24.0.1; OpenJDK 64-Bit Server VM 24.0.1+9
- Apache NetBeans IDE 26
- Maven 3.9.16
- MySQL 8.0.43
- Gson
- HttpServer (Java)
- Git e GitHub

## Funcionalidades

### Caçadores
- Criar caçador
- Listar caçadores
- Buscar caçador por ID
- Atualizar caçador
- Remover caçador

### Monstros
- Criar monstro
- Listar monstros
- Buscar monstro por ID
- Atualizar monstro
- Remover monstro

### Missões
- Criar missão
- Listar missões
- Buscar missão por ID
- Atualizar missão
- Remover missão

## Banco de dados

O script de criação do banco encontra-se no arquivo:

```
guilda.sql
```

## Como executar

1. Clone o repositório:

```bash
git clone https://github.com/prodwillzera/Guilda-API.git
```

2. Importe o arquivo `scheme.sql` no MySQL.

3. Configure as credenciais do banco no arquivo `DatabaseConnection.java`.

4. Compile o projeto:

```bash
mvn clean package
```

5. Execute:

```bash
java -jar target/guilda-api-1.0.0.jar
```

O servidor iniciará em:

```
http://localhost:8080
```

## Autor

William Lopes Batista
