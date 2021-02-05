Feature: Sorting
  As a user
  I want to perform an action
  So that I can achieve a business goal

  Scenario Outline: Check sort by selected sorting
    Given User opens notebook page
    When User selects in dropdown sorting type - '<SORTING_TYPE>'
    When User waits when products are sorting by '<SORTING_TYPE>'
    Then User checks that products sorted by selected sorting

    Examples:
      | SORTING_TYPE         |
      | От дешевых к дорогим |
      | От дорогих к дешевым |

  Scenario: Check sort by product firm
    Given User opens notebook page
    When User selects product firm
    Then User checks products contains in title selected firm