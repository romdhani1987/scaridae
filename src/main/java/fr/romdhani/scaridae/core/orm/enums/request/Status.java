package fr.romdhani.scaridae.core.orm.enums.request;

public enum Status {
    WAITING("Waiting"), PENDING("Pending"), PROGRESS("Progress"), APPROVED("Approved"), REFUSED("Refused");
    private String status;

    Status(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public Status getStatus(String statusStr) {
        for (Status status : Status.values()) {
            if (status.getStatus().equals(statusStr))
                return status;
        }
        return null;
    }

    @Override
    public String toString() {
        return status;
    }
}
