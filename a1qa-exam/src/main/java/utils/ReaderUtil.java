package utils;

import aquality.selenium.core.logging.Logger;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ReaderUtil {

    public static String readFile(String path, Charset encoding) {
        try {
            return Files.readString(Paths.get(path), encoding);
        } catch (IOException e) {
            Logger.getInstance().error(e.toString());
            throw new RuntimeException();
        }
    }
}
