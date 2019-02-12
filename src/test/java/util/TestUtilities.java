package util;

import database.DatabaseWrapper;

import java.io.*;

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
