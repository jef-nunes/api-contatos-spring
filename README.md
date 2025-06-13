## Configurando o projeto
#### Necessário criar o arquivo src/main/resources/application.properties e adicionar as propriedades:
+ Propriedades relacionadas ao banco de dados
Atualização de entidades -`spring.jpa.hibernate.ddl-auto=update`<br>
URL -`spring.datasource.url=jdbc:mysql://${MYSQL_HOST:localhost}:3306/contatos`<br>
Usuário - `spring.datasource.username=root`<br>
Senha - `spring.datasource.password=`<br>
