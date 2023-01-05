package Utils;

import aquality.selenium.core.logging.Logger;
import conection.AbstractConnection;

import java.sql.*;

public class SQLUtils {

    public static Object[][] performSQLRequest(String sql) {
        Object[][] resultTable = new Object[0][];

        try {
            PreparedStatement stmt = AbstractConnection.getConnection()
                    .prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);

            ResultSet rs = stmt.executeQuery();
            ResultSetMetaData rsmd = rs.getMetaData();

            int countOfRows = 0;

            while (rs.next()) {
                countOfRows++;
            }

            int countOfColumn = rsmd.getColumnCount();
            resultTable = new Object[countOfRows][countOfColumn];

            countOfRows = 0;
            rs.beforeFirst();

            while (rs.next()) {
                int i = 0;
                int j = 1;
                while (j <= countOfColumn) {
                    resultTable[countOfRows][i] = rs.getObject(j);
                    i++;
                    j++;
                }
                countOfRows++;
            }
        } catch (SQLException e) {
            Logger.getInstance().error(e.toString());
            throw new SQLException();
        } finally {
            AbstractConnection.closeConnection();
            return resultTable;
        }
    }
}
