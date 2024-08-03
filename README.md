# postech-fiap-lanchonete-cliente-ms

# Lanchonete Produto - Versão 1.0.0

Bem-vindo ao projeto **Lanchonete Produto**! Esta é a versão 1.0.0, e abaixo você encontrará todas as instruções necessárias para começar a utilizar Docker e Docker Compose para rodar a aplicação.

## Pré-Requisitos

Antes de prosseguir, assegure-se de que você tem o Docker e o Docker Compose instalados em seu sistema. Caso ainda não os tenha instalado, visite a [documentação oficial do Docker](https://docs.docker.com/get-docker/) para obter instruções de instalação.

## Estrutura do Projeto

O projeto tem a seguinte estrutura de diretórios:

```plaintext
lanchonete-produto/
│   docker-compose.yml
│   Dockerfile
│   ...
```

- `Dockerfile`: Arquivo com instruções para a criação da imagem Docker.
- `docker-compose.yml`: Arquivo de configuração para rodar a aplicação com o Docker Compose, definindo serviços, volumes, portas, entre outros.

## Construindo a Imagem Docker

Para construir a imagem Docker do projeto, abra um terminal, vá até a raiz do projeto e execute o seguinte comando:

`docker build -t lanchonete-produto:latest`

## Executando a Imagem Docker com `postgres:13.3`:

`docker-compose up`

# Guia de Execução do Projeto e Utilização das APIs

Antes de iniciar, certifique-se de ter o Docker instalado em sua máquina. A seguir, são apresentados os passos necessários para executar o projeto localmente.

## Passo 1: Inicializando o Ambiente

Abra o terminal na pasta do projeto e execute o seguinte comando:

```bash
docker-compose up
```

Este comando iniciará todas as imagens Docker necessárias para a execução do projeto.

## Passo 2: Cadastro de Usuário

Para efetuar um pedido e o checkout, é necessário cadastrar um usuário. Utilize a seguinte API para isso:

### Cadastrar ou Identificar Usuário

- **Método:** POST
- **URL:** `/lanchonete/api/v1/clientes`

Esta API cadastra um novo usuário na base de dados. Se o usuário já estiver cadastrado, a API o identificará.
