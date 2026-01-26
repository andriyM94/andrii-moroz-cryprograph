package ua.cryptograph.service;

import ua.cryptograph.constant.Alphabet;
import ua.cryptograph.constant.Language;

import java.util.ArrayList;
import java.util.List;

public class AlphabetService {
    private final List<Character> alphabet;

    public AlphabetService(Language language) {
        alphabet = getListAlphabetByLanguage(language);
    }

    public List<Character> getAlphabet() {
        return alphabet;
    }

    private List<Character> getListAlphabetByLanguage(Language language) {
        String alphabetLoc;

        Alphabet alphabetConst = new Alphabet();

        alphabetLoc = switch (language) {
            case EN -> alphabetConst.getEn() + alphabetConst.getSymbols();
            case UA -> alphabetConst.getUa() + alphabetConst.getSymbols();
            //todo: create custom exception
            default ->  throw new RuntimeException("Not correct language");
        };

        List<Character> tmpList = new ArrayList<>(alphabetLoc.length());

        char[] charArray = alphabetLoc.toCharArray();

        for (char c : charArray) {
            tmpList.add(c);
        }

        return tmpList;
    }
}
