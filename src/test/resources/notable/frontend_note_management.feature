Feature: WebUI for Managing Notes
  As a student,
  In order to management my notes,
  I would like to have the ability to create, read, edit and remove my notes

  @frontend
  Scenario: Add new note
    Given I am on the home page
    When I enter the id of my note
    When I enter the content of my note
    And I submit the form
    Then I should be notified that note has been added successfully
    And my note should appear on the home page
