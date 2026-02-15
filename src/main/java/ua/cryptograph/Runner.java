package ua.cryptograph;

import ua.cryptograph.type.Command;
import ua.cryptograph.type.Mode;
import ua.cryptograph.domain.Argument;
import ua.cryptograph.service.EncryptionService;
import ua.cryptograph.service.FileService;
import ua.cryptograph.service.ValidationArgsService;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;


public class Runner {
    private final EncryptionService encryptionService;
    private final FileService fileService;
    private final ValidationArgsService validationArgsService;

    public Runner(ValidationArgsService validationArgsService, EncryptionService encryptionService, FileService fileService) {
        this.validationArgsService = validationArgsService;
        this.encryptionService = encryptionService;
        this.fileService = fileService;
    }

    public void run(String[] args) {
        if (getModeRunner(args) == Mode.CLI) {
            runCLI();
        } else {
            runWithArgs(args);
        }
    }

    private void runWithArgs(String[] args) {
        validationArgsService.validateCorrectNumberAndTypeParams(args);

        Argument argument = new Argument(args);

        int key = argument.getKey();

        if (argument.getCommand() == Command.BRUTE_FORCE) {
            key = encryptionService.determineKeyForDecrypt(argument);
        }

        Path pathFileToWrite = fileService.createFile(argument.getCommand(), argument.getPath(), key);

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(argument.getPath().toString()))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String newLine = encryptionService.execute(argument.getCommand(), line, key);
                fileService.writeLineToFile(newLine, pathFileToWrite);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void runCLI() {
        //todo
    }

    private Mode getModeRunner(String[] args) {
        if (args.length == 0) {
            return Mode.CLI;
        } else if (args.length == 2 || args.length == 3) {
            return Mode.WITH_ARGS;
        } else {
            throw new IllegalArgumentException("Incorrect number of arguments");
        }
    }
}
