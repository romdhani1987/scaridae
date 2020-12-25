package fr.romdhani.scaridae.core.orm;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Set;


@Entity
@NamedQueries({
        @NamedQuery(name = "findAllUsers", query = "Select u from UserAccount u")
})
@Table(name = "user_account")
public class UserAccount implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;

    @Column(name = "login")
    private String login;

    @Column(name = "password_hash")
    private String passwordHash;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "mail")
    private String mail;

    @Column(name = "phone")
    private String phone;

    @Column(name = "creation_time")
    private Timestamp creationTime = new Timestamp(new Date().getTime());

    @Column(name = "serialized_properties")
    private String serializedProperties;

    @ManyToOne
    @JoinColumn(name = "service_id", nullable = true)
    private Service service;

    @ManyToOne
    @JoinColumn(name = "user_account_group_id", nullable = true)
    private UserGroup userGroup;

    @OneToOne
    @JoinColumn(name = "address_id", nullable = true)
    private Address address;

    @OneToMany(mappedBy = "userAccountType")
    private Set<UserType> userTypeSet;

    @OneToMany(mappedBy = "userAccountFunction")
    private Set<UserFunction> userFunctionSet;

    @OneToMany(mappedBy = "userAccountRole")
    private Set<UserRole> userRoleSet;

    // bi-directional many-to-many association to Project
    @ManyToMany
    @JoinTable(name = "project_user_account_map", joinColumns = {@JoinColumn(name = "user_account_id")}, inverseJoinColumns = {@JoinColumn(name = "project_id")})
    private Set<Project> projectSet;

    @ManyToOne
    @JoinColumn(name = "contract_id", nullable = true)
    private Contract contract;

    @OneToMany(mappedBy = "interventionUserAccountSet")
    private Set<Intervention> interventionSet;


    // bi-directional many-to-many association to Product
    @ManyToMany
    @JoinTable(name = "user_account_product_map", joinColumns = {@JoinColumn(name = "user_account_id")}, inverseJoinColumns = {@JoinColumn(name = "product_id")})
    private Set<Product> productSet;

    // bi-directional many-to-many association to
    @ManyToMany
    @JoinTable(name = "user_account_request_purchase_map", joinColumns = {@JoinColumn(name = "user_account_id")}, inverseJoinColumns = {@JoinColumn(name = "request_purchase_id")})
    private Set<RequestPurchase> requestPurchaseSet;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Timestamp getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Timestamp creationTime) {
        this.creationTime = creationTime;
    }

    public String getSerializedProperties() {
        return serializedProperties;
    }

    public void setSerializedProperties(String serializedProperties) {
        this.serializedProperties = serializedProperties;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public UserGroup getGroup() {
        return userGroup;
    }

    public void setGroup(UserGroup userGroup) {
        this.userGroup = userGroup;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Set<UserType> getUserTypeSet() {
        return userTypeSet;
    }

    public void setUserTypeSet(Set<UserType> userTypeSet) {
        this.userTypeSet = userTypeSet;
    }

    public Set<UserFunction> getUserFunctionSet() {
        return userFunctionSet;
    }

    public void setUserFunctionSet(Set<UserFunction> userFunctionSet) {
        this.userFunctionSet = userFunctionSet;
    }

    public Set<UserRole> getUserRoleSet() {
        return userRoleSet;
    }

    public void setUserRoleSet(Set<UserRole> userRoleSet) {
        this.userRoleSet = userRoleSet;
    }

    public UserGroup getUserGroup() {
        return userGroup;
    }

    public void setUserGroup(UserGroup userGroup) {
        this.userGroup = userGroup;
    }

    public Set<Project> getProjectSet() {
        return projectSet;
    }

    public void setProjectSet(Set<Project> projectSet) {
        this.projectSet = projectSet;
    }

    public UserAccount(String login, String passwordHash) {
        this.login = login;
        this.passwordHash = passwordHash;
    }

    public Contract getContract() {
        return contract;
    }

    public void setContract(Contract contract) {
        this.contract = contract;
    }

    public Set<Intervention> getInterventionSet() {
        return interventionSet;
    }

    public void setInterventionSet(Set<Intervention> interventionSet) {
        this.interventionSet = interventionSet;
    }

    public Set<Product> getProductSet() {
        return productSet;
    }

    public void setProductSet(Set<Product> productSet) {
        this.productSet = productSet;
    }

    public Set<RequestPurchase> getRequestPurchaseSet() {
        return requestPurchaseSet;
    }

    public void setRequestPurchaseSet(Set<RequestPurchase> requestPurchaseSet) {
        this.requestPurchaseSet = requestPurchaseSet;
    }

    public UserAccount() {
    }

    @Override
    public String toString() {
        return "User{" +
                "login='" + login + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", mail='" + mail + '\'' +
                '}';
    }
}
