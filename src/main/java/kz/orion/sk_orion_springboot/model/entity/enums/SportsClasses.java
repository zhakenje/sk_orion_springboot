package kz.orion.sk_orion_springboot.model.entity.enums;

public enum SportsClasses {
    SWIM("БАССЕЙН"),
    GYM("ТРЕНАЖЕРНЫЙ ЗАЛ"),
    AEROBICS("АЭРОБИКА"),
    TENNIS("ТЕННИСНЫЙ КОРТ");

    private final String str;

    SportsClasses(String str){
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
