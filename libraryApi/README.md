# 📚 Projeto CRUD de Livros com Spring Boot

Este é um projeto de estudo desenvolvido por mim, Lucas, com o objetivo de praticar a construção de uma API RESTful utilizando Spring Boot, JPA e boas práticas de arquitetura Java.

---

## 🚀 Tecnologias Utilizadas

- Java 17+
- Spring Boot
- Spring Data JPA
- Lombok
- PostgreSQL (ou outro banco relacional)
- Maven
- UUID como identificador primário

---

## 🧠 Funcionalidades

- ✅ Cadastrar um novo livro (`POST /livros`)
- ✅ Listar todos os livros (`GET /livros`)
- ✅ Buscar livro por nome (`GET /livros/buscar?nome=...`)
- ✅ Atualizar um livro (`PUT /livros`)
- ✅ Deletar livro por ID (`DELETE /livros/{id}`)

---

## 🗂️ Estrutura do Projeto

```bash
src/
 └── main/
     └── java/
         ├── controller/      # Camada REST (exposição da API)
         ├── service/         # Camada de regras de negócio
         ├── repository/      # Interface com o banco de dados
         └── model/           # Entidades JPA
