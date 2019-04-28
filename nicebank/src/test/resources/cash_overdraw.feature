@Ignore @MarginalCase
Feature: Prevent users from going overdrawn
  Scenario: User tries to withdraw more than their balance
    Given my account has been credited with $100.00
    When I withdraw $200.00
    Then nothing should be dispensed
    And I should be told that I have insufficient funds in my account