package fr.romdhani.scaridae.core.orm;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Used for intervention type
 */
@Entity
@Table(name = "intervention_type")
public class InterventionType implements Serializable {
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

    @OneToMany(mappedBy = "interventionType")
    private Set<Intervention> interventionTypeSet = new HashSet<>();

    public InterventionType() {
    }

    public InterventionType(String name, String description) {
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
        return "InterventionType{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
