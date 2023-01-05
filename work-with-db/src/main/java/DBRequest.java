import Utils.SQLUtils;
import Utils.StringUtils;
import aquality.selenium.core.logging.Logger;
import parser.JsonParser;

public class DBRequest {

    Object[][] resultTable;

    public void makeTaskOne() {
        resultTable = SQLUtils.performSQLRequest(JsonParser.parseTestData().get("RequestOne"));
        Logger.getInstance().info(StringUtils.makeStringFromArray(resultTable));
    }

    public void makeTaskTwo() {
        resultTable = SQLUtils.performSQLRequest(JsonParser.parseTestData().get("RequestTwo"));
        Logger.getInstance().info(StringUtils.makeStringFromArray(resultTable));
    }

    public void makeTaskThree() {
        resultTable = SQLUtils.performSQLRequest(JsonParser.parseTestData().get("RequestThree"));
        Logger.getInstance().info(StringUtils.makeStringFromArray(resultTable));
    }

    public void makeTaskFour() {
        resultTable = SQLUtils.performSQLRequest(JsonParser.parseTestData().get("RequestFour"));
        Logger.getInstance().info(StringUtils.makeStringFromArray(resultTable));
    }
}
