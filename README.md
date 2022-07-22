# Officersoft-test

### Prompt de Comando(cmd)

Necessita de [Maven](https://maven.apache.org/install.html) instalado para usar o comando `mvn clean install`.

Necessita de [Java 11](https://jdk.java.net/archive/) nas variavéis de ambiente do sistema.

Abra a pasta do projeto no cmd  e execute o comando `mvn clean install` em seguida execute `mvn spring-boot:run`

### Banco de dados

Necessita ter uma base no postgres, eu usei um container do docker para isso.

`docker run --name officersofttest -p 5432:5432 -e POSTGRES_PASSWORD=123456789 -d postgres`

usuario e senha do banco de dados:

usuario: postgres

senha: 123456789

### Login

Para fazer o primeiro login, você vai precisar adicionar manualmente um usuário no banco de dados, por exemplo:

`http://localhost:8080/login`

O usuario e a senha devem ser passados no corpo da requisição.

usuario: `admin`

senha: `$2a$10$Pa8G5Rjy6s2Dp2TSmdCGeuZEQ3Ro3ZARhNtl8CSqSRsRS8e0L0wSu`

A senha deve estar criptografada no banco de dados no formato Bcrypt Hash o exemplo acima é de uma senha  = `admin`