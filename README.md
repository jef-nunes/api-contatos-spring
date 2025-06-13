## Configurando o projeto

#### 1. Clonar o arquivo pom.xml / adicionar as dependências
* mysql-connector-java
* spring-boot-devtools
* spring-boot-starter-web
* spring-boot-starter-data-jpa
* lombok

#### 2. Criar / editar o arquivo src/main/resources/application.properties

Altera a estrutura da tabela caso a entidade tenha mudanças <br>
`spring.jpa.hibernate.ddl-auto=update`

Acesso ao banco de dados <br>
`spring.datasource.url=jdbc:mysql://${MYSQL_HOST:localhost}:3306/api_spring`

Usuário do banco de dados <br>
`spring.datasource.username=root`

Senha do banco de dados <br>
`spring.datasource.password=`
