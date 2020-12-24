package fr.romdhani.scaridae.core.orm;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

@Entity
@Table(name = "product_group")
public class ProductGroup implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "creation_time")
    private Timestamp creationTime = new Timestamp(new Date().getTime());

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

    public Timestamp getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Timestamp creationTime) {
        this.creationTime = creationTime;
    }

    public ProductGroup(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public ProductGroup() {
    }

    @Override
    public String toString() {
        return "ProductGroup{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
