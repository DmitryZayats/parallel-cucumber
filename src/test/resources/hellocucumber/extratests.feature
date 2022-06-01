Feature: Extra Selenium tests
  Sample selenium tests

  Scenario Outline: Finding some tornado
  Given I am on the Google search page
  When I search for "<query>"
  Then the page title should start with "<title>"

    Examples:
      | query          | title |
      | airplane         | airplane |
      | school         | school |
      | cheese         | cheese |
      | tornado         | tornado |
      | racecar         | racecar |
