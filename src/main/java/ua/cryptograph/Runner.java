package ua.cryptograph;

import ua.cryptograph.constant.Command;
import ua.cryptograph.constant.Language;
import ua.cryptograph.constant.Mode;
import ua.cryptograph.helper.Argument;
import ua.cryptograph.service.AlphabetService;
import ua.cryptograph.service.EncryptionService;
import ua.cryptograph.service.FileService;
import ua.cryptograph.service.ValidationArgsService;


public class Runner {
    private final AlphabetService alphabetService;
    private final EncryptionService encryptionService;
    private final FileService fileService;
    private ValidationArgsService validationArgsService;

    private Mode mode;

    private Argument argument;

    public Runner(String[] args, ValidationArgsService validationArgsService, AlphabetService alphabetService, EncryptionService encryptionService, FileService fileService) {
        this.validationArgsService = validationArgsService;
        this.alphabetService = alphabetService;
        this.encryptionService = encryptionService;
        this.fileService = fileService;

        setMode(args);

        if (this.mode == Mode.WITH_ARGS) {
            validateCorrectNumberParams(args);
            validationArgsService.validate();
        }

        setArgument(args);
    }

    private void setArgument(String[] args) {
        this.argument = new Argument(args);
    }

    private void validateCorrectNumberParams(String[] args) {
        if (args.length == 2 && !args[0].equals(Command.BRUTE_FORCE.name())) {
            throw new IllegalArgumentException("For ENCRYPT and DECRYPT, parameter 'key' must be present.");
        }

        if (args.length == 3 && args[0].equals(Command.BRUTE_FORCE.name())) {
            throw new IllegalArgumentException("For BRUTE_FORCE, the presence of parameter 'ket' is unacceptable.");
        }
    }

    public void run() {
        if (mode == Mode.CLI) {
            runCLI();
        } else {
            runWithArgs();
        }
    }

    private void runWithArgs() {
        encryptionService.execute(this.argument);
    }

    private void runCLI() {
        //todo
    }

    private void setMode(String[] args) {
        if (args.length == 0) {
            mode = Mode.CLI;
        } else if (args.length == 2 || args.length == 3) {
            mode = Mode.WITH_ARGS;
        } else {
            //todo: create custom exception
            throw new RuntimeException("Incorrect number of arguments");
        }
    }
}
