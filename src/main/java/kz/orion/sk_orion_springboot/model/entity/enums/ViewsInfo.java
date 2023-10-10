package kz.orion.sk_orion_springboot.model.entity.enums;

public enum ViewsInfo {
    INFO_INS("ИНФОРМАЦИЯ О ИНСТРУКТОРАХ"),
    INFO_SK("ИНФОРМАЦИЯ О БАССЕЙНЕ");

    private final String str;

    ViewsInfo(String str){
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
