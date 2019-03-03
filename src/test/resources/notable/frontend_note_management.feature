Feature: WebUI for Managing Notes
  As a student,
  In order to management my notes,
  I would like to have the ability to create, read, edit and remove my notes

  Scenario: Add new note
    Given I am on the home page
    When I enter the id of my note
    When I enter the content of my note
    And I submit the form
    Then I should be notified that note has been added successfully
    And my note should appear on the home page

  Scenario Outline: Delete note
    Given I have added a note with "<id>" and "<content>"
    When I click on the delete button for "<id>"
    Then I should be notified that the note has been deleted
    And the note with "<content>" should no longer appear in the list of notes

    Examples:
      | id   | content                     |
      | 2000 | consectetur adipiscing elit |

  @frontend
  Scenario Outline: Edit note
    Given I have added a note with "<id>" and "<old_content>"
    When I click on the edit button for "<id>"
    And I provide "<new_content>"
    Then I should be notified that note has been edited successfully
    And I should see the note with the "<new_content>" in the list of notes
    And I should not see the note with the "<old_content>" in the list of notes

    Examples:
      | id | old_content             | new_content       |
      | 7  | Excepteur sint occaecat | Culpa qui officia |
