# Gerenciamento de Biblioteca

Sistema de gerenciamento de biblioteca escolar desenvolvido em **Spring Boot**, com API REST para controle de alunos, turmas, cursos, livros, estoque e empréstimos.

## Tecnologias

- **Java 17**
- **Spring Boot 3.5.14**
  - Spring Web
  - Spring Data JPA
  - Spring Security
  - Spring Validation
- **MySQL** (via `mysql-connector-j`)
- **Lombok**
- **Maven** (com Maven Wrapper)

## Funcionalidades

O sistema é organizado nos seguintes módulos:

| Módulo | Descrição |
|---|---|
| **Alunos** | Cadastro e gerenciamento de alunos, vinculados a uma turma |
| **Turmas** | Turmas vinculadas a um curso, campus e suas matérias |
| **Cursos** | Cadastro de cursos oferecidos |
| **Campus** | Cadastro das unidades/campi |
| **Matérias** | Cadastro de matérias, associadas a turmas |
| **Livros** | Catálogo de livros (título, disciplina, ano escolar, edição) |
| **Estoque** | Controle de quantidade de exemplares por livro (total, disponível, novo, conservado, mal conservado, inutilizado) |
| **Empréstimos** | Registro de empréstimo e devolução de livros para alunos, com controle de condição do livro e status |
| **Dashboard** | Estatísticas consolidadas do sistema |

## Modelo de dados (resumo)

- `Aluno` → pertence a uma `Turma`
- `Turma` → pertence a um `Curso` e um `Campus`, e possui várias `Materia`s
- `Livro` → possui um `Estoque` (1:1)
- `Emprestimo` → relaciona um `Aluno` e um `Livro`, com status (`StatusEmprestimo`) e condição do livro na entrega/devolução (`CondicaoLivro`)

## Pré-requisitos

- **JDK 17** ou superior instalado
- **MySQL Server** em execução (local ou remoto)
- Não é necessário ter o Maven instalado — o projeto já inclui o Maven Wrapper (`mvnw` / `mvnw.cmd`)

## Configuração

1. Crie um banco de dados no MySQL chamado `biblioteca`:
   ```sql
   CREATE DATABASE biblioteca;
   ```

