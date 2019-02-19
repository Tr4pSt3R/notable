Feature: Create a note
  As a student,
  In order to access my notes at a future date
  I would like to be able to create and save my notes

Scenario: Create note
  Given I am on the home page
  When I enter my note
  And I save it
  Then note should be saved successfully