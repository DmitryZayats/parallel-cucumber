Feature: Selenium tests
  Sample selenium tests

  Scenario Outline: Finding some cheese
  Given I am on the Google search page
  When I search for "<query>"
  Then the page title should start with "<title>"

    Examples:
      | query          | title |
      | cheese         | cheese |
      | tornado         | tornado |
      | racecar         | racecar |
      | airplane         | airplane |
      | school         | school |
