package utility;

import java.io.File;

public class CheckFileExists {
    public static boolean check(String filePath){
        File file = new File(filePath);
        return file.exists() ? true : false;
    }
}
