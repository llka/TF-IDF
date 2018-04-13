package ru.ilka.tfidf.reader;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TextFileReader {

    public String readFile(String filePath) {
        StringBuilder fileString = new StringBuilder("");
        FileReader fr = null;
        BufferedReader br = null;
        File file = new File(filePath);

        if (!file.exists()) {
            System.out.println("Can't find the file ( " + filePath + " )");
            throw new RuntimeException("No input data");
        }

        try {
            String line;
            fr = new FileReader(file);
            br = new BufferedReader(fr);

            while ((line = br.readLine()) != null) {
                fileString.append(line);
            }
        } catch (FileNotFoundException e) {
            System.out.println("FileNotFoundException but this catch is unreachable");
        } catch (IOException e) {
            System.out.println("IOException in dataReader but this catch is unreachable");
        } finally {
            try {
                br.close();
            } catch (IOException ex) {
                System.out.println("IOException in dataReader closing" + ex);
            }
            try {
                fr.close();
            } catch (IOException ex) {
                System.out.println("IOException in dataReader closing" + ex);
            }
        }
        return fileString.toString();
    }

}
