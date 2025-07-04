
## 🚀 Projeto Java - API REST com Spring Boot, JWT e H2

Este projeto é uma API RESTful desenvolvida com **Spring Boot**, utilizando:
- Autenticação via **JWT**
- Banco de dados **H2**
- Validação de dados com **Bean Validation**
- Testes automatizados com **MockMvc**
- Estrutura limpa com **Camadas (Controller, Service, Repository)**

### ✅ Funcionalidades
- `POST /auth/login`: autenticação e geração de token JWT
- `GET /api/posts`: retorna todos os posts (protegido por JWT)
- `POST /api/posts`: cria um novo post (protegido por JWT)

### 📦 Tecnologias utilizadas
- Java 17+
- Spring Boot
- Spring Web
- Spring Security
- Spring Data JPA
- H2 Database
- JSON Web Token (jjwt)
- Maven

### ⚙️ Como executar o projeto

```bash
git clone https://github.com/Eboscatto/projeto-java-spring-boot
cd seu-repositório
mvn spring-boot:run
```

A aplicação estará disponível em `http://localhost:8080`

### 🔐 Autenticação

#### 1. Faça login
```http
POST /auth/login
Content-Type: application/json

{
  "username": "admin",
  "senha": "123"
}
```

Retorna:
```json
{ "token": "Bearer eyJhbGciOiJIUzI1NiJ9..." }
```

#### 2. Use o token nas rotas protegidas
Adicione o header:
```
Authorization: Bearer SEU_TOKEN_AQUI
```

### 🛠️ Configurações

As configurações do JWT estão no `application.properties`:

```properties
projeto-java.security.token.secret=${JWT_SECRET:chave-super-segura-de-32+}
```

## 🧪 Testes

Execute os testes com:

```bash
mvn test
```

Inclui testes para:
- Autenticação
- Validações de input
- Requisições REST com MockMvc

### 🧬 Estrutura de diretórios

```
src/
├── main/java/com/projetoJava/
│   ├── controller/
│   ├── service/
│   ├── model/
│   ├── config/
│   └── MainApplication.java
└── test/java/com/projetoJava/
```

### 🧠 Melhorias possíveis

- Integração com banco de dados PostgreSQL ou MySQL
- Implementar refresh tokens
- Adicionar Swagger para documentação da API
- Implementar controle de acesso por roles (admin, user, etc.)

---

### ✨ Contribuições

Fique à vontade para enviar PRs, sugerir melhorias ou reportar bugs!

---

### 📄 Licença

Este projeto está sob a licença MIT. Consulte o arquivo `LICENSE` para mais informações.

