package kz.orion.sk_orion_springboot.model.entity.enums;

public enum TypeSubscription{

    SUBSCRIPTION_MONTH_GYM("МЕСЯЧНЫЙ АБОНЕМЕНТ(ТРЕНАЖЕРНЫЙ ЗАЛ)"),
    SUBSCRIPTION_MONTH_POOL("МЕСЯЧНЫЙ АБОНЕМЕНТ(БАССЕЙН)"),

    SUBSCRIPTION_DAY_POOL("ДНЕВНОЙ АБОНЕМЕНТ(БАССЕЙН)"),

    SUBSCRIPTION_DAY_GYM("ДНЕВНОЙ АБОНЕМЕНТ(ТРЕНАЖЕРНЫЙ ЗАЛ)");

    private final String str;


    TypeSubscription(String str) {
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