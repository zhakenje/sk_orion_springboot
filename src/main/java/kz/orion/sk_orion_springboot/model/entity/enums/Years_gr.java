package kz.orion.sk_orion_springboot.model.entity.enums;

public enum Years_gr {

    Y_2023("2023"),
    Y_2024("2024"),
    Y_2025("2025"),
    Y_2026("2026"),
    Y_2027("2027"),
    Y_2028("2028"),
    Y_2029("2029"),
    Y_2030("2030");

    private final String str;

    Years_gr(String str) {
        this.str = str;
    }

    public String getStr() {
        return str;
    }


    @Override
    public String toString() {
        return str;
    }


}
