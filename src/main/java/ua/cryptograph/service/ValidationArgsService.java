package ua.cryptograph.service;

import ua.cryptograph.constant.Command;

import java.nio.file.Files;
import java.nio.file.Path;

public class ValidationArgsService {
    private final String[] args;

    public ValidationArgsService(String[] args) {
        this.args = args;
    }

    public void validate() {
        validateCommand();
        validateFilePath();

        if (args.length == 3) {
            validateKey();
        }
    }

    private void validateKey() {
        int key = Integer.valueOf(args[2]);

        if (key <= 0) {
            //todo: create custom exception
            throw new RuntimeException("Invalid parameter value key");
        }
    }

    private void validateFilePath() {
        if (!Files.exists(Path.of(this.args[1]))) {
            throw new RuntimeException("Invalid path to file");
        }
    }

    private void validateCommand() {
        Command.valueOf(args[0]);
    }
}
