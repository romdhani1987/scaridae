package fr.romdhani.scaridae.core.orm;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "intervention")
public class Intervention implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "conditions")
    private String conditions;

    @Column(name = "ref")
    private String ref;

    @Column(name = "start_time")
    private Timestamp startTime = new Timestamp(new Date().getTime());

    @Column(name = "end_time")
    private Timestamp enTime;

    @Column(name = "duration")
    private Timestamp duration;

    @Column(name = "last_modification_time")
    private Timestamp lastModificationTime = new Timestamp(new Date().getTime());

    @Column(name = "serialized_properties")
    private String serializedProperties;

    // bi-directional many-to-many association to ExternalDb
    @ManyToMany
    @JoinTable(name = "user_account_intervention_map", joinColumns = {@JoinColumn(name = "intervention_id")}, inverseJoinColumns = {@JoinColumn(name = "user_account_id")})
    private Set<UserAccount> interventionUserAccountSet;

    // bi-directional many-to-many association to ExternalDb
    @ManyToMany
    @JoinTable(name = "project_intervention_map", joinColumns = {@JoinColumn(name = "intervention_id")}, inverseJoinColumns = {@JoinColumn(name = "project_id")})
    private Set<Project> interventionProjectSet;

    @ManyToOne
    @JoinColumn(name = "intervention_type_id", nullable = true)
    private InterventionType interventionType;

    public Intervention() {
    }

    public Intervention(String name, String description) {
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

    public String getConditions() {
        return conditions;
    }

    public void setConditions(String conditions) {
        this.conditions = conditions;
    }

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public Timestamp getStartTime() {
        return startTime;
    }

    public void setStartTime(Timestamp startTime) {
        this.startTime = startTime;
    }

    public Timestamp getEnTime() {
        return enTime;
    }

    public void setEnTime(Timestamp enTime) {
        this.enTime = enTime;
    }

    public Timestamp getDuration() {
        return duration;
    }

    public void setDuration(Timestamp duration) {
        this.duration = duration;
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

    public Set<UserAccount> getInterventionUserAccountSet() {
        return interventionUserAccountSet;
    }

    public void setInterventionUserAccountSet(Set<UserAccount> interventionUserAccountSet) {
        this.interventionUserAccountSet = interventionUserAccountSet;
    }

    public Set<Project> getInterventionProjectSet() {
        return interventionProjectSet;
    }

    public void setInterventionProjectSet(Set<Project> interventionProjectSet) {
        this.interventionProjectSet = interventionProjectSet;
    }

    public InterventionType getInterventionType() {
        return interventionType;
    }

    public void setInterventionType(InterventionType interventionType) {
        this.interventionType = interventionType;
    }

    @Override
    public String toString() {
        return "Intervention{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
