Este documento contém instruções para executar a aplicação 
POC - Golden Raspberry Awards Apllication, que é uma API 
Spring Boot no padrão REST.

A princípio, API ocupará a porta 8089, porém este valor 
pode ser configurado alterando o valor do parâmetro server.port, 
no arquivo "../resources/application.properties".
Dessa forma o URI que representa o domínio da API 
será: "http://localhost:8088/".

Para obter o o produtor com maior intervalo entre dois prêmios 
consecutivos, e o que obteve dois prêmios mais rápido 
deve-se realizar uma chamada para o endpoint:
GET - http://localhost:8088/producers/winners

Além disso, foram implantados os seguintes endpoints:

============= Producers =================
POST - http://localhost:8088/producers/
BODY:
{
    "name":"Producer Name"
}

GET - http://localhost:8088/producers/

GET - http://localhost:8088/producers/{id}
RETURN:
{
    "id":{id}
    "name":"Producer Name"
}


PUT - http://localhost:8088/producers/{id}
BODY:
{
    "id":{id}
    "name":"New Producer Name"
}

DELETE - http://localhost:8088/producers/{id}

============= Studios =================
POST - http://localhost:8088/producers/
BODY:
{
    "name":"Studio Name"
}

GET - http://localhost:8088/studios/

GET - http://localhost:8088/studios/{id}
RETURN:
{
    "id":{id}
    "name":"Studio Name"
}


PUT - http://localhost:8088/studios/{id}
BODY:
{
    "id":{id}
    "name":"New Studio Name"
}

DELETE - http://localhost:8088/studios/{id}



============= Nominations =================

GET - http://localhost:8088/nominations/

GET - http://localhost:8088/nominations/{id}
RETURN:
{
    "id":{id}
    "name":"Studio Name"
}


DELETE - http://localhost:8088/nominations/{id}

[
    {
        "id": 1,
        "movieTitle": "Can't Stop the Music",
        "year": 1980,
        "winner": true,
        "studioPartners": [
            {
                "id": 1,
                "name": "Associated Film Distribution"
            }
        ],
        "producerPartners": [
            {
                "id": 1,
                "name": "Allan Carr"
            }
        ]
    },...
]

Os testes de integração estão presentes no arquivo

WinnerControllerTest

Os testes garantem que o retorno terá sempre os objeto min e max além de verificar se ambos estão ordenados corretamente.



