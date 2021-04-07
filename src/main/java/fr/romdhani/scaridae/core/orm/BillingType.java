package fr.romdhani.scaridae.core.orm;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Used for billing type : card , money , cheque , restaurant ticket or other
 */
@Entity
@Table(name = "billing_type")

public class BillingType implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;

    @Column(name = "ref")
    private String ref;

    @Column(name = "description")
    private String description;

    public BillingType() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BillingType(String ref, String description) {
        this.ref = ref;
        this.description = description;
    }

    @Override
    public String toString() {
        return "BillingType{" +
                "ref='" + ref + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
