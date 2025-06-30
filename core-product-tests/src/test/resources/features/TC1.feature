Feature: core product flow1
  @TC1
  Scenario: To verify core product execution flow1
    Given User lands on core product home page
    Then Scroll to shop menu
    And Click on Men's option
    Then Find all jackets from all paginated pages
    Then Store each jacket price, title and top seller's message to text file
    And Attach text file to report