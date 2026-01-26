package ua.cryptograph.constant;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Alphabet {
    private final String ua = "АаБбВвГгҐґДдЕеЄєЖжЗзИиІіЇїЙйКкЛлМмНнОоПпРрСсТтУуФфХхЦцЧчШшЩщьЮюЯя";
    private final String en = "AaBbCcDdEeFfGgHhIiJjKkLlMmNnOoPpQqRrSsTtUuVvWwXxYyZz";
    private final String symbols = ".,«»\"\\':!? ";

    public String getUa() {
        return ua;
    }

    public String getEn() {
        return en;
    }

    public String getSymbols() {
        return symbols;
    }
}
