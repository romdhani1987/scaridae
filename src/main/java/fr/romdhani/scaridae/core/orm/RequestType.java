package fr.romdhani.scaridae.core.orm;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

/**
 * Used for request type
 */
@Entity
@Table(name = "request_type")
public class RequestType implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "serialized_properties")
    private String serializedProperties;

    public RequestType() {
    }

    public RequestType(String name, String description) {
        this.name = name;
        this.description = description;
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

    public String getSerializedProperties() {
        return serializedProperties;
    }

    public void setSerializedProperties(String serializedProperties) {
        this.serializedProperties = serializedProperties;
    }

    @Override
    public String toString() {
        return "RequestType{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
