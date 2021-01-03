package fr.romdhani.scaridae.core.orm.enums.user;

public enum UserRole {
    USER("User", "Standard user,allowed only for his requests, his actions and his projects"), EDITOR("Editor", "Editor role allowed to create, delete and edit projects"), ANONYMOUS("Anonymous", "Only watching"), ADMIN("Admin", "Admin rights");
    private String role;
    private String description;

    UserRole(String role, String description) {
        this.role = role;
        this.description = description;
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
