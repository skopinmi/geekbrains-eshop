Feature: Products

  Scenario Outline: Successful add and delete Product
    Given Login "<username>" and "<password>"
    And click management of product
    And click products list
    And click create new
    And put in form "<name>" and "<price>" and "<brand>" and "<category>"
    And delete product

    Examples:
      | username | password | name | price | brand | category |
      | admin | admin | newProduct | 1234  | NIKE| SPORTSWEAR |