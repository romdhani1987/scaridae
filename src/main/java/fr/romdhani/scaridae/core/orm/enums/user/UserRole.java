package fr.romdhani.scaridae.core.orm.enums.user;

public enum UserRole {
    USER("User"), EDITOR("Editor"), ANONYMOUS("Anonymous"), ADMIN("Admin");
    private String role;

    UserRole(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }

    public UserRole getUserRole(String role) {
        for (UserRole userRole : UserRole.values()) {
            if (userRole.getRole().equals(role))
                return userRole;
        }
        return null;
    }

    @Override
    public String toString() {
        return role;
    }
}
