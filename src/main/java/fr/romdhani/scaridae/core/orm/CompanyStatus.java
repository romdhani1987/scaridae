package fr.romdhani.scaridae.core.orm;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "company_status")
public class CompanyStatus implements Serializable {
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

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL,
            mappedBy = "companyStatus")
    private Company company;

    public CompanyStatus() {
    }

    public CompanyStatus(String name, String description) {
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

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }
}
