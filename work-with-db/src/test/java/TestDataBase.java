import aquality.selenium.core.logging.Logger;
import org.testng.annotations.Test;

public class TestDataBase {

    DBRequest dbRequest;

    @Test
    public void dataBaseTest() {
        dbRequest = new DBRequest();
        Logger.getInstance()
                .info("Task 1: For each test, print the minimum running time. Sort by projects, and by tests within projects");
        dbRequest.makeTaskOne();
        Logger.getInstance()
                .info("Task 2: Display all projects in the log indicating the number of unique tests on the project");
        dbRequest.makeTaskTwo();
        Logger.getInstance()
                .info("Task 3: Display tests for each project that ran after November 7, 2015. Sort by project, and by tests within projects");
        dbRequest.makeTaskThree();
        Logger.getInstance()
                .info("Task 4: Display the number of tests that were run on Firefox and on Chrome");
        dbRequest.makeTaskFour();
    }
}
