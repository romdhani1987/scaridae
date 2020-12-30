package fr.romdhani.scaridae.core.orm.enums;

public enum Status {
    WAITING("Waiting"), PENDING("Pending"), APPROVED("Approved"), REFUSED("Refused");
    private String name;

    Status(String name) {
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
