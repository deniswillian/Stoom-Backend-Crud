# Stoom Backend - CRUD Rest 

[![N|Solid](https://user-images.strikinglycdn.com/res/hrscywv4p/image/upload/c_limit,fl_lossy,h_1440,w_720,f_auto,q_auto/232859/unge0id3r9s54lxzmiyo.png)](https://www.stoom.com.br/)

## Instalação

Primeiro clone o projeto para sua maquina usando o [git clone](https://git-scm.com/).

```bash
git clone https://github.com/deniswillian/stoom-backend-crud.git
```
Depois vamos usar o [docker-compose build](https://docs.docker.com/compose/reference/build/) para criar uma imagem com o ambiente da aplicação. Use o comando dentro da pasta do projeto.

```bash
docker-compose build
```

## Utilização

Para iniciar a aplicação vamos usar o [docker-compose up](https://docs.docker.com/compose/reference/up/).

```bash
docker-compose up
```


## API Reference

Dados de referência para chamadas na api

#### Address - Index `GET => http://localhost:8080/address`
##### Resposta `200`
```json
[
  {
    "id": 1,
    "streetName": "Rua Portugal",
    "number": "60403",
    "complement": null,
    "neighbourhood": "Jardim Lucélia",
    "city": "Sumaré",
    "state": "São Paulo",
    "country": "Brasil",
    "zipcode": "13173215",
    "latitude": -22.8013234,
    "longitude": -47.2792241
  }
]
```
#### Address - Create `POST => http://localhost:8080/address`
##### Conteúdo `JSON`
```json
{
  "streetName": "Rua Portugal",
  "number": "60403",
  "complement": null,
  "neighbourhood": "Jardim Lucélia",
  "city": "Sumaré",
  "state": "São Paulo",
  "country": "Brasil",
  "zipcode": "13173215"
}
```
##### Resposta `200`
```json
{
  "id": 1,
  "streetName": "Rua Portugal",
  "number": "60403",
  "complement": null,
  "neighbourhood": "Jardim Lucélia",
  "city": "Sumaré",
  "state": "São Paulo",
  "country": "Brasil",
  "zipcode": "13173215",
  "latitude": -22.8013234,
  "longitude": -47.27922419999999
}
```

#### Address - Get `GET => http://localhost:8080/address/{id}`
##### Resposta `200`
```json
{
  "id": 1,
  "streetName": "Rua Portugal",
  "number": "60403",
  "complement": null,
  "neighbourhood": "Jardim Lucélia",
  "city": "Sumaré",
  "state": "São Paulo",
  "country": "Brasil",
  "zipcode": "13173215",
  "latitude": -22.8013234,
  "longitude": -47.27922419999999
}
```
#### Address - Update `PUT => http://localhost:8080/address/{id}`
##### Conteúdo `JSON`
```json
{
  "streetName": "Rua Portugal",
  "number": "60403",
  "complement": null,
  "neighbourhood": "Jardim Lucélia",
  "city": "Sumaré",
  "state": "São Paulo",
  "country": "Brasil",
  "zipcode": "13173215"
}
```
##### Resposta `200`
```json
{
  "id": 1,
  "streetName": "Rua Portugal",
  "number": "60403",
  "complement": null,
  "neighbourhood": "Jardim Lucélia",
  "city": "Sumaré",
  "state": "São Paulo",
  "country": "Brasil",
  "zipcode": "13173215",
  "latitude": -22.8013234,
  "longitude": -47.27922419999999
}
```

#### Address - Delete `DEL => http://localhost:8080/address/{id}`
##### Resposta `200`
```txt
no body returned for response
```
## Considerações Finais

Estrutura do banco criada com base no teste de qualificação backend *STOOM*, em uma aplicação real o ideal seria abstrair melhor o banco separando as colunas em tabelas deixando o banco mais fofinho ;)

Ex:

1 Tabela de `country`
1 Tabela de `state` que tem uma fk com `country`
1 Tabela de `city` com fk de `state` e assim vai...

Obrigado pelo teste, espero que gostem !


## Licença
[![License](http://img.shields.io/:license-mit-blue.svg?style=flat-square)](http://badges.mit-license.org)
