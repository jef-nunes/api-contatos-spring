## Configurando o projeto
#### Necessário criar o arquivo src/main/resources/application.properties e adicionar as seguintes propriedades:

Altera a estrutura da tabela caso a entidade tenha mudanças <br>
`spring.jpa.hibernate.ddl-auto=update`

Acesso ao banco de dados <br>
`spring.datasource.url=jdbc:mysql://${MYSQL_HOST:localhost}:3306/api_spring`

Usuário do banco de dados <br>
`spring.datasource.username=root`

Senha do banco de dados <br>
`spring.datasource.password=`
