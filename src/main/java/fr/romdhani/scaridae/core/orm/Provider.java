package fr.romdhani.scaridae.core.orm;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Set;


@Entity
@Table(name = "provider")
public class Provider implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "creation_time")
    private Timestamp creationTime = new Timestamp(new Date().getTime());

    //uni-directional
    @OneToOne
    @JoinColumn(name = "company_id", nullable = true)
    Company company;

    @OneToMany(mappedBy = "provider")
    Set<ProviderIncident> providerIncidentSet;

    public Provider() {
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

    public Timestamp getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Timestamp creationTime) {
        this.creationTime = creationTime;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public Set<ProviderIncident> getProviderIncidentSet() {
        return providerIncidentSet;
    }

    public void setProviderIncidentSet(Set<ProviderIncident> providerIncidentSet) {
        this.providerIncidentSet = providerIncidentSet;
    }

    public Provider(String name, Company company) {
        this.name = name;
        this.company = company;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "name='" + name + '\'' +
                '}';
    }
}















































