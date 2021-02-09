Feature: Sorting
  As a user
  I want to perform an action
  So that I can achieve a business goal

  Background: Pre-condition
    Given User opens home page
    Then Home page are displayed for user

  Scenario Outline: Check sort by ascending sorting
    When User opens notebook page
    When User selects in dropdown sorting type - '<SORTING_TYPE>'
    Then User checks that products sorted by ascending

    Examples:
      | SORTING_TYPE         |
      | От дешевых к дорогим |

  Scenario Outline: Check sort by descending sorting
    When User opens notebook page
    When User selects in dropdown sorting type - '<SORTING_TYPE>'
    Then User checks that products sorted by descending

    Examples:
      | SORTING_TYPE         |
      | От дорогих к дешевым |

  Scenario: Check sort by product firm
    When User opens notebook page
    When User selects product firm
    Then User checks products contains in title selected firm