package ua.cryptograph;

import ua.cryptograph.constant.Language;
import ua.cryptograph.service.AlphabetService;
import ua.cryptograph.service.EncryptionService;
import ua.cryptograph.service.FileService;
import ua.cryptograph.service.ValidationArgsService;

public class Main {
    public static void main(String[] args) {
        AlphabetService alphabetService = new AlphabetService(Language.UA);
        FileService fileService = new FileService();
        EncryptionService encryptionService = new EncryptionService(alphabetService, fileService);
        ValidationArgsService validationArgsService = new ValidationArgsService(args);

        Runner runner = new Runner(args, validationArgsService, alphabetService, encryptionService, fileService);
        runner.run();
    }
}