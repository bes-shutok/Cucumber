Feature: Cash Withdrawal
  Scenario Outline: Successful withdrawal from an account in credit
    Given my account has been credited with <Deposited Sum>
    When I withdraw <Taken Sum>
    Then <Taken Sum> should be dispensed
    And I should have left with <Remaining Sum> in my account
    Scenarios:
  | Deposited Sum | Taken Sum | Cash Sum     | Remaining Sum |
  | $100.00       | $20.00    | $20.00       | $80.00        |
  | $10.00        | $10.00    | $10          | $0.00         |
#  | $20.00        | $30.00    | $30          | -$10.00       |