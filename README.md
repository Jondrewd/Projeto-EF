# API de Licenciamento da Prefeitura

Esta é uma API desenvolvida em Java para permitir que os cidadãos solicitem e acompanhem o status de licenças emitidas pela prefeitura. Os usuários podem enviar documentos necessários para obter a licença desejada, e a API gerencia todo o processo desde a criação de um pedido até a emissão da licença.

## Funcionalidades

- Solicitação de licenças
- Upload de documentos relacionados ao pedido de licenças
- Consulta de status dos pedidos
- Emissão de licenças após aprovação
- Gestão de usuários e permissões

## Tecnologias Utilizadas

- **Java 11**: Linguagem principal do projeto
- **Spring Boot**: Framework para a construção da API REST
- **JPA/Hibernate**: Mapeamento objeto-relacional e gerenciamento de banco de dados
- **H2 Database**: Banco de dados em memória para testes
- **Maven**: Gerenciador de dependências
- **JWT (JSON Web Tokens)**: Para autenticação e autorização de usuários

## Instalação

1. Clone o repositório:
   `git clone https://github.com/seu-usuario/seu-repositorio.git`

2. Navegue até o diretório do projeto:
   `cd seu-repositorio`

3. Instale as dependências:
   `mvn clean install`

4. Execute a aplicação:
   `mvn spring-boot:run`

## Endpoints Principais

### Usuários
- **POST** `/register`: Cadastro de novos usuários
- **POST** `/login`: Autenticação de usuários

### Pedidos de Licença
- **GET** `/orders`: Listar todos os pedidos de licença
- **POST** `/orders`: Criar um novo pedido de licença
- **GET** `/orders/{id}`: Consultar detalhes de um pedido específico

### Documentos
- **POST** `/documents/upload`: Fazer upload de documentos
- **GET** `/documents/{id}/download`: Fazer download de um documento

### Licenças
- **GET** `/licenses`: Listar todas as licenças emitidas
- **GET** `/licenses/{id}`: Consultar uma licença específica

## Banco de Dados

O projeto utiliza um banco de dados **H2** em memória para testes. Ao iniciar a aplicação, o banco é inicializado automaticamente com alguns dados de exemplo.
