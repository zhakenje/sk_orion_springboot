package kz.orion.sk_orion_springboot.model.entity.enums;

import lombok.Data;

public enum Months {
    JAN("ЯНВАРЬ", 1),
    FEB("ФЕВРАЛЬ", 2),
    MAR("МАРТ", 3),
    APR("АПРЕЛЬ", 4),
    MAY("МАЙ", 5),
    JUNE("ИЮНЬ", 6),
    JULY("ИЮЛЬ", 7),
    AUG("АВГУСТ", 8),
    SEP("СЕНТЯБРЬ", 9),
    OCT("ОКТЯБРЬ", 10),
    NOV("НОЯБРЬ", 11),
    DEC("ДЕКАБРЬ", 12);

    private final String str;
    private final int id;

    Months(String str, int id) {
        this.str = str;
        this.id = id;
    }

    public String getStr() {
        return str;
    }

    public int getId(){return id;}

    @Override
    public String toString() {
        return str;
    }
}
