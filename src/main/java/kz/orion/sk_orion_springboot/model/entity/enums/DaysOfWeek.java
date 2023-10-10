package kz.orion.sk_orion_springboot.model.entity.enums;

public enum DaysOfWeek {
    MN_WD_FR("ПН, СР, ПТ"),
    TU_TH_SA("ВТ, ЧТ, СБ");

    private final String str;

    DaysOfWeek(String str){
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
