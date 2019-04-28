@SmokeTests
Feature: Cash Withdrawal
  Scenario Outline: Successful withdrawal from an account in credit
    Given my account has been credited with <Deposited Sum>
    When I withdraw <Taken Sum>
    Then <Taken Sum> should be dispensed
    And the balance of my account should be <Remaining Sum>
    Scenarios:
  | Deposited Sum | Taken Sum |Remaining Sum |
  | $100.00       | $20.00    |$80.00        |
  | $10.00        | $10.00    |$0.00         |
  | $50.00        | $30.00    |$20.00         |