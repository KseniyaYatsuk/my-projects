import aquality.selenium.browser.AqualityServices;
import aquality.selenium.core.utilities.ISettingsFile;
import aquality.selenium.core.utilities.JsonSettingsFile;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTest {

    ISettingsFile environment = new JsonSettingsFile("config.json");
    private final String BASE_URL = environment.getValue("/baseUrl").toString();

    @BeforeMethod
    public void startDriver() {
        AqualityServices.getBrowser().goTo(BASE_URL);
    }

    @AfterMethod
    public void closeDriver() {
        AqualityServices.getBrowser().quit();
    }
}
