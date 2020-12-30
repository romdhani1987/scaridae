package fr.romdhani.scaridae.core.orm.enums;

public enum Priority {
    TRIVIAL("Trivial"), MINOR("Minor"), MAJOR("Major"), CRITICAL("Critical"), BLOCKER("Blocker");
    private String name;

    Priority(String name) {
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
