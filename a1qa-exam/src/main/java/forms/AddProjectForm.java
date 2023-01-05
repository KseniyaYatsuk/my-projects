package forms;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.elements.interfaces.ILabel;
import aquality.selenium.elements.interfaces.ITextBox;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

public class AddProjectForm extends Form {

    private final ITextBox projectNameTextBox = AqualityServices.getElementFactory()
            .getTextBox(By.id("projectName"), "Project name TextBox");

    private final IButton saveProjectButton = AqualityServices.getElementFactory()
            .getButton(By.xpath("//button[@type='submit']"), "Save project Button");

    private final ILabel successLabel = AqualityServices.getElementFactory()
            .getLabel(By.xpath("//div[contains(@class, 'alert-success')]"), "Success Label");

    public AddProjectForm() {
        super(By.id("addProjectForm"), "Add project Form");
    }

    public void enterProjectNameInTextBox(String projectName) {
        projectNameTextBox.type(projectName);
    }

    public void clickSaveProjectButton() {
        saveProjectButton.click();
    }

    public boolean isSuccessLabelAppeared() {
        return successLabel.state().waitForDisplayed(AqualityServices.getConfiguration().getTimeoutConfiguration().getCondition());
    }
}
