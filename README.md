# Biblioteca API

API REST para gerenciar o acervo e os empréstimos de uma biblioteca: cadastro de autores, livros, exemplares físicos e usuários, com registro e devolução de empréstimos.

Projeto pessoal de estudo, desenvolvido com Spring Boot e PostgreSQL.

<!-- Dica: salve um print do Swagger em docs/swagger.png e descomente a linha abaixo -->
<!-- ![Swagger UI](docs/swagger.png) -->

## Funcionalidades

- Cadastro, consulta, listagem e exclusão de **autores**, **livros** e **usuários**
- Cadastro de **exemplares** de um livro (várias cópias físicas do mesmo livro), cada uma com um código sequencial gerado pelo banco
- **Registro de empréstimo** de um exemplar para um usuário
- **Devolução** de empréstimo, que preenche a data de devolução e libera o exemplar (`DISPONIVEL`)
- Consulta de empréstimos por usuário
- Validação de dados de entrada com Bean Validation
- Tratamento centralizado de erros, com status HTTP adequados (400, 404, 422) e resposta padronizada
- Documentação interativa com Swagger UI

## Tecnologias

- Java 21+
- Spring Boot 3.5 (Web, Data JPA, Validation)
- Hibernate / JPA
- PostgreSQL 16
- Flyway (versionamento do banco)
- springdoc-openapi (Swagger UI)
- Maven

## Modelo de dados

```
Autor 1 ──── N Livro 1 ──── N Exemplar 1 ──── N Emprestimo N ──── 1 Usuario
```

- **Livro**: a obra (título, ano, gênero, autor)
- **Exemplar**: cópia física de um livro, com status `DISPONIVEL`, `EMPRESTADO` ou `PERDIDO`
- **Emprestimo**: liga um usuário a um exemplar, com data de empréstimo e data de devolução (nula enquanto o empréstimo está ativo)

O schema é criado e versionado pelas migrations do Flyway em `src/main/resources/db/migration`.

## Como executar

### Pré-requisitos

- JDK 21 ou superior
- Maven (ou use o wrapper `mvnw`)
- PostgreSQL rodando em `localhost:5432`

### 1. Clonar o repositório

```bash
git clone https://github.com/Joao-andrade2798/biblioteca.git
cd biblioteca
```

### 2. Criar o banco

```sql
CREATE DATABASE biblioteca;
```

Não é preciso criar tabelas: o Flyway aplica as migrations na primeira execução.

### 3. Configurar as credenciais

O usuário e a senha do banco são lidos de variáveis de ambiente (o usuário usa `postgres` como padrão).

Linux / macOS:

```bash
export DB_USER=postgres
export DB_PASSWORD=sua_senha
```

Windows (PowerShell):

```powershell
$env:DB_USER="postgres"
$env:DB_PASSWORD="sua_senha"
```

### 4. Subir a aplicação

```bash
./mvnw spring-boot:run
```

Ou, com o Maven instalado: `mvn spring-boot:run`.

A API sobe em `http://localhost:8181`.

## Documentação da API

Com a aplicação rodando:

- Swagger UI: http://localhost:8181/swagger-ui.html
- Especificação OpenAPI (JSON): http://localhost:8181/v3/api-docs

### Rotas principais

Todas as rotas têm o prefixo `/biblioteca`.

**Autor**

| Método | Rota | Descrição |
|---|---|---|
| POST | `/biblioteca/autor/cadastro` | Cadastra um autor |
| GET | `/biblioteca/autor/listagem` | Lista os autores |
| GET | `/biblioteca/autor/consulta` | Consulta um autor |
| DELETE | `/biblioteca/autor/exclusao` | Exclui um autor |

**Livro**

| Método | Rota | Descrição |
|---|---|---|
| POST | `/biblioteca/livro/cadastro` | Cadastra um livro |
| POST | `/biblioteca/livro/cadastro/exemplar` | Cadastra exemplares de um livro |
| GET | `/biblioteca/livro/listagem` | Lista os livros |
| GET | `/biblioteca/livro/consulta` | Consulta um livro |
| DELETE | `/biblioteca/livro/exclusao` | Exclui um livro |

**Usuário**

| Método | Rota | Descrição |
|---|---|---|
| POST | `/biblioteca/usuario/cadastro` | Cadastra um usuário |
| GET | `/biblioteca/usuario/listagem` | Lista os usuários |
| GET | `/biblioteca/usuario/consulta` | Consulta um usuário |
| DELETE | `/biblioteca/usuario/exclusao` | Exclui um usuário |

**Empréstimo**

| Método | Rota | Descrição |
|---|---|---|
| POST | `/biblioteca/emprestimo/registro` | Registra um empréstimo |
| PATCH | `/biblioteca/emprestimo/{id}/devolucao` | Registra a devolução |
| GET | `/biblioteca/emprestimo/listagem` | Lista os empréstimos |
| GET | `/biblioteca/emprestimo/consultaPorUsuario` | Lista os empréstimos de um usuário |

Os detalhes de cada rota (parâmetros, corpo e respostas) estão no Swagger UI.

## Decisões de projeto

- **Migrations com Flyway** e `ddl-auto=validate`: o Hibernate apenas confere o schema, quem altera o banco são os scripts versionados.
- **Código do exemplar gerado pelo banco**, por uma sequence do PostgreSQL, evitando código repetido em requisições simultâneas.
- **DTOs como `record`**, separando o que trafega na API das entidades JPA.
- **Relacionamentos `LAZY`** e `open-in-view` desativado, com o carregamento controlado dentro de transações nos services.
- **Exceções de domínio próprias** tratadas em um `@RestControllerAdvice`, em vez de reaproveitar exceções do JPA.

## Próximos passos

- [ ] Autenticação e perfis de acesso (Spring Security + JWT)
- [ ] Testes automatizados
- [ ] Frontend com Angular

## Autor
**João Andrade**

[LinkedIn](https://www.linkedin.com/in/jo%C3%A3o-andrade-488632366/) · [GitHub](https://github.com/Joao-andrade2798)
