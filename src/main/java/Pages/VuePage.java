package Pages;

import Constants.Enums;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.DefaultUrl;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;

import java.util.List;

@DefaultUrl("http://todomvc.com/examples/vue/")
public class VuePage extends PageObject {
    @FindBy(css = "input.new-todo")
    WebElementFacade newToDoTextBox;

    @FindBy(css = "a.selected")
    WebElementFacade allBtn;

    @FindBy(xpath = "//a[text()='Active']")
    WebElementFacade activeBtn;

    @FindBy(xpath = "//a[text()='Completed']")
    WebElementFacade completedBtn;

    @FindBy(css = "button.clear-completed")
    WebElementFacade clearCompletedBtn;

    @FindBy(css = ".todo-count")
    WebElementFacade toDoCount;

    @FindBy(css = "input.edit")
    WebElementFacade editBtn;

    @FindBy(css = "header h1")
    WebElementFacade todoLabel;

    @FindBy(xpath = "//li[@class='todo%$']")
    List<WebElementFacade> toDoList;

    @FindBy(xpath = "li.todo")
    List<WebElementFacade> unCompletedTooDoList;

    @FindBy(xpath = "//section[@class='main']/label[1]")
    WebElementFacade completeAllToDoListBtn;

    @FindBy(css = "li label")
    WebElementFacade toDoLabelList;

    private final String toDoLabelLists = "label";

    private String toDoListLabel = "li.todo%s label";

    private String toDoListItemDeleteBtn = "button.destroy";


    private final String toDoListEdit = "//li[@class='todo']//label[text()='%s']";

    private final String toDoListCompleteBtn = "input.toggle";

    private String toDoParentBasedOnLabelValue = "//label[text()='%s']//ancestor::li";

    public void enterToDoItemInTextBox(String input) {
        this.newToDoTextBox.typeAndEnter(input);
    }

    public String getVuePageHeader() {
        return this.todoLabel.getText();
    }

    /**
     * Returns all the ToDo items labels
     *
     * @param listType - List Type like All,Active and Completed
     * @return - List of ToDo items
     */
    public List<String> getAllItemsFromToDoList(String listType) {
        return this.findAll(By.cssSelector(String.format(toDoListLabel, listType))).stream().map(item -> item.getText()).toList();
    }

    //Complete all the ToDo List Items
    public void completeAllTheToDoListItems() {
        this.completeAllToDoListBtn.click();
    }

    //Double click on the ToDo item label
    public void doubleClickToDoItem(String inputTxt) {
        withAction().moveToElement(this.element(By.xpath(String.format(toDoListEdit, inputTxt)))).doubleClick().perform();
    }

    //Edit the ToDo Item
    public void editToDoItem(String updateTxt) {
        this.editBtn.typeAndEnter(updateTxt);
    }

    //Delete all the completed items
    public void clearCompletedToDoItems() {
        this.clearCompletedBtn.click();
    }

    //Complete only single ToDo Item
    public void completeEnteredToDoItem(String toDoItem) {
        this.element(By.xpath(String.format(toDoParentBasedOnLabelValue, toDoItem))).findElement(By.cssSelector(toDoListCompleteBtn)).click();
    }

    //Click on the button to view ToDo List
    public void clickOnListType(Enums.ToDoType lisType) {
        switch (lisType) {
            case ALL:
                this.allBtn.click();
                break;
            case ACTIVE:
                this.activeBtn.click();
                break;
            case COMPLETED:
                this.completedBtn.click();
        }
    }

    //Remove/delete added ToDo items independently
    public void deleteAddedToDoListItem(String toDoItem) {
        //This JavaScript Executor code can be moved to separate class called Utils if it has to be used again in other methods
        JavascriptExecutor executor = (JavascriptExecutor) getDriver();
        executor.executeScript("arguments[0].click();", this.element(By.xpath(String.format(toDoParentBasedOnLabelValue, toDoItem))).findElement(By.cssSelector(toDoListItemDeleteBtn)));
    }

    //Return Items left to complete
    public String returnItemsLeftCount() {
        return this.toDoCount.getText();
    }

}
