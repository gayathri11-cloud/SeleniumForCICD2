Feature: validate error login in Ecommerce Website

  Background:
    Given I landed on Ecommerce Page

  @ErrorValidation
  Scenario Outline: validate error login in Ecommerce Website

    Given a user logged with <username> and <password>
    Then "Incorrect email or password." message is displayed on landing page
    Then CLose the browser

    Examples:
      | username     | password |
      | sri@test.com | Sre@123 |