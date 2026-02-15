package ua.cryptograph.domain;

import ua.cryptograph.type.Command;
import java.nio.file.Path;

public class Argument {
    private final Command command;
    private final Path path;
    private final Integer key;

    public Argument(String[] args) {
        this.command = Command.valueOf(args[0]);
        this.path = Path.of(args[1]);

        if (this.command != Command.BRUTE_FORCE) {
            this.key = Integer.valueOf(args[2]);
        } else {
            key = 0;
        }
    }

    public Command getCommand() {
        return command;
    }

    public Path getPath() {
        return path;
    }

    public Integer getKey() {
        return key;
    }

    @Override
    public String toString() {
        return "Argument{command=" + command +
                ", path=" + path +
                ", key=" + key +
                '}';
    }
}
