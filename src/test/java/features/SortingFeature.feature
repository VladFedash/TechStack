Feature: Sorting
  As a user
  I want to perform an action
  So that I can achieve a business goal

  Background: Pre-condition
    Given User opens home page
    Then Home page is displayed for user

  Scenario: Check sort by ascending sorting
    When User opens notebook page
    When User selects in dropdown sorting type - "От дешевых к дорогим"
    Then User checks that products sorted by ascending

  Scenario: Check sort by descending sorting
    When User opens notebook page
    When User selects in dropdown sorting type - "От дорогих к дешевым"
    Then User checks that products sorted by descending

  Scenario: Check sort by product firm
    When User opens notebook page
    When User selects product firm
    Then User checks products contains in title selected firm