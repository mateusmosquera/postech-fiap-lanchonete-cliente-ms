Feature: Gerenciamento de clientes via HTTP

   @tagPost
   Scenario Outline: : Criar um novo cliente
    Given que o cliente envia uma solicitação para criar um novo cliente
    When cliente recebe a solicitacao com code status <status>
    Then o cliente recebe a resposta


     Examples:
       | status |
       | 201    |

  @tagGet
  Scenario Outline: Recuperar informações de um cliente pelo CPF
    Given que o cliente envia uma solicitacao para recuperar informações de um cliente pelo CPF
    When  cliente recebe as informacoes com code status <status>
    Then recebe as informacoes com a resposta

    Examples:
      | status |
      | 200    |