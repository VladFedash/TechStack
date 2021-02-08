Feature: Sorting
  As a user
  I want to perform an action
  So that I can achieve a business goal

  Background: Pre-condition
    Given User opens home page
    Then Home page are displayed for user

  Scenario Outline: Check sort by selected sorting
    When User opens notebook page
    When User selects in dropdown sorting type - '<SORTING_TYPE>'
    When User waits when products are sorting by '<SORTING_TYPE>'
    When User checks that products sorted by selected sorting

    Examples:
      | SORTING_TYPE         |
      | От дешевых к дорогим |
      | От дорогих к дешевым |

  Scenario: Check sort by product firm
    When User opens notebook page
    When User selects product firm
    When User checks products contains in title selected firm