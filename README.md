# Sistema Gerenciador de Artistas e Álbuns 

## Dados da Vaga

|Informação|Descrição|
|---|---|
|**Candidato:**|José Quintino da Silva Junior|
|**CPF:**|027.350.XXX-XX|
|**Nome do Projeto:**|josequintinodasilvajunior027350|
|**Vaga:**|Analista de Tecnologia da Informação – Engenharia da Computação|
|**Processo:**|SEPLAG - Governo do Estado de Mato Grosso|
|**Data de entrega:**|05/02/2026|

---

## Objetivo
Implementar uma solução para gerenciamento de artistas e álbuns, com autenticação JWT, upload de capas em MinIO, consultas paginadas e notificações em tempo real via WebSocket, conforme requisitos do [Edital](https://seletivo.seplag.mt.gov.br/ver-edital/397).

---

## Arquitetura

![alt text](docs/image-1.png)

|Informação|Descrição|
|---|---|
|**Back end:**|Java 21 + Spring Boot|
|**Banco de Dados:**|PostgreSQL|
|**Storage:**|MinIO (API S3) |
|**Orquestração:**|Docker Compose (API + BD + MinIO)|
|**Extras:**|WebSocket, Rate Limit, Health Checks, Testes unitários|
|**Observabilidade:**|Health Checks e Liveness/Readiness|

---

## API

|Dependências|
|---|
|Spring Boot DevTools|
|Spring Web|
|Spring Security|
|Spring Data JPA|
|Flyway Migration|
|PostgreSQL Driver|
|Validation|
|Java Mail Sender|
|Spring Boot Actuator|

---

## Gestão do Projeto

A gestão do projeto será feita na aba de [Projetos](https://github.com/users/repositoryjosequintino/projects/1/views/1).

|Data|Autor|Tarefa|Descrição|
|---|---|---|---|
|22/01/2026|[@josequintino](https://github.com/repositoryjosequintino)|SEPLAGMT22012026000730API|Criar projeto backend|
|21/01/2026|[@josequintino](https://github.com/repositoryjosequintino)|SEPLAGMT21012026235541DOC|Criar documentação do sistema|