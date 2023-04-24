Effecti - Comprasnet
=======================================

Este projeto é uma aplicação Spring Boot que realiza web scraping no site ComprasNet (<http://comprasnet.gov.br/ConsultaLicitacoes/ConsLicitacaoDia.asp>) e expõe uma API REST para listar oportunidades e marcar oportunidades como lida/não lida.

Pré-requisitos
--------------

-   Java 8 ou superior
-   Maven 3.6.0 ou superior

Dependências
------------

-   Spring Boot 2.5.5
-   Jsoup 1.14.3

Como executar
-------------

1.  Clone este repositório:

bashCopy code

`git clone https://github.com/jocimar-dev/Effecti-Encontrar.git`

1.  Navegue até a pasta do projeto:

bashCopy code

`cd EtapaEncontrar`

1.  Execute o projeto usando o Maven:

arduinoCopy code

`./mvnw spring-boot:run`

O aplicativo será iniciado na porta 8080.

Utilizando a API
----------------

A API possui os seguintes endpoints:

-   `GET /comprasnet`: Retorna a lista de oportunidades obtidas do site ComprasNet.
-   `PUT /comprasnet/{id}`: Marca a oportunidade com o ID especificado como lida ou não lida. Envie o valor booleano `true` ou `false` no corpo da requisição.

Exemplo de uso com o cURL:

1.  Listar oportunidades:

bashCopy code

`curl -X GET http://localhost:8080/comprasnet`

1.  Marcar uma oportunidade como lida:

bashCopy code

`curl -X PUT -H "Content-Type: application/json" -d "true" http://localhost:8080/comprasnet/{id}`

Estrutura do projeto
--------------------

-   `src/main/java/com/effecti/etapaencontrar`: Contém o código-fonte do projeto.
    -   `Application.java`: Classe principal do aplicativo Spring Boot.
    -   `ComprasNetDataService.java`: Serviço que realiza o web scraping no site ComprasNet.
    -   `ComprasNetService.java`: Classe que representa uma oportunidade.
    -   `ComprasNetServiceController.java`: Controlador REST que expõe a API para listar e marcar oportunidades como lida/não lida.

Licença
-------

Este projeto é distribuído sob a licença MIT. Veja o arquivo [LICENSE](https://chat.openai.com/c/LICENSE) para mais detalhes.