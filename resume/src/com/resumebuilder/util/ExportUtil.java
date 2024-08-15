package com.resumebuilder.util;

import java.io.FileWriter;
import java.io.IOException;

public class ExportUtil {
    public static void exportToFile(String content, String filePath) {
        try (FileWriter writer = new FileWriter(filePath)) {
            writer.write(content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

