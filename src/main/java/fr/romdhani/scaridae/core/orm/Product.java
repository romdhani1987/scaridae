package fr.romdhani.scaridae.core.orm;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Set;


@Entity
@Table(name = "product")
public class Product implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "details")
    private String details;

    @Column(name = "price_incl")
    private float priceIncl;

    @Column(name = "price_excl")
    private float priceExcl;

    @Column(name = "manufacturing_time")
    private Timestamp manufacturingTime;

    @Column(name = "expire_time")
    private Timestamp expireTime;

    @Column(name = "validity_time")
    private Timestamp validityTime;

    @Column(name = "serialized_properties")
    private String serializedProperties;

    //uni-directional
    @OneToOne
    @JoinColumn(name = "provider_id", nullable = true)
    Provider provider;

    //uni-directional
    @OneToOne
    @JoinColumn(name = "product_group_id", nullable = true)
    ProductGroup productGroup;

    //uni-directional
    @OneToOne
    @JoinColumn(name = "product_category_id", nullable = true)
    ProductCategory productCategory;

    //uni-directional
    @OneToOne
    @JoinColumn(name = "product_item_id", nullable = true)
    ProductItem productItem;

    //uni-directional
    @OneToOne
    @JoinColumn(name = "product_type_id", nullable = true)
    ProductType proudctType;

    //uni-directional
    @OneToOne
    @JoinColumn(name = "product_use_id", nullable = true)
    ProductUse productUse;

    //uni-directional
    @OneToOne
    @JoinColumn(name = "vat_id", nullable = true)
    Vat vat;

    @ManyToMany(mappedBy = "productSet")
    Set<UserAccount> userAccountSet;

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

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public float getPriceIncl() {
        return priceIncl;
    }

    public void setPriceIncl(float priceIncl) {
        this.priceIncl = priceIncl;
    }

    public float getPriceExcl() {
        return priceExcl;
    }

    public void setPriceExcl(float priceExcl) {
        this.priceExcl = priceExcl;
    }

    public Timestamp getManufacturingTime() {
        return manufacturingTime;
    }

    public void setManufacturingTime(Timestamp manufacturingTime) {
        this.manufacturingTime = manufacturingTime;
    }

    public Timestamp getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(Timestamp expireTime) {
        this.expireTime = expireTime;
    }

    public Timestamp getValidityTime() {
        return validityTime;
    }

    public void setValidityTime(Timestamp validityTime) {
        this.validityTime = validityTime;
    }

    public String getSerializedProperties() {
        return serializedProperties;
    }

    public void setSerializedProperties(String serializedProperties) {
        this.serializedProperties = serializedProperties;
    }

    public Provider getProvider() {
        return provider;
    }

    public void setProvider(Provider provider) {
        this.provider = provider;
    }

    public ProductGroup getProudctGroup() {
        return productGroup;
    }

    public void setProudctGroup(ProductGroup productGroup) {
        this.productGroup = productGroup;
    }

    public ProductCategory getProudctCategory() {
        return productCategory;
    }

    public void setProudctCategory(ProductCategory productCategory) {
        this.productCategory = productCategory;
    }

    public ProductItem getProudctItem() {
        return productItem;
    }

    public void setProudctItem(ProductItem productItem) {
        this.productItem = productItem;
    }

    public ProductType getProudctType() {
        return proudctType;
    }

    public void setProudctType(ProductType proudctType) {
        this.proudctType = proudctType;
    }

    public ProductUse getProductUse() {
        return productUse;
    }

    public void setProductUse(ProductUse productUse) {
        this.productUse = productUse;
    }

    public Set<UserAccount> getUserAccountSet() {
        return userAccountSet;
    }

    public void setUserAccountSet(Set<UserAccount> userAccountSet) {
        this.userAccountSet = userAccountSet;
    }

    public Vat getVat() {
        return vat;
    }

    public void setVat(Vat vat) {
        this.vat = vat;
    }

    public ProductGroup getProductGroup() {
        return productGroup;
    }

    public ProductCategory getProductCategory() {
        return productCategory;
    }

    public ProductItem getProductItem() {
        return productItem;
    }

    public void setProductGroup(ProductGroup productGroup) {
        this.productGroup = productGroup;
    }

    public void setProductCategory(ProductCategory productCategory) {
        this.productCategory = productCategory;
    }

    public void setProductItem(ProductItem productItem) {
        this.productItem = productItem;
    }

    public Product() {
    }

    public Product(String name, float priceIncl) {
        this.name = name;
        this.priceIncl = priceIncl;
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", details='" + details + '\'' +
                '}';
    }
}















































