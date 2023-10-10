package kz.orion.sk_orion_springboot.model.entity.enums;

public enum TypeGraph {
    TYPE_YEAR("ГРАФИК ПО ГОДУ"),
    TYPE_MONTH("ГРАФИК ПО МЕСЯЦУ"),
    TYPE_DAY("ГРАФИК ПО ДНЯМ");

    private final String str;

    TypeGraph(String str){
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
