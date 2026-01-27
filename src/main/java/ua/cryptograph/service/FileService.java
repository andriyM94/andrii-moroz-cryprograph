package ua.cryptograph.service;

import ua.cryptograph.constant.Command;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileService {
    private final Command[] commandNameArr = Command.values();

    public void writeLineToFile(String line, Path pathFileToWrite) {
        try (FileWriter fileWriter = new FileWriter(pathFileToWrite.toString(), true);
             PrintWriter printWriter = new PrintWriter(fileWriter)) {
             printWriter.println(line);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Path createFile(Command command, Path originalPath) {
        String newFileName = createNewNameFileByCommand(command, originalPath);

        Path newPath = Path.of(originalPath.getParent().toString(), newFileName);

        if (!Files.exists(newPath)) {
            try {
                return Files.createFile(newPath);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            try {
                PrintWriter writer = new PrintWriter(newPath.toString());
                writer.print("");
                writer.close();
            } catch (FileNotFoundException e) {
                //TODO
                e.printStackTrace();
            }
            return newPath;
        }
    }

    private String createNewNameFileByCommand(Command command, Path path) {
        String fileName = path.getFileName().toString();
        String basePath = path.getParent().toString();

        System.out.println(basePath);

        String fileNameWithoutExtension = "";
        String extensionWithDot = "";

        int lastIndexOf = fileName.lastIndexOf(".");
        if (lastIndexOf != -1) {
            fileNameWithoutExtension = fileName.substring(0, lastIndexOf);
        }

        if (lastIndexOf > 0 && lastIndexOf < fileName.length() - 1) {
            extensionWithDot = fileName.substring(lastIndexOf);
        }

        fileNameWithoutExtension = cleanFileName(fileNameWithoutExtension);

        return  fileNameWithoutExtension + " [" + command.name() + "]" + extensionWithDot;
    }

    private String cleanFileName(String fileName) {
        String cleanedFileName = fileName;

        for (Command command : commandNameArr) {
            cleanedFileName = cleanedFileName.replace("[" + command.name() + "]", "").trim();
        }

        return cleanedFileName;
    }
}
