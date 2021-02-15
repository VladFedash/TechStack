Feature: Searching
  As a user
  I want to perform an action
  So that I can achieve a business goal

  Background: Pre-condition
    Given User opens home page
    Then Home page is displayed for user

  Scenario: Check show more feature
    When User inputs "phone" to search field and press enter
    When User scrolls down and click show more products button
    Then User checks that amount of elements increased by the specified amount

  Scenario: Check elements amount on search page
    When User inputs "phone" to search field and press enter
    When User clears search field and input another "iphone"
    Then User checks amount of products equals specified quantity

  Scenario Outline: Check search for no matches
    When User inputs '<SEARCH_NON_EXISTENT_KEYWORD>' to search field and press enter
    Then User checks actual massage equals '<EXPECTED_NO_MATCHES_MESSAGE>'

    Examples:
      | SEARCH_NON_EXISTENT_KEYWORD | EXPECTED_NO_MATCHES_MESSAGE                      |
      | non existent requestsfddgd  | По заданным параметрам не найдена ни одна модель |