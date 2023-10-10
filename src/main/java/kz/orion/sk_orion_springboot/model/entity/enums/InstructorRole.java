package kz.orion.sk_orion_springboot.model.entity.enums;

public enum InstructorRole{

    INSTRUCTOR_SWIM("ИНСТРУКТОР ПО ПЛАВАНИЮ"),
    INSTRUCTOR_GYM("ИНСТРУКТОР ПО ТРЕНАЖЕРУ"),
    INSTRUCTOR_AEROBICS("ИНСТРУКТОР ПО АЭРОБИКЕ"),
    INSTRUCTOR_TENNIS("ИНСТРУКТОР ПО ТЕННИСУ");

    private final String str;

    InstructorRole(String str){
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
