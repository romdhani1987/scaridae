package fr.romdhani.scaridae.core.orm.enums.request;

public enum Priority {
    TRIVIAL("Trivial"), MINOR("Minor"), MAJOR("Major"), CRITICAL("Critical"), BLOCKER("Blocker");
    private String priority;

    Priority(String name) {
        this.priority = name;
    }

    public String getPriority() {
        return priority;
    }

    public static Priority getPriority(String priorityStr) {
        for (Priority priority : Priority.values()) {
            if (priority.getPriority().equals(priorityStr))
                return priority;
        }
        return null;
    }

    @Override
    public String toString() {
        return priority;
    }

}
