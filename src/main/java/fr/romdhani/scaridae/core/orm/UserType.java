package fr.romdhani.scaridae.core.orm;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

/**
 * Used for user type : intern or extern user for example
 */
@Entity
@Table(name = "user_account_type")
public class UserType implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "start_time")
    private Timestamp startTime = new Timestamp(new Date().getTime());

    @Column(name = "serialized_properties")
    private String serializedProperties;

    @ManyToOne
    @JoinColumn(name = "user_account_id", nullable = true)
    private UserAccount userAccountType;

    public UserType() {
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

    public Timestamp getStartTime() {
        return startTime;
    }

    public void setStartTime(Timestamp startTime) {
        this.startTime = startTime;
    }

    public String getSerializedProperties() {
        return serializedProperties;
    }

    public void setSerializedProperties(String serializedProperties) {
        this.serializedProperties = serializedProperties;
    }

    public UserAccount getUserType() {
        return userAccountType;
    }

    public void setUserType(UserAccount userAccountType) {
        this.userAccountType = userAccountType;
    }

    public UserType(String name, String description) {
        this.name = name;
        this.description = description;
    }

    @Override
    public String toString() {
        return "UserType{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
