package fr.romdhani.scaridae.core.orm.enums;

public enum Labels {
    BUG("Bug"), DOCUMENTATION("Documentation"), DUPLICATE("Duplicate"), ENHANCEMENT("Enhancement"), HELP_WANTED("Help wanted"), INVALID("Invalid"), QUESTION("Question"), WONT_FIX("Wont fix"), MISC("Miscellaneous");
    private String name;

    Labels(String name) {
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
