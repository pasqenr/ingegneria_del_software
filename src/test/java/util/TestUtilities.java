package util;

import database.DatabaseWrapper;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TestUtilities {
    final static File source = new File(System.getProperty("user.dir") + "/magazzino.sqlite");
    final static File dest = new File(System.getProperty("user.dir") + "/magazzino_test.sqlite");

    public static void init() throws Exception {
        copyFileUsingStream(source, dest);

        DatabaseWrapper.forceConnectionUrl(DatabaseWrapper.getDefaultTestConnection());
    }

    public static void done() throws Exception {
        dest.delete();
    }

    public static List<String> flattenToString(Object[][] data) {
        List<String> list = new ArrayList<>();

        Arrays.stream(data)
                .forEach(objects -> Arrays.stream(objects)
                        .forEach(obj -> list.add(String.valueOf(obj))));

        return list;
    }

    private static void copyFileUsingStream(File source, File dest) throws Exception {
        try (InputStream is = new FileInputStream(source); OutputStream os = new FileOutputStream(dest)) {
            byte[] buffer = new byte[1024];
            int length;
            while ((length = is.read(buffer)) > 0) {
                os.write(buffer, 0, length);
            }
        }
    }
}
