package ua.cryptograph.service;

import ua.cryptograph.domain.Argument;
import ua.cryptograph.type.Command;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.CharBuffer;
import java.util.*;

public class EncryptionService {
    private final String pattern = "[\\s\\S]*[A-Za-zА-Яа-я], [A-Za-zА-Яа-я][\\s\\S]*";
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
            case Command.DECRYPT, Command.BRUTE_FORCE -> decrypt(text, key);
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

                newCharArr[i] = alphabetCharArray.get(
                        Math.floorMod(index + key, alphabetCharArray.size())
                );
            } else {
                newCharArr[i] = textCharArray[i];
            }
        }

        return new String(newCharArr);
    }

    private int searchKeyValue(String text) {

        for (int i = 0; i < alphabetCharArray.size(); i++) {
            String decrypt = decrypt(text, i);
            boolean containsPattern = decrypt.matches(pattern);
            if (containsPattern) {
                return i;
            }
        }

        throw new RuntimeException("Error search key");
    }

    public int determineKeyForDecrypt(Argument argument) {
        int key = 0;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(argument.getPath().toString()))) {
            CharBuffer buffer = CharBuffer.wrap(new char[1000]);
            if (bufferedReader.read(buffer) != 0) {
                key = searchKeyValue(new String(buffer.array()));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return key;
    }
}
