package fr.romdhani.scaridae.core.orm.enums.request;

public enum RequestGroup {
    HARDWARE("Hardware"), SOFTWARE("Software"),QUALITY("Quality"), MISCELLANEOUS("Miscellaneous");
    private String name;

    RequestGroup(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static RequestGroup getReqGroup(String name) {
        for (RequestGroup requestGroup : RequestGroup.values()) {
            if (requestGroup.getName().equals(name))
                return requestGroup;
        }
        return null;
    }

    @Override
    public String toString() {
        return name;
    }
}
