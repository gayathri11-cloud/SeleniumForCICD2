Feature: submitting order in Ecommerce Website

  Background:
    Given I landed on Ecommerce Page

  @Regression
  Scenario Outline: Positive testing of submitting order

    Given a user logged with <username> and <password>
    When I add <Product> to cart
    And I checkout <Product> and submit the order
    Then "THANKYOU FOR THE ORDER." message is displayed on confirmation page

    Examples:
      | username     | password | Product   |
      | sri@test.com | Sree@123 | ZARA COAT 3 |