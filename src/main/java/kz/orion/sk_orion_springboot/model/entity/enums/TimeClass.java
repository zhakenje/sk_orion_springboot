package kz.orion.sk_orion_springboot.model.entity.enums;

public enum TimeClass {
    FIRST_TIME("08:30-09:30"),
    SECOND_TIME("09:30-10:30"),
    THIRD_TIME("10:30-11:30"),
    FOURTH_TIME("11:30-12:30"),
    FIFTH_TIME("12:30-13:30"),
    SIXTH_TIME("13:30-14:30"),
    SEVENTH_TIME("14:30-15:30"),
    EIGHTH_TIME("15:30-16:30"),
    NINTH_TIME("16:30-17:30"),
    TENTH_TIME("17:30-18:30"),
    ELEVENTH_TIME("18:30-19:30"),
    TWELFTH_TIME("19:30-20:30");

    private final String str;

    TimeClass(String str){
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
