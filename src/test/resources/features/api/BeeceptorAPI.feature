Feature: Verify GET and POST API calls from Beeceptor application

  Scenario Outline: Get API and verify response
    Given user hit the GET API <path>
    Then user validates the <path>, <ip> in response
    And user validates all headers in the response
    Examples:
      | path                               | ip       |
      | "/sample-request?author=beeceptor" | "49.207" |

  Scenario Outline: POST API and verify response
    Given user hit the POST API <path>
    And user validates customer information in the response
      | name       | email                 | phone          |
      | Jane Smith | janesmith@example.com | 1-987-654-3210 |
    And user validates payment information in the response
      | method      | transaction_id | amount | currency |
      | credit_card | txn_67890      | 111.97 | USD      |
    And user validates product information in the response
      | product_id | name                | quantity | price |
      | A101       | Wireless Headphones | 1        | 79.99 |
      | B202       | Smartphone Case     | 2        | 15.99 |
    Examples:
      | path                               |
      | "/sample-request?author=beeceptor" |
