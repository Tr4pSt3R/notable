Feature: Create a note
  As a student,
  In order to access my notes at a future date
  I would like to be able to create and save my notes

Scenario Outline: Create note resource with HTTP POST
  When I make a POST request
  Then I should get an HTTP success "<status_code>"
  And the response body should be:
    """
    {"content":"Hello World"}
    """

  Examples:
      | status_code |
      | 200         |
