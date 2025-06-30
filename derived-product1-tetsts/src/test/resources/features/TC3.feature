Feature: Derived product1 flow
  @TC3
  Scenario: To verify derived product1 execution flow
    Given User lands on home page
    Then below tickets menu, count number of slides present
    Then get title of each slide
    And validate title with expected test data
    Then calculate duration for which each slide is playing
    And validate duration with expected duration "10" sec