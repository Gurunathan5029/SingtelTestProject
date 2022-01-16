@ToDo @web @AddToDoList
Feature: Web tests for Vue ToDo List

  @add
  Scenario Outline: Verify user ia able to add different text types of inputs for ToDo List
    Given ToDo url is launched
    When user enter ToDo text box with string having <TextTypes> characters
    Then the ENTERED ToDo item should be listed under ToDo ALL list
    And complete and remove all the added ToDo items

    Examples:
      | TextTypes     |
      | ALPHA_NUMERIC |
      | SPECIAL       |
      | UNICODE       |
      | BLANK         |
      | LENGTHY       |

  @edit
  Scenario: Verify user is able to edit the added ToDo List item
    Given ToDo url is launched
    When user enter ToDo text box with string having ALPHA_NUMERIC characters
    And the ENTERED ToDo item should be listed under ToDo ALL list
    And user edit the added ToDo item with UNICODE characters string
    Then the EDITED ToDo item should be listed under ToDo ALL list
    And the old ToDo item should not be listed
    And complete and remove all the added ToDo items

  @complete
  Scenario: Verify user is able to complete the added ToDo item
    Given ToDo url is launched
    When user enter ToDo text box with string having ALPHA_NUMERIC characters
    And the ENTERED ToDo item should be listed under ToDo ALL list
    And user complete the added ToDo item
    Then the ENTERED ToDo item should be listed under ToDo COMPLETED list
    And remove all the completed ToDo items

  @list
  Scenario: Verify user is able to see valid items in selected list Type
    Given ToDo url is launched
    When user enter multiple ToDo text box with string having following characters
      | ALPHA_NUMERIC |
      | SPECIAL       |
    And user complete the all added ToDo items
    Then the ENTERED multiple ToDo items should be listed under ToDo COMPLETED list
    And user incomplete the all added ToDo items
    And the ENTERED multiple ToDo items should be listed under ToDo ACTIVE list
    And the ENTERED multiple ToDo items should be listed under ToDo ALL list

  @remove
  Scenario: Verify user is able to remove added ToDo item without completing it
    Given ToDo url is launched
    When user enter ToDo text box with string having ALPHA_NUMERIC characters
    And  the ENTERED ToDo item should be listed under ToDo ALL list
    And user delete the added ToDo item
    Then the DELETED ToDo item should be listed under ToDo ALL list

  @count
  Scenario: Verify Items left count Of ToDo List Items
    Given ToDo url is launched
    When user enter multiple ToDo text box with string having following characters
      | ALPHA_NUMERIC |
      | SPECIAL       |
    And user complete the all added ToDo items
    And user enter multiple ToDo text box with string having following characters
      | LENGTHY |
      | UNICODE |
    Then the items left count should reflect only non completed count