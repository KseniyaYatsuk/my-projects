import aquality.selenium.browser.AqualityServices;
import aquality.selenium.core.logging.Logger;
import aquality.selenium.core.utilities.ISettingsFile;
import aquality.selenium.core.utilities.JsonSettingsFile;
import constants.ProjectConstants;
import encoder.EncodeBase64;
import enums.ContentType;
import forms.AddProjectForm;
import forms.ProjectPage;
import forms.ProjectsPage;
import forms.TestsTableForm;
import org.testng.annotations.Test;
import utils.BrowserUtils;
import utils.ListTestsUtils;
import utils.ProjectApiUtils;
import utils.ReaderUtil;

import java.nio.charset.StandardCharsets;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import static org.testng.Assert.*;

public class ProjectTest extends BaseTest {

    private static ISettingsFile environment = new JsonSettingsFile("test_data.json");
    private static ISettingsFile configEnvironment = new JsonSettingsFile("config.json");

    private static Logger logger = Logger.getInstance();

    private ProjectsPage projectsPage;
    private ProjectPage projectPage;
    private TestsTableForm testsTableForm;
    private AddProjectForm addProjectForm;

    @Test
    public void projectTest() {
        logger.info("step 1: Using api request, get a token according to the variant number");
        String token = ProjectApiUtils.getToken((Integer) environment.getValue("/numberOfVariant"));
        assertNotNull(token, "Token not generated");
        logger.info("step 2: Go to site. Pass the necessary authorization. Using cookie, pass the token generated in step 1 (the token parameter). Refresh the page");
        AqualityServices.getBrowser().getDriver().get(configEnvironment.getValue("/authorizationUrl").toString());
        BrowserUtils.createCookie(ProjectConstants.COOKIE_PARAMETER, token);
        AqualityServices.getBrowser().refresh();
        projectsPage = new ProjectsPage();
        assertTrue(projectsPage.state().waitForDisplayed(ProjectConstants.PAGE_LOAD_TIMEOUT),
                "Project page isn't open");
        assertEquals(projectsPage.getNumberOfVersionFromFooterLabel(), environment.getValue("/numberOfVariant"),
                "After refreshing the page, the variant number is incorrect in the footer");
        logger.info("step 3: Go to the Nexage project page. Using api request get a list of tests in JSON\\XML format.");
        projectsPage.clickProjectButton(environment.getValue("/project").toString());
        projectPage = new ProjectPage();
        assertTrue(projectPage.state().waitForDisplayed(ProjectConstants.PAGE_LOAD_TIMEOUT),
                "Nexage page isn't open");
        List<models.Test> testsFromApi = ProjectApiUtils.getJsonTests((Integer) environment.getValue("/projectId"));
        testsTableForm = new TestsTableForm();
        List<models.Test> testsFromUI = testsTableForm.getTestsFromTable();
        List<Date> startDate = ListTestsUtils.getStartDateListFromTestsList(testsFromUI);
        List<Date> sortStartDate = startDate;
        sortStartDate.sort(Comparator.reverseOrder());
        assertEquals(startDate, sortStartDate, "The tests on the first page are not sorted by date descending");
        assertTrue(testsFromApi.containsAll(testsFromUI),
                "The tests on the first page do not match those returned by the API request");
        logger.info("step 4: Return to the previous page in the browser (project page). Click on +Add. Enter a project name and save. To close the window for adding a project, call the js-method closePopUp(). Refresh the page");
        AqualityServices.getBrowser().goBack();
        projectsPage.clickAddProjectButton();
        AqualityServices.getBrowser().tabs().switchToLastTab();
        addProjectForm = new AddProjectForm();
        assertTrue(addProjectForm.state().waitForDisplayed(ProjectConstants.PAGE_LOAD_TIMEOUT),
                "Add project form isn't open");
        addProjectForm.enterProjectNameInTextBox(environment.getValue("/newProject").toString());
        addProjectForm.clickSaveProjectButton();
        assertTrue(addProjectForm.isSuccessLabelAppeared(), "Success message didn't appear");
        BrowserUtils.closeCurrentTabAndSwitchToPreviousTab();
        AqualityServices.getBrowser().refresh();
        assertTrue(projectsPage.isProjectDisplayed(environment.getValue("/newProject").toString()),
                "After refreshing the page, the project didn't appear in the list");
        logger.info("step 5: Go to the page of the created project. Add a test using the API (along with a log and a screenshot of the current page)");
        projectsPage.clickProjectButton(environment.getValue("/newProject").toString());
        assertTrue(projectPage.state().waitForDisplayed(ProjectConstants.PAGE_LOAD_TIMEOUT),
                "Project page isn't open");
        int testId = ProjectApiUtils.putTest(environment.getValue("/SID").toString(),
                environment.getValue("/newProject").toString(),
                environment.getValue("/testName").toString(), environment.getValue("/methodName").toString(),
                BrowserUtils.getHostAddress(), environment.getValue("/startTime").toString(),
                environment.getValue("/browser").toString());
        ProjectApiUtils.putAttachment(testId, ProjectConstants.KEY,
                EncodeBase64.encodeByteArray(AqualityServices.getBrowser().getScreenshot()), ContentType.IMAGE_PNG);
        ProjectApiUtils.putLog(testId, ReaderUtil.readFile(environment.getValue("/filePath").toString(), StandardCharsets.UTF_8));
        assertTrue(testsTableForm.isNewRowWithTestAppeared(testId), "The new test didn't display without refreshing the page");
    }
}
