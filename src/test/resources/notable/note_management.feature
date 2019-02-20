Feature: Create a note
  As a student,
  In order to access my notes at a future date
  I would like to be able to create and save my notes

Scenario: Create note resource with HTTP POST
  When I make a "POST" request to "/notes"
  Then I should get an HTTP "200" status code
  And the response body should be:
  """
  {
    "content": "Hello World"
  }
  """
