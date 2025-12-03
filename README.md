# StockControl Pro: Documentação do Projeto

**Alunos:** Anderson Santos, Lorenzo Gotuzzo  
**Curso:** Engenharia de Software

Este documento apresenta a solução técnica e a arquitetura do **StockControl Pro**, uma API RESTful para gerenciamento de estoque desenvolvida com Spring Boot, incluindo funcionalidades avançadas como segurança de senhas, perfis de usuário e deleção lógica (soft delete) com validações explícitas.

---

## 1. Visão Geral

### O Problema
O StockControl Pro resolve a ineficiência e a falta de rastreabilidade no gerenciamento de estoques. As principais dores são a falta de auditoria nas movimentações, a inconsistência no saldo de estoque, a desorganização para localizar produtos e a falta de um controle de acesso seguro e com feedback claro para os operadores do sistema.

### A Solução
A solução é uma API RESTful que centraliza o controle de estoque. Ela automatiza o cálculo de saldo em tempo real, digitaliza cada transação e garante a segurança com senhas criptografadas e perfis de acesso. A funcionalidade de "soft delete" permite inativar produtos e usuários sem perder o histórico, e o sistema agora fornece mensagens de erro claras para operações inválidas, como tentar inativar um recurso que já está inativo.

---

## 2. Como Executar o Projeto

Siga os passos abaixo para configurar e executar a aplicação em seu ambiente local.

### Pré-requisitos
*   **Java 17** ou superior
*   **Maven 3.8** ou superior
*   **PostgreSQL** instalado e em execução

### Passo 1: Configuração do Banco de Dados
1.  Certifique-se de que o PostgreSQL esteja rodando.
2.  Crie um banco de dados com o nome `management_db`.
3.  As credenciais de acesso estão no arquivo `src/main/resources/application.properties`. Ajuste se necessário.

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/management_db
spring.datasource.username=postgres
spring.datasource.password=123456
```

### Passo 2: Compilar e Executar a Aplicação
Você pode executar o projeto de duas maneiras:

#### A) Pelo Terminal (Recomendado)
1.  Abra um terminal na raiz do projeto.
2.  Execute o comando Maven para compilar o projeto:
    ```sh
    mvn clean install
    ```
3.  Após o `BUILD SUCCESS`, execute o arquivo `.jar` gerado:
    ```sh
    java -jar target/demo-0.0.1-SNAPSHOT.jar
    ```

#### B) Pela IDE (IntelliJ, Eclipse, etc.)
1.  Garanta que o projeto foi compilado com sucesso pelo Maven (`mvn clean install`).
2.  Recarregue o projeto na IDE para sincronizar as dependências.
3.  Localize a classe `DemoApplication.java` e execute-a.

A aplicação estará rodando em `http://localhost:8080`.

---

## 3. Documentação da API (Swagger UI)

A API do StockControl Pro é autodocumentada usando SpringDoc (Swagger).

### Como Acessar
1.  Com a aplicação em execução, acesse a URL:
    **[http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)**

### Como Utilizar
Na interface do Swagger, você pode explorar e testar todos os endpoints. O sistema agora fornece feedback claro para operações de deleção, como:
*   `400 Bad Request` com a mensagem `"Este usuário já está inativo."` se você tentar inativar o mesmo usuário duas vezes.
*   `400 Bad Request` com a mensagem `"Categoria possui produtos e não pode ser deletada!"` se você tentar deletar uma categoria que ainda tem produtos associados.

---

## 4. Arquitetura Detalhada e Funcionalidades Avançadas

### Segurança com Spring Security
*   **Criptografia de Senhas**: As senhas dos usuários são armazenadas no banco de dados de forma segura usando o `BCryptPasswordEncoder`.
*   **Perfis de Acesso**: A entidade `Usuario` possui um `Perfil` (`ADMIN` ou `OPERADOR`), permitindo a futura implementação de autorizações por tipo de usuário.

### Deleção Lógica (Soft Delete) com Validações
*   **Status do Produto e Usuário**: As entidades `Produto` e `Usuario` possuem um campo de `Status` (`ATIVO` ou `INATIVO`).
*   **Integridade do Histórico**: Ao invés de deletar um registro do banco (`DELETE`), o sistema agora altera seu status para `INATIVO`. Isso preserva a integridade do histórico de movimentações.
*   **Validações Explícitas**:
    *   O sistema impede a inativação de um recurso que já está inativo, retornando um erro `400`.
    *   O sistema impede a deleção de `Categorias` ou `Localizacoes` que possuem produtos associados, protegendo a integridade referencial.
*   **Busca Inteligente**: Os endpoints `GET /produtos` e `GET /usuarios` foram configurados para retornar apenas os registros `ATIVOS`.

### Estrutura de Pacotes
```
src/main/java/com/example/demo/
├── config/              // Configurações do Spring (ex: SecurityConfig)
├── domain/              // Entidades e Enums (Perfil, StatusProduto, StatusUsuario)
├── dtos/                // DTOs para request e response
├── repositories/        // Interfaces de acesso ao banco
├── services/            // Lógica de negócio (criptografia, soft delete, validações)
├── resources/           // Endpoints REST
├── mappers/             // Mapeamento entre DTOs e Entidades
└── DemoApplication.java
```

### As Camadas e Suas Responsabilidades
*   **Resource (Controller)**: Porta de entrada da API. Lida com HTTP, recebe DTOs e retorna `ResponseEntity`.
*   **Service**: Cérebro da aplicação. Contém a lógica de negócio, como criptografia de senhas e regras de validação.
*   **Repository**: Camada de acesso a dados.
*   **Entity**: Representa as tabelas do banco.
*   **DTO**: Contrato de dados com o mundo exterior.
*   **MapStruct**: Ferramenta para conversão automática entre DTOs e Entidades.
*   **Tratamento de Exceções**: `@ControllerAdvice` global para respostas de erro padronizadas.

### Dependências Essenciais (`pom.xml`)
*   `spring-boot-starter-security`: Para criptografia e futuras autorizações.
*   `spring-boot-starter-web`: Para criar a API REST.
*   `spring-boot-starter-data-jpa`: Para persistência de dados.
*   `postgresql`: Driver do banco de dados.
*   `org.mapstruct`: Para mapeamento de objetos.
*   `org.springdoc:springdoc-openapi-starter-webmvc-ui`: Para a documentação com Swagger.
