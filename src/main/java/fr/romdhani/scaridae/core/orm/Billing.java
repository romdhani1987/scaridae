package fr.romdhani.scaridae.core.orm;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;


@Entity
@Table(name = "billing")
public class Billing implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;

    @Column(name = "ref")
    private String ref;

    @Column(name = "description")
    private String description;

    @Column(name = "amount_incl")
    private float amountIncl;

    @Column(name = "amount_excl")
    private float amountExcl;

    @Column(name = "creation_time")
    private Timestamp creationTime = new Timestamp(new Date().getTime());

    @Column(name = "validation_time")
    private Timestamp validationTime = new Timestamp(new Date().getTime());

    @Column(name = "serialized_properties")
    private String serializedProperties;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "billing_type_id", nullable = true)
    private BillingType billingtype;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "vat_id", nullable = true)
    private Vat vat;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "billing_currency_id", nullable = true)
    private BillingCurrency BillingCurrency;

    public Billing() {
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

    public float getAmountIncl() {
        return amountIncl;
    }

    public void setAmountIncl(float amountIncl) {
        this.amountIncl = amountIncl;
    }

    public float getAmountExcl() {
        return amountExcl;
    }

    public void setAmountExcl(float amountExcl) {
        this.amountExcl = amountExcl;
    }

    public Timestamp getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Timestamp creationTime) {
        this.creationTime = creationTime;
    }

    public Timestamp getValidationTime() {
        return validationTime;
    }

    public void setValidationTime(Timestamp validationTime) {
        this.validationTime = validationTime;
    }

    public String getSerializedProperties() {
        return serializedProperties;
    }

    public void setSerializedProperties(String serializedProperties) {
        this.serializedProperties = serializedProperties;
    }

    public BillingType getBillingtype() {
        return billingtype;
    }

    public void setBillingtype(BillingType billingtype) {
        this.billingtype = billingtype;
    }

    public Vat getVat() {
        return vat;
    }

    public void setVat(Vat vat) {
        this.vat = vat;
    }

    public fr.romdhani.scaridae.core.orm.BillingCurrency getBillingCurrency() {
        return BillingCurrency;
    }

    public void setBillingCurrency(fr.romdhani.scaridae.core.orm.BillingCurrency billingCurrency) {
        BillingCurrency = billingCurrency;
    }

    public Billing(float amountIncl, float amountExcl) {
        this.amountIncl = amountIncl;
        this.amountExcl = amountExcl;
    }

    public Billing(String ref, float amountIncl) {
        this.ref = ref;
        this.amountIncl = amountIncl;
    }

    @Override
    public String toString() {
        return "Billing{" +
                "ref='" + ref + '\'' +
                ", amountIncl=" + amountIncl +
                '}';
    }
}















































