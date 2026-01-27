package ua.cryptograph.service;

import ua.cryptograph.constant.Command;

import java.util.ArrayList;

public class EncryptionService {
    private final ArrayList<Character> alphabetCharArray;

    public EncryptionService(String alphabet) {
        ArrayList<Character> tmpList = new ArrayList<>();

        for (char c : alphabet.toCharArray()) {
            tmpList.add(c);
        }

        alphabetCharArray = tmpList;
    }

    public String execute(Command command, String text, int key) {
        key = normalizeKey(key);

        return switch (command) {
            case Command.ENCRYPT -> encrypt(text, key);
            case Command.DECRYPT -> decrypt(text, key);
            default -> throw new IllegalStateException("Unexpected value: " + command);
        };
    }

    private int normalizeKey(int key) {
        if (key > 0 && alphabetCharArray.size() - key < 0) {
            key = alphabetCharArray.size() - key;

            key = normalizeKey(key);
        } if (key < 0 && alphabetCharArray.size() + key < 0) {
            key = key + alphabetCharArray.size();

            key = normalizeKey(key);
        }

        return key;
    }

    private String encrypt(String text, int key) {
        return shiftChars(text, key);
    }

    private String decrypt(String text, int key) {
        return shiftChars(text, -key);
    }

    private String shiftChars(String text, int key) {
        char[] newCharArr = new char[text.length()];

        char[] textCharArray = text.toCharArray();

        for (int i = 0; i < textCharArray.length; i++) {
            if (alphabetCharArray.contains(textCharArray[i])) {
                int index = alphabetCharArray.indexOf(textCharArray[i]);

                if (key < 0) {
                    if(index + key < 0) {
                        int correctKey = alphabetCharArray.size() + (index + key);
                        newCharArr[i] = alphabetCharArray.get(correctKey);
                    } else {
                        newCharArr[i] = alphabetCharArray.get(index + key);
                    }
                } else {
                    if(index + key + 1 > alphabetCharArray.size()) {
                        int correctKey = index + key - alphabetCharArray.size();
                        newCharArr[i] = alphabetCharArray.get(correctKey);
                    } else {
                        newCharArr[i] = alphabetCharArray.get(index + key);
                    }
                }

            } else {
                newCharArr[i] = textCharArray[i];
            }
        }

        return new String(newCharArr);
    }

    private String bruteForce(String text) {
        return "";
    }
}
