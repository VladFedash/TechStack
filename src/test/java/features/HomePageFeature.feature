Feature: Home page features
  As a user
  I want to perform an action
  So that I can achieve a business goal

    Background: Pre-condition
    Given User opens home page
    Then Home page are displayed for user

  Scenario: Check ability change city location
    When User clicks on the city button
    When User selects random city
    When User clicks accept city choice button
    When User checks changed city location

  Scenario Outline: Check different methods (Web_Elements Lecture)
    When User checks that tag name equals '<tagName>'
    When User checks that attribute name 'arial-label' equals '<attributeName>'
    When User checks that css value 'font-size' equals '<pixels>'
    When User checks correct site logo and product catalog elements location

    Examples:
      | tagName | attributeName   | pixels |
      | button  | Каталог товаров | 16px   |