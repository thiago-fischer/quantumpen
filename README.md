# QuantumPen

API REST para gerenciamento de estoque e vendas de canetas personalizadas, desenvolvida com Spring Boot.

## 📋 Sobre o Projeto

QuantumPen é um sistema completo de gerenciamento para uma empresa especializada em canetas personalizadas. O sistema oferece controle de estoque, gestão de clientes, produtos, funcionários e autenticação de usuários.

## 🚀 Tecnologias

- **Java** - Linguagem de programação
- **Spring Boot** - Framework principal
- **Spring Security** - Autenticação e autorização
- **Spring Data JPA** - Persistência de dados
- **SQLite** - Banco de dados
- **Maven** - Gerenciamento de dependências
- **OpenAPI/Swagger** - Documentação da API

## 📁 Estrutura do Projeto

```
quantumpen/
├── src/main/java/io/fischer/quantumpen/
│   ├── App.java                    # Classe principal
│   ├── auth/                       # Autenticação e autorização
│   │   ├── controller/
│   │   ├── dto/
│   │   ├── security/
│   │   └── service/
│   ├── clientes/                   # Gestão de clientes
│   │   ├── controller/
│   │   ├── dto/
│   │   ├── mapper/
│   │   ├── model/
│   │   ├── repository/
│   │   └── service/
│   ├── config/                     # Configurações
│   │   ├── OpenApiConfig.java
│   │   └── StartupConfig.java
│   ├── estoque/                    # Controle de estoque
│   ├── exception/                  # Tratamento de exceções
│   ├── funcionarios/               # Gestão de funcionários
│   ├── produtos/                   # Catálogo de produtos
│   ├── shared/                     # Componentes compartilhados
│   └── users/                      # Gerenciamento de usuários
└── src/main/resources/
    ├── application.properties
    └── docs/                       # Documentação adicional
        ├── descricao_institucional.md
        ├── paleta_cores.md
        ├── produtos.md
        ├── sobre_empresa.md
        └── assets/
```

## 🔧 Pré-requisitos

- Java 17 ou superior
- Maven 3.6+
- Git

## ⚙️ Instalação

1. **Clone o repositório**
```bash
git clone https://github.com/thiago-fischer/quantumpen.git
cd quantumpen
```

2. **Compile o projeto**
```bash
mvn clean install
```

3. **Execute a aplicação**
```bash
mvn spring-boot:run
```

Ou execute diretamente o JAR:
```bash
java -jar target/quantumpen-<version>.jar
```

## 🌐 Endpoints Principais

A aplicação estará disponível em `http://localhost:8080`

### Documentação da API

- **Swagger UI**: `http://localhost:8080/swagger-ui.html`
- **OpenAPI JSON**: `http://localhost:8080/v3/api-docs`

### Módulos

- `/api/auth` - Autenticação
- `/api/clientes` - Gestão de clientes
- `/api/produtos` - Catálogo de produtos
- `/api/estoque` - Controle de estoque
- `/api/funcionarios` - Gestão de funcionários
- `/api/users` - Gerenciamento de usuários

## 🔐 Segurança

O sistema utiliza Spring Security para autenticação e autorização. Configure as credenciais no arquivo `application.properties`.

## 🔑 Como autenticar na API (JWT)

A API utiliza **JWT (JSON Web Token)** para autenticação.

Para testar os endpoints protegidos no **Swagger**, siga os passos abaixo.

### 1️⃣ Realizar login

No Swagger, acesse o endpoint:

```
POST /api/v1/auth/login
```

Utilize as seguintes credenciais de teste:

```json
{
  "email": "admin@quantumpen.com.br",
  "senha": "admin"
}
```

Após executar a requisição, a API retornará um token JWT:

```json
{
  "token": "seu_token_jwt_aqui"
}
```

---

### 2️⃣ Autorizar no Swagger

1. Copie o **token** retornado pelo login
2. Clique no botão **Authorize** no topo do Swagger
3. Cole o token no campo:

```
Bearer SEU_TOKEN_AQUI
```

Exemplo:

```
Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...
```

4. Clique em **Authorize**
5. Agora você poderá acessar os endpoints protegidos da API.

---

### 3️⃣ Testar endpoints protegidos

Depois de autorizado, você poderá testar endpoints como:

```
/api/clientes
/api/produtos
/api/estoque
/api/funcionarios
```

---

💡 **Dica:**
Se preferir, você também pode testar a API utilizando ferramentas como:

* **Postman**
* **Insomnia**
* **curl**

Lembre-se sempre de enviar o header:

```
Authorization: Bearer SEU_TOKEN
```

## 📄 Configuração

Edite o arquivo `src/main/resources/application.properties` para configurar:

- Porta da aplicação
- Configurações do banco de dados
- Níveis de log
- Parâmetros de segurança

## 🗄️ Banco de Dados

O projeto utiliza SQLite como banco de dados. O arquivo `quantumpen.db` é criado automaticamente na raiz do projeto na primeira execução.

## 🧪 Testes

Execute os testes com:

```bash
mvn test
```

## 📦 Build

Para gerar o arquivo JAR executável:

```bash
mvn clean package
```

O arquivo será gerado em `target/quantumpen-<version>.jar`

## 👥 Autor

**Thiago Fischer**
- GitHub: [@thiago-fischer](https://github.com/thiago-fischer)


## 🤝 Contribuindo

1. Fork o projeto
2. Crie uma branch para sua feature (`git checkout -b feature/AmazingFeature`)
3. Commit suas mudanças (`git commit -m 'Add some AmazingFeature'`)
4. Push para a branch (`git push origin feature/AmazingFeature`)
5. Abra um Pull Request

## 📞 Suporte

Para suporte, abra uma issue no repositório ou entre em contato através do GitHub.