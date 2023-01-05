package utils;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.core.logging.Logger;
import org.openqa.selenium.Cookie;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class BrowserUtils {

    public static void createCookie(String parameter, String cookie) {
        Cookie ck = new Cookie(parameter, cookie);
        AqualityServices.getBrowser().getDriver().manage().addCookie(ck);
    }

    public static void closeCurrentTabAndSwitchToPreviousTab() {
        AqualityServices.getBrowser().tabs().closeTab();
        AqualityServices.getBrowser().tabs().switchToLastTab();
    }

    public static String getHostAddress() {
        String hostAddress;
        try {
            hostAddress = InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            Logger.getInstance().error(e.toString());
            throw new RuntimeException();
        }
        return hostAddress;
    }
}
