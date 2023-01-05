package conection;

import aquality.selenium.core.logging.Logger;
import parser.JsonParser;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class AbstractConnection {

    private static Connection connection;

    public static Connection getConnection() {
        try {
            Class.forName(JsonParser.parseConfigData().get("className"));
            if (connection == null) {
                connection = DriverManager.getConnection(JsonParser.parseConfigData().get("url"),
                        JsonParser.parseConfigData().get("login"),
                        JsonParser.parseConfigData().get("password"));
            }
        } catch (ClassNotFoundException | SQLException e) {
            Logger.getInstance().error(e.getMessage());
            throw new RuntimeException(e);
        }
        return connection;
    }

    public static void closeConnection() {
        try {
            connection.close();
            connection = null;
        } catch (SQLException e) {
            Logger.getInstance().error(e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
