package fr.romdhani.scaridae.core.orm;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Set;


@Entity
@Table(name = "customer")
public class Customer implements Serializable {
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

    @OneToMany(mappedBy = "customer")
    Set<CustomerIncident> customerIncidentSet;


    public Customer() {
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

    public Set<CustomerIncident> getCustomerIncidentSet() {
        return customerIncidentSet;
    }

    public void setCustomerIncidentSet(Set<CustomerIncident> customerIncidentSet) {
        this.customerIncidentSet = customerIncidentSet;
    }

    public Customer(String name, Company company) {
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















































