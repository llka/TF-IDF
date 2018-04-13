package ru.ilka.tfidf.logic;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TfIdfLogic {
    private static final String REGEX_TERM = "(((\\s)([1-9][a-z][A-Z])*)|(([1-9][a-z][A-Z])*(\\s))|((\\s)([1-9][a-z][A-Z])*(\\s)))";

    public double countTF(String term, String document) {
        double allWordsCount = countAllTermsInDocument(document);
        if (allWordsCount > 0) {
            return countTermOccurrences(term, document) / allWordsCount;
        } else {
            return 0.0;
        }
    }

    public double countIdF(String term, List<String> documentsCollection) {
        int documentsContainingTerm = 0;
        for (String aDocumentsCollection : documentsCollection) {
            if (countTermOccurrences(term, aDocumentsCollection) > 0) {
                documentsContainingTerm++;
            }
        }
        if (documentsContainingTerm > 0) {
            return Math.log(documentsCollection.size() / documentsContainingTerm);
        } else {
            return 0.0;
        }

    }

    public double countTFIDF(String term, String document, List<String> documentsCollection) {
        return countTF(term, document) * countIdF(term, documentsCollection);
    }

    private double countTermOccurrences(String term, String document) {
        Pattern p = Pattern.compile(term);
        Matcher m = p.matcher(document);
        double count = 0;
        while (m.find()) {
            count++;
        }
        return count;
    }

    private double countAllTermsInDocument(String document) {

        String[] words = document.split("([\\W\\s]+)");
        return words.length;
    }
}
