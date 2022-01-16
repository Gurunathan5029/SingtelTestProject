package glue;

import Constants.Enums;
import Constants.Enums.InputTextType;
import Steps.ToDoPageSteps;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Steps;

import java.util.List;
import java.util.stream.Collectors;

public class ToDoStepDefinition {
    @Steps
    ToDoPageSteps toDoSteps;

    @Given("ToDo url is launched")
    public void to_do_url_is_launched() {
        toDoSteps.launchToDoUrl();
    }

    @When("user enter ToDo text box with string having (.+) characters$")
    public void user_enter_to_do_text_box_with_string_having_alpha_numeric_characters(InputTextType inputType) {
        toDoSteps.enterToDoList(inputType);
    }

    @When("user enter multiple ToDo text box with string having following characters$")
    public void user_enter_to_do_text_box_with_string_having_alpha_numeric_characters(DataTable inputTypes) {
        List<InputTextType> inputType = inputTypes.asList().stream()
                .map(InputTextType::valueOf)
                .collect(Collectors.toList());
        ;
        toDoSteps.enterMultipleToDoList(inputType);
    }

    @Then("the (ENTERED|EDITED|DELETED) ToDo item should be listed under ToDo (ALL|ACTIVE|COMPLETED) list$")
    public void the_entered_to_do_item_should_be_listed_under_to_do_all_list(Enums.FlowType flow, Enums.ToDoType listType) {
        toDoSteps.verifyList(flow, listType);
    }

    @Then("complete and remove all the added ToDo items")
    public void completeAndRemove_all_the_added_to_do_items() {
        toDoSteps.completeAndDeleteCompletedToDoList();
    }
    @Then("remove all the completed ToDo items")
    public void remove_all_the_added_to_do_items() {
        toDoSteps.deleteAllCompletedToDoList();
    }

    @When("user edit the added ToDo item with (.+) characters string$")
    public void userEditTheAddedToDoItemWithUNICODECharactersString(InputTextType inputType) {
        toDoSteps.updateAddedToDoItem(inputType);
    }

    @Then("the old ToDo item should not be listed")
    public void theOldToDoItemShouldNotBeListed() {
        toDoSteps.oldDeletedToDoItemShouldNotBeListed(Enums.ToDoType.ALL);
    }

    @When("user (?:incomplete|complete) the added ToDo item$")
    public void user_complete_the_added_to_do_item() {
        toDoSteps.completeAddedToDoList();
    }


    @Then("the added ToDo item should be listed under ToDo (ALL|ACTIVE|COMPLETED) list")
    public void the_added_to_do_item_should_be_listed_under_to_do_completed_list(Enums.ToDoType listType) {
        toDoSteps.verifyList(Enums.FlowType.ENTERED, Enums.ToDoType.COMPLETED);
    }

    @When("user (?:incomplete|complete) the all added ToDo items$")
    public void and_user_complete_the_all_added_to_do_items() {
        toDoSteps.completeAllToDoListItems();
    }

    @Then("the (ENTERED|EDITED) multiple ToDo items should be listed under ToDo (ALL|ACTIVE|COMPLETED) list$")
    public void the_added_multiple_to_do_items_should_be_listed_under_to_do_completed_list(Enums.FlowType flow, Enums.ToDoType listType) {
        toDoSteps.clickListType(listType);
        toDoSteps.verifyWholeList(flow, listType);
    }

    @When("user delete the added ToDo item")
    public void user_delete_the_added_to_do_item() {
        toDoSteps.deleteAddedToDoItem();
    }

    @Then("the items left count should reflect only non completed count")
    public void theItemsLeftCountShouldReflectOnlyNonCompletedCount() {
        toDoSteps.verifyItemsLeftCount();
    }
}
