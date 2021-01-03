package fr.romdhani.scaridae.core.orm.enums.request;


public enum Labels {
    BUG("Bug"), DOCUMENTATION("Documentation"), DUPLICATE("Duplicate"), ENHANCEMENT("Enhancement"), HELP_WANTED("Help wanted"), INVALID("Invalid"), QUESTION("Question"), WONT_FIX("Wont fix"), MISC("Miscellaneous");
    private String name;

    Labels(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Labels getUserRole(String name) {
        for (Labels labels : Labels.values()) {
            if (labels.getName().equals(name))
                return labels;
        }
        return null;
    }

    @Override
    public String toString() {
        return name;
    }
}
