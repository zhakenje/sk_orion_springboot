package kz.orion.sk_orion_springboot.model.entity.enums;

public enum TypeGroup {

    TYPE_GROUP_SWIM("ГРУППА ПО ПЛАВАНИЮ(ВЗРОСЛЫЕ)"),
    TYPE_GROUP_GYM("ГРУППА ПО ТРЕНАЖЕРУ(ВЗРОСЛЫЕ)"),
    TYPE_GROUP_SWIM_KIDS("ГРУППА ПО ПЛАВАНИЮ(УЧАЩИЙСЯ В СОШ)"),
    TYPE_GROUP_GYM_KIDS("ГРУППА ПО ТРЕНАЖЕРУ(УЧАЩИЙСЯ В СОШ)");

    private final String str;

    TypeGroup(String str){
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
