package ru.ilka.tfidf.demo;

import ru.ilka.tfidf.logic.TfIdfLogic;
import ru.ilka.tfidf.reader.TextFileReader;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        TfIdfLogic tfIdfLogic = new TfIdfLogic();
        TextFileReader textFileReader = new TextFileReader();

        ArrayList<String> documents = new ArrayList<>(args.length);
        for (int i = 0; i < args.length; i++) {
            documents.add(textFileReader.readFile(args[i]));
        }

        for (int i = 0; i < documents.size(); i++) {
            HashMap<String, Double> documentTermsTfIdf = new HashMap<>();
            String[] terms = documents.get(i).split("([\\W\\s]+)");
            for (int j = 0; j < terms.length; j++) {
                documentTermsTfIdf.put(terms[i], tfIdfLogic.countTFIDF(terms[i], documents.get(i), documents));
            }

            String maxTfIdfTerm = "";
            double maxTfIdfValue = 0.0;

            maxTfIdfValue = (Collections.max(documentTermsTfIdf.values()));
            for (Map.Entry<String, Double> entry : documentTermsTfIdf.entrySet()) {  // Itrate through hashmap
                if (entry.getValue() == maxTfIdfValue) {
                    maxTfIdfTerm = entry.getKey();
                }
            }

            System.out.println("-----------------------------------------------");
            System.out.println("Document - " + args[i]);
            System.out.println("Max TF-IDF term: " + maxTfIdfTerm + " has value: " + maxTfIdfValue);
            System.out.println("-----------------------------------------------");
        }
	   /* String test = "a  a a b c  d.";

        System.out.println(tfIdfLogic.countTF("a",test));*/
    }
}