2. Ajuste as credenciais de acesso ao banco em `src/main/resources/application.properties` conforme seu ambiente:
   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/biblioteca
   spring.datasource.username=root
   spring.datasource.password=sua_senha
   ```

   > As tabelas são criadas/atualizadas automaticamente na inicialização (`spring.jpa.hibernate.ddl-auto=update`).

   > ⚠️ Por padrão o repositório está configurado com credenciais de desenvolvimento (`root`/`0000`). Para ambientes compartilhados ou produção, prefira não versionar senhas reais — use variáveis de ambiente ou um arquivo `application-local.properties` ignorado pelo Git.

## Como executar

Clone o repositório e entre na pasta do projeto:

```bash
git clone https://github.com/Rebecafa02/GerenciamentoBiblioteca.git
cd GerenciamentoBiblioteca
```

Primeiro, baixe as dependências do projeto (equivalente ao `npm i`):

**Linux/macOS:**
```bash
./mvnw install
```

**Windows (PowerShell):**
```powershell
.\mvnw.cmd install
```

Depois, suba a aplicação:

**Linux/macOS:**
```bash
./mvnw spring-boot:run
```

**Windows (PowerShell):**
```powershell
.\mvnw.cmd spring-boot:run
```

A aplicação sobe por padrão em:

```
http://localhost:8080
```

### Outros comandos úteis do Maven Wrapper

```bash
./mvnw clean install     # baixa dependências, compila, testa e empacota
./mvnw test               # roda apenas os testes
./mvnw clean package      # gera o .jar em target/
```

## Endpoints da API

Todos os endpoints estão sob o prefixo `/api`.

### Alunos — `/api/alunos`
| Método | Rota | Descrição |
|---|---|---|
| POST | `/api/alunos` | Cria um aluno |
| GET | `/api/alunos` | Lista alunos |
| GET | `/api/alunos/{id}` | Busca aluno por ID |
| PATCH | `/api/alunos/{id}` | Atualização parcial |
| DELETE | `/api/alunos/{id}` | Remove aluno |

### Turmas — `/api/turma`
| Método | Rota | Descrição |
|---|---|---|
| POST | `/api/turma` | Cria turma |
| GET | `/api/turma` | Lista turmas |
| GET | `/api/turma/{id}` | Busca turma por ID |
| PATCH | `/api/turma/{id}` | Atualização parcial |
| DELETE | `/api/turma/{id}` | Remove turma |

### Cursos — `/api/curso`
| Método | Rota | Descrição |
|---|---|---|
| POST | `/api/curso` | Cria curso |
| GET | `/api/curso` | Lista cursos |
| GET | `/api/curso/{id}` | Busca curso por ID |
| PUT | `/api/curso/{id}` | Atualiza curso |
| DELETE | `/api/curso/{id}` | Remove curso |

### Campus — `/api/campus`
| Método | Rota | Descrição |
|---|---|---|
| POST | `/api/campus` | Cria campus |
| GET | `/api/campus` | Lista campi |
| GET | `/api/campus/{id}` | Busca campus por ID |
| PUT | `/api/campus/{id}` | Atualiza campus |
| DELETE | `/api/campus/{id}` | Remove campus |

### Matérias — `/api/materias`
| Método | Rota | Descrição |
|---|---|---|
| POST | `/api/materias` | Cria matéria |
| GET | `/api/materias` | Lista matérias |
| GET | `/api/materias/{id}` | Busca matéria por ID |
| PUT | `/api/materias/{id}` | Atualiza matéria |
| DELETE | `/api/materias/{id}` | Remove matéria |

### Livros — `/api/livros`
| Método | Rota | Descrição |
|---|---|---|
| POST | `/api/livros` | Cria livro |
| GET | `/api/livros` | Lista livros |
| GET | `/api/livros/{id}` | Busca livro por ID |
| PATCH | `/api/livros/{id}` | Atualização parcial |
| DELETE | `/api/livros/{id}` | Remove livro |

### Estoque — `/api/estoques`
| Método | Rota | Descrição |
|---|---|---|
| POST | `/api/estoques` | Cria registro de estoque |
| GET | `/api/estoques` | Lista estoques |
| GET | `/api/estoques/{id}` | Busca estoque por ID |
| PUT | `/api/estoques/{id}` | Atualiza estoque |
| PATCH | `/api/estoques/{id}` | Atualização parcial |
| DELETE | `/api/estoques/{id}` | Remove estoque |

### Empréstimos — `/api/emprestimos`
| Método | Rota | Descrição |
|---|---|---|
| POST | `/api/emprestimos` | Registra empréstimo |
| GET | `/api/emprestimos` | Lista empréstimos |
| GET | `/api/emprestimos/{id}` | Busca empréstimo por ID |
| PUT | `/api/emprestimos/{id}/devolver` | Registra devolução |
| DELETE | `/api/emprestimos/{id}` | Remove empréstimo |

### Dashboard — `/api/dashboard`
| Método | Rota | Descrição |
|---|---|---|
| GET | `/api/dashboard/stats` | Estatísticas consolidadas do sistema |

## Segurança

O projeto utiliza Spring Security, porém atualmente a configuração (`SecurityConfig`) libera todas as requisições (`permitAll`) e desabilita CSRF — adequado para desenvolvimento. Para produção, recomenda-se restringir o acesso conforme as regras de negócio.

## Tratamento de erros

O projeto possui um `GlobalExceptionHandler` que centraliza o tratamento de exceções (ex: `ResourceNotFoundException`), retornando respostas padronizadas para a API.

## Estrutura do projeto

```
src/main/java/com/biblioteca/gerenciamento/
├── config/          # Configurações (Security, exceções globais)
├── controller/       # Controllers REST
├── domain/
│   ├── dto/          # DTOs e Enums
│   └── entity/        # Entidades JPA
├── repository/        # Repositórios Spring Data JPA
└── service/           # Regras de negócio
```
