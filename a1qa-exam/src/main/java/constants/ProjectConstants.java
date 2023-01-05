package constants;

import aquality.selenium.browser.AqualityServices;

import java.time.Duration;

public class ProjectConstants {

    public static final Duration PAGE_LOAD_TIMEOUT = AqualityServices.getConfiguration().getTimeoutConfiguration().getPageLoad();
    public static final String COOKIE_PARAMETER = "token";
    public static final int INDEX_OF_CHAR_IN_FOOTER = 1;
    public static final int INDEX_OF_NAME_IN_TABLE = 0;
    public static final int INDEX_OF_METHOD_IN_TABLE = 1;
    public static final int INDEX_OF_STATUS_IN_TABLE = 2;
    public static final int INDEX_OF_START_TIME_IN_TABLE = 3;
    public static final int INDEX_OF_END_TIME_IN_TABLE = 4;
    public static final int INDEX_OF_DURATION_IN_TABLE = 5;
    public static final int INDEX_OF_HEADER_OF_CELLS = 0;
    public static final String KEY = "content";
}
