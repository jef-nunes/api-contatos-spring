## Configurando o projeto
#### Necessário criar o arquivo src/main/resources/application.properties e adicionar as propriedades:
`spring.jpa.hibernate.ddl-auto=update`<br>
`spring.datasource.url=jdbc:mysql://${MYSQL_HOST:localhost}:3306/api_spring`<br>
`spring.datasource.username=root`<br>
`spring.datasource.password=`<br>
