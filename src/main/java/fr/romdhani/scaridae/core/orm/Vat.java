package fr.romdhani.scaridae.core.orm;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

/**
 * Used for billing type : card , money , cheque , restaurant ticket or other
 */
@Entity
@Table(name = "vat")

public class Vat implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "value")
    private float value;

    @Column(name = "description")
    private String description;

    @Column(name = "creation_time")
    private Timestamp creationTime = new Timestamp(new Date().getTime());

    @Column(name = "updated_time")
    private Timestamp updatedTime = new Timestamp(new Date().getTime());

    public Vat() {
    }

    public Vat(String name, float value) {
        this.name = name;
        this.value = value;
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

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
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

    public Timestamp getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(Timestamp updatedTime) {
        this.updatedTime = updatedTime;
    }

    @Override
    public String toString() {
        return "Vat{" +
                "name='" + name + '\'' +
                ", value=" + value +
                '}';
    }
}
