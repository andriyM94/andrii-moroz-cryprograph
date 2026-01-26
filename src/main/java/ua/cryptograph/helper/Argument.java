package ua.cryptograph.helper;

import ua.cryptograph.constant.Command;
import ua.cryptograph.constant.Language;

import java.nio.file.Path;

public class Argument {
    private final Language language;
    private final Command command;
    private final Path path;
    private final Integer key;

    public Argument(Language language, Command command, Path path, Integer key) {
        this.language = language;
        this.command = command;
        this.path = path;
        this.key = key;
    }

    public Language getLanguage() {
        return language;
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
        return "Argument{" +
                "language=" + language +
                ", command=" + command +
                ", path=" + path +
                ", key=" + key +
                '}';
    }
}
