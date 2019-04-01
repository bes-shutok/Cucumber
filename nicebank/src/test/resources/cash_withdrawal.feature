Feature: Cash Withdrawal
  Scenario: Successful withdrawal from an account in credit
    Given I have deposited $100.00 in my account
    When I withdraw $20.00
    Then $20.00 should be dispensed
    And I should have left with $80.00 in my account