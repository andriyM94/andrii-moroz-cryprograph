package ua.cryptograph.service;

import ua.cryptograph.constant.Command;
import ua.cryptograph.constant.Language;
import ua.cryptograph.helper.Argument;

import java.nio.file.Files;
import java.nio.file.Path;

public class ValidationArgsService {
    private Command command;
    private Path filePath;
    private Integer key;

    private Language language;

    String[] args;

    public ValidationArgsService(String language, String[] args) {
        this.args = args;
        this.language = Language.valueOf(language.toUpperCase());

    }

    public void validate() {
        validateCommand(args[0]);
        validateFilePath(args[1]);

        if (args.length == 3) {
            validateKey(args[2]);
        }
    }

    public Argument getArgument() {
        return new Argument(this.language, this.command, this.filePath, this.key);
    }

    private void validateKey(String key) {
        this.key = Integer.parseInt(key);

        if (this.key == 0 || this.key < 1) {
            //todo: create custom exception
            throw new RuntimeException("Invalid parameter value key");
        }
    }

    private void validateFilePath(String path) {
        this.filePath = Path.of(path);
        //todo create FilePathService and use its functional
        if (!Files.exists(this.filePath)) {
            throw new RuntimeException("Invalid path to file");
        }
    }

    private void validateCommand(String command) {
        this.command = Command.valueOf(command);

        if (
                (this.command == Command.BRUTE_FORCE && args.length == 3) ||
                        (this.command != Command.BRUTE_FORCE && args.length == 2)
        ) {
            throw new RuntimeException("Invalid number of parameters (args.length = "
                    + args.length
                    + ") for the selected command. Command - " + this.command.name());
        }
    }
}
