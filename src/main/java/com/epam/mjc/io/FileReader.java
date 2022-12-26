package com.epam.mjc.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;


public class FileReader {

    private String read(File file) {
        String result = "";
        try (FileInputStream inputStream = new FileInputStream(file)) {
            int bits;
            while ((bits = inputStream.read()) != -1) {
                result += (char)bits;
            }
        }
        catch (IOException e) {
            System.err.println(e.getMessage());
        }

        return result;
    }

    public Profile getDataFromFile(File file) {

        String text = read(file);
        String[] lines = text.split("\n");
        String[] info = new String[4];
        int ind = 0;
        for (String line : lines) {
            for (int i = 0; i < line.length(); i++) {
                if (line.charAt(i) == ':') {
                    info[ind++] = line.substring(i+1).trim();
                }
            }
        }
        try {
            return new Profile(info[0], Integer.parseInt(info[1]), info[2], Long.parseLong(info[3]));
        }
        catch (NumberFormatException e) {
            System.err.println(e.getMessage());
            return null;
        }
    }
}
