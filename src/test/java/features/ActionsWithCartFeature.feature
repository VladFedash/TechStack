Feature: Action with cart
  As a user
  I want to perform an action
  So that I can achieve a business goal

  Background: Pre-condition
    Given User opens home page
    Then Home page are displayed for user

  Scenario Outline: Check add product to cart
    When User input '<SEARCH_WORD>' into search field with action functionality
    When  User adds into the cart product with '<PRODUCT_TITLE>' title
    When Users checks that cart count is '<EXPECTED_COUNT>'

    Examples:
      | SEARCH_WORD | PRODUCT_TITLE                                                         | EXPECTED_COUNT |
      | xiaomi      | Видеорегистратор Xiaomi Yi Smart Dash WiFi Gray International Edition | 1              |

  Scenario Outline: Check remove product from cart
    When User input '<SEARCH_WORD>' into search field with action functionality
    When  User adds into the cart product with '<PRODUCT_TITLE>' title
    When User opens cart
    When User deletes product from cart
    When User checks that cart is '<EMPTY>'

    Examples:
      | SEARCH_WORD | PRODUCT_TITLE                                                         | EMPTY         |
      | xiaomi      | Видеорегистратор Xiaomi Yi Smart Dash WiFi Gray International Edition | Корзина пуста |

  Scenario Outline: Check subtotal price of elements in cart
    When User input '<SEARCH_WORD>' into search field with action functionality
    When User closes ad popup if it's visible
    When  User adds into the cart product with '<FIRST_PRODUCT_TITLE>' title
    When  User adds into the cart product with '<SECOND_PRODUCT_TITLE>' title
    When  User adds into the cart product with '<THIRD_PRODUCT_TITLE>' title
    When User opens cart
    When User checks that products sum price equals subtotal price

    Examples:
      | SEARCH_WORD | FIRST_PRODUCT_TITLE                                                   | SECOND_PRODUCT_TITLE                          | THIRD_PRODUCT_TITLE                                  |
      | xiaomi      | Видеорегистратор Xiaomi Yi Smart Dash WiFi Gray International Edition | IP-камера Xiaomi YI 1080p Home White YYS.2016 | IP-камера Xiaomi YI Dome X 360° 1080P White YYS.3017 |