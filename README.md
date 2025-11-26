# StockControl Pro: Documentação do Projeto

**Alunos:** Anderson Santos, Lorenzo Gotuzzo  
**Curso:** Engenharia de Software

Este documento apresenta a solução técnica e a arquitetura do **StockControl Pro**, uma API RESTful para gerenciamento de estoque desenvolvida com Spring Boot, incluindo funcionalidades avançadas como segurança de senhas, perfis de usuário e deleção lógica (soft delete) para produtos e usuários.

---

## 1. Visão Geral

### O Problema
O StockControl Pro resolve a ineficiência e a falta de rastreabilidade no gerenciamento de estoques. As principais dores são a falta de auditoria nas movimentações, a inconsistência no saldo de estoque, a desorganização para localizar produtos e a falta de um controle de acesso seguro para os operadores do sistema.

### A Solução
A solução é uma API RESTful que centraliza o controle de estoque. Ela automatiza o cálculo de saldo em tempo real, digitaliza cada transação e garante a segurança com senhas criptografadas e perfis de acesso (`ADMIN`, `OPERADOR`). A funcionalidade de "soft delete" permite inativar produtos e usuários sem perder o histórico de movimentações, garantindo a integridade dos dados.

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
Na interface do Swagger, você pode explorar e testar todos os endpoints. Ao criar um usuário, a senha enviada será automaticamente criptografada com BCrypt. Ao deletar um produto ou usuário, ele será apenas inativado no banco de dados.

---

## 4. Arquitetura Detalhada e Funcionalidades Avançadas

### Segurança com Spring Security
*   **Criptografia de Senhas**: As senhas dos usuários são armazenadas no banco de dados de forma segura usando o `BCryptPasswordEncoder`.
*   **Perfis de Acesso**: A entidade `Usuario` possui um `Perfil` (`ADMIN` ou `OPERADOR`), permitindo a futura implementação de autorizações por tipo de usuário.

### Deleção Lógica (Soft Delete)
*   **Status do Produto e Usuário**: As entidades `Produto` e `Usuario` possuem um campo de `Status` (`ATIVO` ou `INATIVO`).
*   **Integridade do Histórico**: Ao invés de deletar um registro do banco (`DELETE`), o sistema agora altera seu status para `INATIVO`. Isso preserva a integridade do histórico de movimentações.
*   **Busca Inteligente**: Os endpoints `GET /produtos` e `GET /usuarios` foram configurados para retornar apenas os registros `ATIVOS`.
*   **Regra de Negócio**: O sistema impede que um usuário `INATIVO` crie novas movimentações de estoque.

### Estrutura de Pacotes
```
src/main/java/com/example/demo/
├── config/              // Configurações do Spring (ex: SecurityConfig)
├── domain/              // Entidades e Enums (Perfil, StatusProduto, StatusUsuario)
├── dtos/                // DTOs para request e response
├── repositories/        // Interfaces de acesso ao banco
├── services/            // Lógica de negócio (criptografia, soft delete)
├── resources/           // Endpoints REST
├── mappers/             // Mapeamento entre DTOs e Entidades
└── DemoApplication.java
```

### As Camadas e Suas Responsabilidades
*   **Resource (Controller)**: Porta de entrada da API. Lida com HTTP, recebe DTOs, aciona validações e retorna `ResponseEntity`.
*   **Service**: Cérebro da aplicação. Contém a lógica de negócio, como criptografia de senhas e regras de soft delete.
*   **Repository**: Camada de acesso a dados, com métodos de busca customizados como `findByLogin` e `findByStatus`.
*   **Entity**: Representa as tabelas do banco, agora com campos de `status` e `perfil`.
*   **DTO**: Contrato de dados, com DTOs específicos para criação (`UsuarioCreateDTO`) para lidar com a senha.
*   **MapStruct**: Ferramenta para conversão automática entre DTOs e Entidades.
*   **Tratamento de Exceções**: `@ControllerAdvice` global para respostas de erro padronizadas.

### Dependências Essenciais (`pom.xml`)
*   `spring-boot-starter-security`: Para criptografia e futuras autorizações.
*   `spring-boot-starter-web`: Para criar a API REST.
*   `spring-boot-starter-data-jpa`: Para persistência de dados.
*   `postgresql`: Driver do banco de dados.
*   `org.mapstruct`: Para mapeamento de objetos.
*   `org.springdoc:springdoc-openapi-starter-webmvc-ui`: Para a documentação com Swagger.
