Feature: Home page features
  As a user
  I want to perform an action
  So that I can achieve a business goal

  Scenario: Check ability change city location
    Given User clicks on the city button
    When User selects random city
    When User clicks accept city choice button
    Then User checks changed city location

  Scenario Outline: Check different methods (Web_Elements Lecture)
    Given User opens home page app
    When User checks that tag name equals '<tagName>'
    When User checks that attribute name 'arial-label' equals '<attributeName>'
    When User checks that css value 'font-size' equals '<pixels>'
    Then User checks correct site logo and product catalog elements location

    Examples:
      | tagName | attributeName   | pixels |
      | button  | Каталог товаров | 16px   |