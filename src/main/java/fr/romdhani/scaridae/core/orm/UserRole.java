package fr.romdhani.scaridae.core.orm;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "user_role")
public class UserRole implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = true)
    private User userRole;

    public UserRole() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getUserRole() {
        return userRole;
    }

    public void setUserRole(User userRole) {
        this.userRole = userRole;
    }

    public UserRole(String name, String description) {
        this.name = name;
        this.description = description;
    }

    @Override
    public String toString() {
        return "UserRole{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
