package forms;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.core.elements.ElementState;
import aquality.selenium.elements.Label;
import aquality.selenium.forms.Form;
import constants.ProjectConstants;
import elements.Table;
import models.Test;
import org.openqa.selenium.By;

import java.util.ArrayList;
import java.util.List;

public class TestsTableForm extends Form {

    private final String testInTableLocator = "a[contains(@href, 'testId=%s')]";

    private final Table testsTable = AqualityServices.getElementFactory()
            .getCustomElement(Table::new, By.id("allTests"), "Tests Table", ElementState.EXISTS_IN_ANY_STATE);

    public TestsTableForm() {
        super(By.id("allTests"), "Tests table Form");
    }

    public List<Test> getTestsFromTable() {
        List<Test> tests = new ArrayList<>();
        List<Label> rows = testsTable.getAllRows(ProjectConstants.INDEX_OF_HEADER_OF_CELLS);
        for (Label row : rows) {
            List<Label> cells = testsTable.getAllCellsFromTheRow(row);
            if (cells.size() == 7) {
                tests.add(new Test(cells.get(ProjectConstants.INDEX_OF_DURATION_IN_TABLE).getText(),
                        cells.get(ProjectConstants.INDEX_OF_METHOD_IN_TABLE).getText(),
                        cells.get(ProjectConstants.INDEX_OF_NAME_IN_TABLE).getText(),
                        cells.get(ProjectConstants.INDEX_OF_START_TIME_IN_TABLE).getText(),
                        cells.get(ProjectConstants.INDEX_OF_END_TIME_IN_TABLE).getText(),
                        cells.get(ProjectConstants.INDEX_OF_STATUS_IN_TABLE).getText()));
            }
        }
        return tests;
    }

    public boolean isNewRowWithTestAppeared(int testId) {
        return testsTable.isRowWithContentExist(String.format(testInTableLocator, testId));
    }
}