Feature: To validate successful submission message on Contact Page

  @RegressionTest
  Scenario Outline: To validate successful submission message on Contact Page
    Given Enter URL '<URL>'
    When I go from the home page to contact page
    Then I populate all mandatory fields '<Forename>' '<Email>' '<Message>'
    When I click submit button
    And Verify messages
    And I close the Browser


    Examples:
      | URL                                    | Forename | Email                       | Message               |
      | http://jupiter.cloud.planittesting.com | John     | John.smith@yopmail.com      | Hi Trial with 1st run |
      | http://jupiter.cloud.planittesting.com | Alex     | Alex.Jupiter@yopmail.com    | Hi Trial with 2nd run |
      | http://jupiter.cloud.planittesting.com | Sara     | Sara.Live09@yopmail.com     | Hi Trial with 3rd run |
      | http://jupiter.cloud.planittesting.com | Birdie   | Birdie.Mathews@yopmail.com  | Hi Trial with 4th run |
      | http://jupiter.cloud.planittesting.com | Living   | Living.stones24@yopmail.com | Hi Trial with 5th run |


  @RegressionTest
  Scenario Outline: To validate successful submission message on Contact Page
    Given Enter URL '<URL>'
    When I go from the home page to contact page
    When I click submit button
    And Verify messages
    Then I populate all mandatory fields '<Forename>' '<Email>' '<Message>'
    And Verify messages
    And I close the Browser

    Examples:
      | URL                                    | Forename | Email                  | Message               |
      | http://jupiter.cloud.planittesting.com | John     | John.smith@yopmail.com | Hi Trial with 1st run |