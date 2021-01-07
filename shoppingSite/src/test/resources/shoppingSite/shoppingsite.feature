Feature: Website Shopping System
  As a user
  I want to be able to find items
  So that I can then make a purchase

  Scenario: Find a specific item
    Given I am on the shopping site
    When search for an item
    Then the item is displayed

  Scenario: Purchase an item
    Given I am on the shopping site
    And I add an item to the basket
    When I complete the registration process
    And I complete the order process
    Then an order is placed