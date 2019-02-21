Feature: Create a note
  As a student,
  In order to access my notes at a future date
  I would like to be able to create and save my notes


Scenario: Create note resource with HTTP POST
  When I make a POST request
  Then I should get an HTTP success


Scenario: Read single resource with HTTP GET request
  Given I have a note with id "1" and with content "Quick brown fox"
  When I make a GET request to "/note/1"
  Then I should get an HTTP success
  And the response body should be:
    """
    {"id":"1","content":"Quick brown fox"}
    """

  Scenario: Update note
    Given I have a note with id "1" and with content "Quick brown fox"
    When I change the content of the note with id "1" to "Quick brown dog"
    Then I should get an HTTP success
    And the response body should be:
      """
      {"id":"1","content":"Quick brown dog"}
      """
