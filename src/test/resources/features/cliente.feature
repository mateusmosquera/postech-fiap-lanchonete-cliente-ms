Feature: Gerenciamento de clientes via HTTP

   @tagPost
   Scenario Outline: : Criar um novo cliente
    Given que o cliente envia uma solicitação para criar um novo cliente, com cpf "<cpf>", com nome <nome> e email <email>
    When cliente recebe a solicitacao com code status <status>
    Then o cliente recebe a resposta, com cpf "<cpf>", com nome <nome> e email <email>


     Examples:
     | cpf               | nome             | email              | status |
     |  686.524.400-18   | Cliente Teste1   | cliente1@teste.com | 201    |
     |  123.456.789-09   | Cliente Teste    | cliente@teste.com  | 400    |

  @tagGet
  Scenario Outline: Recuperar informações de um cliente pelo CPF
    Given que o cliente envia uma solicitacao para recuperar informações de um cliente pelo CPF
    When  cliente recebe as informacoes com code status <status>
    Then recebe as informacoes com a resposta

    Examples:
      | status |
      | 200    |