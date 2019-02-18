#noinspection CucumberUndefinedStep
Feature: Transfer Fixed Amount
  The transfer menu contains several fixed amounts to speed up transactions for users.
  Scenario Outline: Transfer fixed amounts of funds from savings into checking account
    Given I have deposited <Checking Balance> in my Checking Account
    And I have deposited <Savings Balance> in my Savings Account
    When I enter <Amount> to tranfer from my Savings Account into my Checking Account
    Then the balance of the Checking Account should be <Remaining Checking>
    And the balance of the Savings Account should be <Remaining Savings>
    Scenarios:
      | Checking Balance | Savings Balance | Amount     | Remaining Checking | Remaining Savings |
      | $10              | $500            | $500       | $510               | $0                |
      | $210             | $500            | $500       | $710               | $0                |
      | $0               | $700            | $200       | $200               | $500              |