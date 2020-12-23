package fr.romdhani.scaridae.core.orm;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "project")
public class Project implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "type")
    private String type;

    @Column(name = "creation_time")
    private Timestamp creationTime = new Timestamp(new Date().getTime());

    @Column(name = "last_modification_time")
    private Timestamp lastModificationTime = new Timestamp(new Date().getTime());

    @Column(name = "serialized_properties")
    private String serializedProperties;

    @OneToMany(mappedBy = "project")
    private Set<ObjectData> objectDataSet = new HashSet<>();

    // bi-directional many-to-many association to userAccount
    @ManyToMany(mappedBy = "projectSet")
    private Set<UserAccount> userAccountSet = new HashSet<>();

    // bi-directional many-to-many association to intervention
    @ManyToMany(mappedBy = "interventionProjectSet")
    private Set<Intervention> interventionSet = new HashSet<>();


    public Project() {
    }

    public Project(String name, String description) {
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Timestamp getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Timestamp creationTime) {
        this.creationTime = creationTime;
    }

    public Timestamp getLastModificationTime() {
        return lastModificationTime;
    }

    public void setLastModificationTime(Timestamp lastModificationTime) {
        this.lastModificationTime = lastModificationTime;
    }

    public String getSerializedProperties() {
        return serializedProperties;
    }

    public void setSerializedProperties(String serializedProperties) {
        this.serializedProperties = serializedProperties;
    }

    public Set<UserAccount> getUserAccountSet() {
        return userAccountSet;
    }

    public void setUserAccountSet(Set<UserAccount> userAccountSet) {
        this.userAccountSet = userAccountSet;
    }

    public Set<ObjectData> getObjectDataSet() {
        return objectDataSet;
    }

    public void setObjectDataSet(Set<ObjectData> objectDataSet) {
        this.objectDataSet = objectDataSet;
    }

    public Set<Intervention> getInterventionSet() {
        return interventionSet;
    }

    public void setInterventionSet(Set<Intervention> interventionSet) {
        this.interventionSet = interventionSet;
    }

    @Override
    public String toString() {
        return "Project{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", type='" + type + '\'' +
                ", creationTime=" + creationTime +
                '}';
    }
}
