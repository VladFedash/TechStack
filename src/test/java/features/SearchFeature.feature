Feature: Searching
  As a user
  I want to perform an action
  So that I can achieve a business goal

  Scenario Outline: Check show more feature
    Given User inputs '<SEARCH_WORD_PHONE>' to search field and press enter
    When  User scrolls down and click show more products button
    Then User checks that amount of elements increased by the specified amount

    Examples:
      | SEARCH_WORD_PHONE |
      | phone             |

  Scenario Outline: Check elements amount on search page
    Given User inputs '<SEARCH_WORD_PHONE>' to search field and press enter
    When User clears search field and input another '<SEARCH_WORD_IPHONE>'
    Then User checks amount of products equals specified quantity

    Examples:
      | SEARCH_WORD_PHONE | SEARCH_WORD_IPHONE |
      | phone             | iphone             |

  Scenario Outline: Check search for no matches
    Given User inputs '<SEARCH_NON_EXISTENT_KEYWORD>' to search field and press enter
    Then User checks actual massage equals '<EXPECTED_NO_MATCHES_MESSAGE>'

    Examples:
      | SEARCH_NON_EXISTENT_KEYWORD | EXPECTED_NO_MATCHES_MESSAGE                      |
      | non existent requestsfddgd  | По заданным параметрам не найдена ни одна модель |