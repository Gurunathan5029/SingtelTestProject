package Steps;

import Constants.Constants;
import Constants.Enums;
import Constants.StoryContextKeys;
import Pages.VuePage;
import net.serenitybdd.core.Serenity;
import net.thucydides.core.annotations.Step;
import org.junit.Assert;

import java.util.HashMap;
import java.util.List;

public class ToDoPageSteps {
    VuePage vuePage;
    private static HashMap<Enums.InputTextType, Boolean> toDoItemStatus = new HashMap<Enums.InputTextType, Boolean>();

    @Step
    public void launchToDoUrl() {
        this.vuePage.open();
        this.vuePage.getDriver().manage().window().maximize();
        Assert.assertTrue("Vue header page title should be displayed as " + Constants.todo, Constants.todo.equals(this.vuePage.getVuePageHeader()));
    }

    /**
     * Enter and Save ToDo item
     *
     * @param inputType - Type of input
     */
    @Step
    public void enterToDoList(Enums.InputTextType inputType) {
        this.vuePage.enterToDoItemInTextBox(inputType.getTextType());
        //Store the entered/added ToDo item type for future validation
        Serenity.getCurrentSession().put(StoryContextKeys.inputTextType, inputType);
        //Store the entered/added ToDo item status for future validation
        toDoItemStatus.put(inputType, false);
        Serenity.getCurrentSession().put(StoryContextKeys.InputTextMap, toDoItemStatus);
    }

    /**
     * Enter and Save multiple ToDo items
     *
     * @param inputType - List of Input types
     */
    @Step
    public void enterMultipleToDoList(List<Enums.InputTextType> inputType) {
        inputType.forEach(input -> {
            this.vuePage.enterToDoItemInTextBox(input.getTextType());
            toDoItemStatus.put(input, false);
        });
        //Store the list of entered/added ToDo item type and status for future validation
        Serenity.getCurrentSession().put(StoryContextKeys.inputTextType, inputType);
        Serenity.getCurrentSession().put(StoryContextKeys.InputTextMap, toDoItemStatus);
    }

    /**
     * Verify ToDo items list
     *
     * @param flow     - Type of flow like -entered/edited ToDo item
     * @param listType - List type from where the list should be validated
     */
    @Step
    public void verifyList(Enums.FlowType flow, Enums.ToDoType listType) {
        List<String> actList = this.vuePage.getAllItemsFromToDoList(listType.getToDoTypeUIPathStr());
        String expToDoItem = null;
        if (flow.equals(Enums.FlowType.ENTERED))
            expToDoItem = ((Enums.InputTextType) Serenity.getCurrentSession().get(StoryContextKeys.inputTextType)).getTextType();
        else if (flow.equals(Enums.FlowType.EDITED))
            expToDoItem = ((Enums.InputTextType) Serenity.getCurrentSession().get(StoryContextKeys.editTextType)).getTextType();
        String finalExpToDoItem = expToDoItem;
        Assert.assertEquals("Verify ToDoList contains the added ToDo Item", 0, actList.stream().filter(item -> !actList.contains(finalExpToDoItem)).count());
    }

    /**
     * Complete and delete added ToDo item
     **/
    @Step
    public void completeAndDeleteCompletedToDoList() {
        this.vuePage.completeAllTheToDoListItems();
        this.vuePage.clearCompletedToDoItems();
    }

    /**
     * Delete all the completed ToDo items
     **/
    @Step
    public void deleteAllCompletedToDoList() {
        this.vuePage.clearCompletedToDoItems();
    }

    /**
     * Update/edit added ToDo Item
     *
     * @param updateTextType - Type of input ToDo item entered
     */
    @Step
    public void updateAddedToDoItem(Enums.InputTextType updateTextType) {
        this.vuePage.doubleClickToDoItem(((Enums.InputTextType) Serenity.getCurrentSession().get(StoryContextKeys.inputTextType)).getTextType());
        this.vuePage.editToDoItem(updateTextType.getTextType());
        Serenity.getCurrentSession().put(StoryContextKeys.editTextType, updateTextType);
    }

    /**
     * Verify Deleted ToDo items should not be listed
     *
     * @param listType - List type from where the list should be validated
     */
    @Step
    public void oldDeletedToDoItemShouldNotBeListed(Enums.ToDoType listType) {
        List<String> actList = this.vuePage.getAllItemsFromToDoList(listType.getToDoTypeUIPathStr());
        String stringNotListed = ((Enums.InputTextType) Serenity.getCurrentSession().get(StoryContextKeys.inputTextType)).getTextType();
        Assert.assertEquals("Verify ToDoList not containing deleted ToDo Item", 0, actList.stream().filter(item -> actList.contains(stringNotListed)).count());
    }

    /**
     * Complete added ToDo Item independently
     **/
    @Step
    public void completeAddedToDoList() {
        this.vuePage.completeEnteredToDoItem(((Enums.InputTextType) Serenity.getCurrentSession().get(StoryContextKeys.inputTextType)).getTextType());
    }

    /**
     * Complete all the added ToDo Items
     **/
    @Step
    public void completeAllToDoListItems() {
        this.vuePage.completeAllTheToDoListItems();
        ((HashMap<Enums.InputTextType, Boolean>) Serenity.getCurrentSession().get(StoryContextKeys.InputTextMap)).entrySet().stream().forEach(ent -> {
            ent.setValue(true);
        });
    }

    /**
     * Verify ToDo items list with multiple/list of ToDo items
     *
     * @param flow     - Type of flow like -entered/edited ToDo item
     * @param listType - List type from where the list should be validated
     */
    @Step
    public void verifyWholeList(Enums.FlowType flow, Enums.ToDoType listType) {
        List<String> actList = this.vuePage.getAllItemsFromToDoList(listType.getToDoTypeUIPathStr());
        List<String> expList = null;
        if (flow.equals(Enums.FlowType.ENTERED))
            expList = (((List<Enums.InputTextType>) Serenity.getCurrentSession().get(StoryContextKeys.inputTextType))).stream().map(input -> input.getTextType()).toList();
        else
            expList = ((List<Enums.InputTextType>) Serenity.getCurrentSession().get(StoryContextKeys.editTextType)).stream().map(input -> input.getTextType()).toList();
        List<String> finalExpList = expList;
        Assert.assertTrue("Verify ToDoList contains the added ToDo List Item", actList.stream().filter(item -> !finalExpList.contains(item)).count() == 0);
    }

    /**
     * Click on the list type
     *
     * @param listType - List type from where the list should be validated
     */
    @Step
    public void clickListType(Enums.ToDoType listType) {
        this.vuePage.clickOnListType(listType);
    }

    /**
     * Delete added ToDo Item
     **/
    @Step
    public void deleteAddedToDoItem() {
        this.vuePage.deleteAddedToDoListItem(((Enums.InputTextType) Serenity.getCurrentSession().get(StoryContextKeys.inputTextType)).getTextType());
    }

    /**
     * Verify Items left to complete list
     **/
    @Step
    public void verifyItemsLeftCount() {
        String expCountStr = String.format(Constants.listCount, String.valueOf(((HashMap<Enums.InputTextType, Boolean>) Serenity.getCurrentSession().get(StoryContextKeys.InputTextMap)).values().stream().filter(v -> v.equals(false)).count()));
        Assert.assertTrue("Items Left count should be matching only non completed count", expCountStr.equals((this.vuePage.returnItemsLeftCount())));
    }
}
