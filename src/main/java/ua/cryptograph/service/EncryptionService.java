package ua.cryptograph.service;

import ua.cryptograph.constant.Command;
import ua.cryptograph.helper.Argument;

public class EncryptionService {
    private final AlphabetService alphabetService;
    private final FileService fileService;

    public EncryptionService(AlphabetService alphabetService, FileService fileService) {
        this.alphabetService = alphabetService;
        this.fileService = fileService;
    }

    public void execute(Argument argument) {
        switch (argument.getCommand()) {
            case Command.ENCRYPT -> encrypt(argument);
            case Command.DECRYPT -> decrypt(argument);
            case Command.BRUTE_FORCE -> bruteForce(argument);
        }
    }

    private void encrypt(Argument argument) {

    }

    private void decrypt(Argument argument) {

    }

    private void bruteForce(Argument argument) {

    }
}
