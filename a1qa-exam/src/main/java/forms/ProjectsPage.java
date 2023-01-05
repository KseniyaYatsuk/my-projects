package forms;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.elements.interfaces.ILabel;
import aquality.selenium.forms.Form;
import constants.ProjectConstants;
import org.openqa.selenium.By;

public class ProjectsPage extends Form {

    private final String projectLocator = "//a[contains(@class, 'list-group-item') and contains(text(), '%s')]";

    private final ILabel textFromFooterLabel = AqualityServices.getElementFactory()
            .getLabel(By.xpath("//p[contains(@class, 'footer-text')]//span[text()]"), "Text from footer Label");

    private final IButton addProjectButton = AqualityServices.getElementFactory()
            .getButton(By.xpath("//a[contains(@href, 'addProject')]"), "Add project Button");

    public ProjectsPage() {
        super(By.xpath("//div[contains(@class, 'list-group')]//a[contains(@href, 'allTests')]"), "Projects Page");
    }

    public int getNumberOfVersionFromFooterLabel() {
        String textFromFooter = textFromFooterLabel.getText();
        return Integer.parseInt(String.valueOf(textFromFooter
                .charAt(textFromFooter.length() - ProjectConstants.INDEX_OF_CHAR_IN_FOOTER)));
    }

    public void clickProjectButton(String projectName) {
        final IButton projectButton = AqualityServices.getElementFactory().getButton(By
                        .xpath(String.format(projectLocator, projectName)),
                projectName + " project Button");
        projectButton.click();
    }

    public boolean isProjectDisplayed(String projectName) {
        final ILabel projectLabel = AqualityServices.getElementFactory().getLabel(By
                        .xpath(String.format(projectLocator, projectName)),
                projectName + " project Label");
        return projectLabel.state().isDisplayed();
    }

    public void clickAddProjectButton() {
        addProjectButton.click();
    }
}
