Feature: UI-Test

  Background:

    Given Application is launched using "URL"

@smoke
  Scenario: 1 When user adds a new currency

    When Admin User sends a Logon message to UI
    When User adds a new currency with
      | Name | Symbol | Code | Rate | Active |
      | KWD  | KWD    | KWD  | 3    | Yes    |
    Then Validate the currency details with
      | Name | Symbol | Code | Rate | Active |
      | KWD  | KWD    | KWD  | 3    | Yes    |
    Then delete the added currency


  Scenario: 2 When user adds a new currency

    When Admin User sends a Logon message to UI
    When User adds a new currency with
      | Name | Symbol | Code | Rate | Active |
      | KWD  | KWD    | KWD  | 3    | Yes    |
    Then Validate the currency details with
      | Name | Symbol | Code | Rate | Active |
      | KWD  | KWD    | KWD  | 3    | Yes    |
    Then delete the added currency










