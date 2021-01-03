package fr.romdhani.scaridae.core.orm.enums.request;

public enum ReqGroup {
    HARDWARE("Hardware"), SOFTWARE("software"), MISCELLANEOUS("Miscellaneous");
    private String name;

    ReqGroup(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public ReqGroup getReqGroup(String name) {
        for (ReqGroup reqGroup : ReqGroup.values()) {
            if (reqGroup.getName().equals(name))
                return reqGroup;
        }
        return null;
    }

    @Override
    public String toString() {
        return name;
    }
}
