package ua.cryptograph;

import ua.cryptograph.service.EncryptionService;
import ua.cryptograph.service.FileService;
import ua.cryptograph.service.ValidationArgsService;

public class Main {
    public static void main(String[] args) {
        String alphabetUa = "АаБбВвГгҐґДдЕеЄєЖжЗзИиІіЇїЙйКкЛлМмНнОоПпРрСсТтУуФфХхЦцЧчШшЩщьЮюЯя.,«»\"\\':!? ";
        String alphabetEn = "AaBbCcDdEeFfGgHhIiJjKkLlMmNnOoPpQqRrSsTtUuVvWwXxYyZz.,«»\"\\':!? ";

        FileService fileService = new FileService();
        EncryptionService encryptionService = new EncryptionService(alphabetEn);
        ValidationArgsService validationArgsService = new ValidationArgsService();

        Runner runner = new Runner(validationArgsService, encryptionService, fileService);
        runner.run(args);
    }
}
