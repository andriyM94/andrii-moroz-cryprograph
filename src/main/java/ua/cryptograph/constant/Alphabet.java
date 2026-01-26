package ua.cryptograph.constant;

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
