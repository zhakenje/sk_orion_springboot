package kz.orion.sk_orion_springboot.model.entity.enums;

public enum Role {

    ADMIN("АДМИН"),
    INSTRUCTOR("ИНСТРУКТОР"),
    VISITOR("ПОСЕТИТЕЛЬ");

    private final String str;

    Role(String str) {
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

