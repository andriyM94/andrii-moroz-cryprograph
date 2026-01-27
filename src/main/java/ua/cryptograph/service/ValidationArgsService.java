package ua.cryptograph.service;

import ua.cryptograph.constant.Command;

import java.nio.file.Files;
import java.nio.file.Path;

public class ValidationArgsService {

    public void validateCorrectNumberAndTypeParams(String[] args) {
        baseValidateArgs(args);

        validateCommand(args[0]);
        validateFilePath(args[1]);

        if (args.length == 3) {
            validateKey(args[2]);
        }
    }

    private static void baseValidateArgs(String[] args) {
        if (args.length < 2 || args.length > 3) {
            throw new IllegalArgumentException("Uncorrected number of parameters");
        }

        if (args.length == 2 && !args[0].equals(Command.BRUTE_FORCE.name())) {
            throw new IllegalArgumentException("For ENCRYPT and DECRYPT, parameter 'key' must be present.");
        }

        if (args.length == 3 && args[0].equals(Command.BRUTE_FORCE.name())) {
            throw new IllegalArgumentException("For BRUTE_FORCE, the presence of parameter 'ket' is unacceptable.");
        }
    }

    private void validateKey(String key) {
        int keyLoc = Integer.valueOf(key);

        if (keyLoc <= 0) {
            //todo: create custom exception
            throw new RuntimeException("Invalid parameter value key");
        }
    }

    private void validateFilePath(String path) {
        if (!Files.exists(Path.of(path))) {
            throw new RuntimeException("Invalid path to file");
        }
    }

    private void validateCommand(String command) {
        Command.valueOf(command);
    }
}
