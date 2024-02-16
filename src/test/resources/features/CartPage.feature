Feature: Cart page and check price

  @RegressionTest
  Scenario Outline: Cart page and check price
    Given Enter URL '<URL>'
    When land on main page and click start shopping
    Then Buy 2 Stuffed Frog, 5 Fluffy Bunny, 3 Valentine Bear
    When Verify the price for each product
    Then Verify the subtotal for each product is correct
    And Verify that total = sum(sub totals)

    Examples:
      | URL                                    |
      | http://jupiter.cloud.planittesting.com |