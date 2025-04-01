# API de Cálculo de Impostos 🏛️

Esta é uma API RESTful desenvolvida em **Java + Spring Boot**, que permite **cadastrar e calcular impostos** no Brasil.  
A API é segura, utilizando **JWT (JSON Web Token)** para autenticação e autorização.

---

## 🚀 **Tecnologias Utilizadas**
- **Java 17**
- **Spring Boot 3**
- **Spring Security + JWT**
- **Spring Data JPA + PostgreSQL**
- **JUnit 5 + Mockito**
- **Swagger (Springdoc OpenAPI)**
- **Maven**

---

## 📌 **Pré-requisitos**
Antes de iniciar, você precisa ter instalado:
- **JDK 17**
- **Maven**
- **PostgreSQL (ou outro banco compatível)**
- **Postman (opcional, para testes)**

---

## ⚙️ **Configuração do Banco de Dados**
A API utiliza **PostgreSQL** por padrão.  
Edite o arquivo **`application.properties`** para configurar o banco de dados:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/seubanco
spring.datasource.username=seuusuario
spring.datasource.password=suasenha
Caso utilize outro banco, altere a dependência no pom.xml.
 
🔧 Instalação e Execução
1. Clone o repositório
sh
Copiar
Editar
git clone https://github.com/seuusuario/api-impostos.git
cd api-impostos
2. Compile o projeto
sh
Copiar
Editar
mvn clean install
3. Execute a aplicação
sh
Copiar
Editar
mvn spring-boot:run
A API estará disponível em http://localhost:8080 🎉
 
🛠 Testando a API
🔹 1. Criar um usuário (POST /user/register)
Request Body:
json
Copiar
Editar
{
  "username": "admin",
  "password": "admin123",
  "role": "ADMIN"
}
Response (201 Created):
json
Copiar
Editar
{
  "id": 1,
  "username": "admin",
  "role": "ADMIN"
}
🔹 2. Fazer login (POST /user/login)
Request Body:
json
Copiar
Editar
{
  "username": "admin",
  "password": "admin123"
}
Response (200 OK):
json
Copiar
Editar
{
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."
}
Copie o token e use-o nos próximos endpoints.
🔹 3. Cadastrar um imposto (POST /tipos)
Headers:
css
Copiar
Editar
Authorization: Bearer {TOKEN_JWT}
Request Body:
json
Copiar
Editar
{
  "nome": "ICMS",
  "descricao": "Imposto sobre Circulação de Mercadorias e Serviços",
  "aliquota": 18.0
}
Response (201 Created):
json
Copiar
Editar
{
  "id": 1,
  "nome": "ICMS",
  "descricao": "Imposto sobre Circulação de Mercadorias e Serviços",
  "aliquota": 18.0
}
🔹 4. Calcular imposto (POST /tipos/calculo)
Headers:
css
Copiar
Editar
Authorization: Bearer {TOKEN_JWT}
Request Body:
json
Copiar
Editar
{
  "tipoImpostoId": 1,
  "valorBase": 1000.0
}
Response (200 OK):
json
Copiar
Editar
{
  "tipoImposto": "ICMS",
  "valorBase": 1000.0,
  "aliquota": 18.0,
  "valorImposto": 180.0
}
 
