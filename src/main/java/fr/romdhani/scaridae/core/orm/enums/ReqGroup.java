package fr.romdhani.scaridae.core.orm.enums;

public enum ReqGroup {
    HARDWARE("Hardware"), SOFTWARE("software"), MISCELLANEOUS("Miscellaneous");
    private String name;

    ReqGroup(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }
}
