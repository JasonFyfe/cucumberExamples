#@demosite
Feature: Website Login System
  As a user
  I want to register on the website
  So that I can then login

  Scenario: Register a new user
    Given the correct web address
    When I register an account
    Then a new account is created

  Scenario: Login an existing user
    Given the correct web address
    When I attempt to login
    Then I am logged into my account