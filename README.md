# Workshop MongoDB com Spring Boot

[![NPM](https://img.shields.io/npm/l/react)](https://github.com/SAINTPHARD/workshop-mongo-v2/blob/main/LICENSE) 

## Sobre o projeto

Este é um projeto de API RESTful desenvolvido com **Java** e **Spring Boot**, utilizando o banco de dados NoSQL **MongoDB**.

O objetivo foi explorar as capacidades do Spring Data MongoDB, construindo um sistema de Usuários e Posts (estilo rede social), com relacionamentos entre documentos e consultas complexas.

### Funcionalidades principais

- **CRUD de Usuários**: Criação, leitura, atualização e remoção de usuários.
- **Relacionamentos**: Associação entre usuários e seus posts (AuthorDTO).
- **Consultas Personalizadas**:
  - Busca de posts contendo um texto específico (ignorando maiúsculas/minúsculas).
  - **Busca Avançada**: Filtragem de posts por texto E intervalo de datas simultaneamente.
- **Tratamento de Erros**: Exception Handler global para retornar respostas JSON adequadas em caso de erro.

## Modelo Conceitual
O banco de dados é composto pelas coleções `user` e `post`.
- Um usuário possui uma lista de posts.
- Um post possui um autor (aninhado) e comentários.

## Tecnologias utilizadas

- Java 21
- Spring Boot 3
- MongoDB
- Maven
- Postman (para testes de API)

## Como executar o projeto

### Pré-requisitos
- Java 21 ou superior instalado.
- MongoDB instalado e rodando na porta padrão (27017) OU Docker.

### Passos
1. Clone o repositório:
```bash
git clone [https://github.com/SAINTPHARD/workshop-mongo-v2.git](https://github.com/SAINTPHARD/workshop-mongo-v2.git)