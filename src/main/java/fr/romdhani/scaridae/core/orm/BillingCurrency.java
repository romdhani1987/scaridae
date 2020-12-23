package fr.romdhani.scaridae.core.orm;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Used for billing currency: euro, GBP, aud or other ...
 */
@Entity
@Table(name = "billing_currency")

public class BillingCurrency implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;

    @Column(name = "currency_code")
    private String currencyCode;

    @Column(name = "default_fraction_digits")
    private int defaultFractionDigits;

    @Column(name = "numeric_code")
    private int numericCode;

    @Column(name = "description")
    private String description;

    public BillingCurrency() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public int getDefaultFractionDigits() {
        return defaultFractionDigits;
    }

    public void setDefaultFractionDigits(int defaultFractionDigits) {
        this.defaultFractionDigits = defaultFractionDigits;
    }

    public int getNumericCode() {
        return numericCode;
    }

    public void setNumericCode(int numericCode) {
        this.numericCode = numericCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BillingCurrency(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    @Override
    public String toString() {
        return "BillingCurrency{" +
                "currencyCode='" + currencyCode + '\'' +
                ", defaultFractionDigits=" + defaultFractionDigits +
                ", numericCode=" + numericCode +
                '}';
    }
}
