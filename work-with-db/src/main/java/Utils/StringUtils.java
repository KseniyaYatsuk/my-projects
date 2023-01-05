package Utils;

public class StringUtils {

    public static String makeStringFromArray(Object[][] array) {

        String str = "";

        for (int i = 0; i < array.length; i++) {
            str = str + "\n";
            for (int j = 0; j < array[0].length; j++) {
                str = str + array[i][j] + " ";
            }
        }
        return str;
    }
}
