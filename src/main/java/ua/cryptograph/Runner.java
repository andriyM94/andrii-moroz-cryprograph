package ua.cryptograph;

import ua.cryptograph.constant.Language;
import ua.cryptograph.constant.Mode;
import ua.cryptograph.helper.Argument;
import ua.cryptograph.service.ValidationArgsService;


public class Runner {
    private final String[] args;
    private final Language language;
    private Mode mode;

    private final ValidationArgsService validationArgsService;
    private Argument argument;

    public Runner(String language, String[] args) {
        this.args = args;

        setMode();

        this.language = Language.valueOf(language.toUpperCase());

        validationArgsService = new ValidationArgsService(language, args);
    }

    public void run() {
        if (mode == Mode.CLI) {
            runCLI();
        } else {
            runWithArgs();
        }
    }

    private void runWithArgs() {
        validationArgsService.validate();

        this.argument = validationArgsService.getArgument();

        System.out.println(this.argument);
    }

    private void runCLI() {
        //todo
    }

    private void setMode() {
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
