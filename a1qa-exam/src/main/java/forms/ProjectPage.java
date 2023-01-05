package forms;

import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

public class ProjectPage extends Form {

    public ProjectPage() {
        super(By.xpath("//div[@id='pie']//canvas"), "Project page");
    }
}
