Feature: Action with cart
  As a user
  I want to perform an action
  So that I can achieve a business goal

  Background: Pre-condition
    Given User opens home page
    Then Home page is displayed for user

  Scenario: Check add product to cart
    When User inputs "xiaomi" into search field with action functionality
    When User adds into the cart product with "XIAOMI MiJia Automatic Foam Soap MJXSJ01XW/MJXSJ03XW" title
    Then Users checks that cart count is "1"

  Scenario: Check remove product from cart
    When User inputs "xiaomi" into search field with action functionality
    When User adds into the cart product with "XIAOMI MiJia Automatic Foam Soap MJXSJ01XW/MJXSJ03XW" title
    When User opens cart
    When User deletes product from cart
    Then User checks that cart is "Корзина пуста"

  Scenario: Check subtotal price of elements in cart
    When User inputs "xiaomi" into search field with action functionality
    When User closes ad popup if it's visible
    When User adds into the cart product with "XIAOMI MiJia Automatic Foam Soap MJXSJ01XW/MJXSJ03XW" title
    When User adds into the cart product with "IP-камера Xiaomi YI 1080p Home White YYS.2016" title
    When User adds into the cart product with "IP-камера Xiaomi YI Dome X 360° 1080P White YYS.3017" title
    When User opens cart
    Then User checks that products sum price equals subtotal price