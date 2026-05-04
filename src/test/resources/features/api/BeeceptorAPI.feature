Feature: Verify GET and POST API calls from Beeceptor application

  Scenario Outline: Get API and verify response
    Given user hit the GET API <path>
    Then user validates the <path>, <ip> in response
    And user validates all headers in the response
      | Host                    | Sec-Fetch-Dest | Via   |
      | echo.free.beeceptor.com | document       | Caddy |
    Examples:
      | path                               | ip                |
      | "/sample-request?author=beeceptor" | "49.207.203.154:" |

  Scenario Outline: POST API and verify response
    Given user hit the POST API <path>
    And user validates customer information in the response
    And user validates payment information in the response
    And user validates product information in the response
    Examples:
      | path                               | ip                |
      | "/sample-request?author=beeceptor" | "49.207.203.154:" |
