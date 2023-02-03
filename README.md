# attornatus-pessoa-api

# API desenvolvida para processo seletivo na Attornatus Procuradoria Digital.

![Print da API rodando com Swagger](https://github.com/Felipe-Noguez/only-assets/blob/main/attornatus-api/Swagger1.png?raw=true)

* Este projeto consiste em desenvolver uma API para cadastro de pessoas com endereços, é possível cadastrar, editar, consultar e listar pessoas (por atributos), criar e listar endereços das pessoas.

* Com Swagger foi realizada a documentação de todos endpoints
* Cobertura de 100% de testes do código das services (PessoaService e EnderecoService)

## Tecnologias utilizadas no desenvolvimento
* Java JDK 17.0.4
* Spring boot
* Maven 4.0.0
* Swagger 1.6.8
* Banco de dados H2
* Mockito/Junit
* Lombok

## Ferramenta utilizada no desenvolvimento
* Intellij IDEA

## Para executar
* Clonar o repositório: attornatus-pessoa-api
* Configurar login e senha do banco de dados, diretamente no arquivo de configuração application.properties ou através da IDE, conforme print abaixo:

![Print da configuração de login e senha do bando H2](https://github.com/Felipe-Noguez/only-assets/blob/main/attornatus-api/IDE1.png?raw=true)

* Executar a aplicação através da classe main
* Acessar o banco no navegador utilizando a url: http://localhost:8080/h2
* Inserir JDBC url, user name e password conforme configuração do arquivo application.properties, segue imagem abaixo para referência:
![Print de referência para configuração do acesso ao H2](https://github.com/Felipe-Noguez/only-assets/blob/main/attornatus-api/H2.png?raw=true)
* Acessar o Swagger no navegador utilizando a url: http://localhost:8080/swagger-ui/index.html#/
* No Swagger já existem exemplos para realizar cadastros, alterações e listar
