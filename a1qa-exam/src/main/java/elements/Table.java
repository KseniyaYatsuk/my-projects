package elements;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.core.elements.ElementState;
import aquality.selenium.elements.Element;
import aquality.selenium.elements.Label;
import org.openqa.selenium.By;

import java.util.List;

public class Table extends Element {

    public Table(By loc, String nameOf, ElementState stateOf) {
        super(loc, nameOf, stateOf);
    }

    public List<Label> getAllRows(int indexOfHeader) {
        List<Label> allRows = this.findChildElements(By.tagName("tr"), "rows", Label.class);
        if (indexOfHeader != -1) {
            allRows.remove(indexOfHeader);
        }
        return allRows;
    }

    public List<Label> getAllCellsFromTheRow(Label row) {
        return row.findChildElements(By.tagName("td"), "cells", Label.class);
    }

    public boolean isRowWithContentExist(String contentLocator) {
        return this.findChildElement(By.xpath(String.format("//tr//td//%s", contentLocator)), "Row with content", Label.class)
                .state().waitForDisplayed(AqualityServices.getConfiguration().getTimeoutConfiguration().getCondition());
    }

    @Override
    protected String getElementType() {
        return "Table";
    }
}
