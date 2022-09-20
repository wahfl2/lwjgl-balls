package engine.util;

import java.awt.*;
import java.io.IOException;
import java.nio.file.*;
import java.util.Random;

public class Utils {

    private Utils() {
        // Utility class
    }

    public static String readFile(String filePath) {
        String str;
        try {
            str = new String(Files.readAllBytes(Paths.get(filePath)));
        } catch (IOException excp) {
            throw new RuntimeException("Error reading file [" + filePath + "]", excp);
        }
        return str;
    }

    public static Color randomColor() {
        Random rand = new Random();
        return new Color(rand.nextFloat(), rand.nextFloat(), rand.nextFloat());
    }

    public static float[] repeatFloatArray(float[] arr, int num) {
        float[] ret = new float[arr.length * num];
        for (int i = 0; i < num; i++) {
            int index = i * arr.length;
            for (int j = 0; j < arr.length; j++) {
                ret[index + j] = arr[j];
            }
        }
        return ret;
    }
}