package fr.romdhani.scaridae.core.orm;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Set;


@Entity
@Table(name = "request_purchase")
public class RequestPurchase implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "ref")
    private String ref;

    @Column(name = "description")
    private String description;

    @Column(name = "type")
    private String type;

    @Column(name = "creation_time")
    private Timestamp creationTime = new Timestamp(new Date().getTime());

    @Column(name = "last_modification_time")
    private Timestamp lastModificationTime = new Timestamp(new Date().getTime());

    @Column(name = "serialized_properties")
    private String serializedProperties;

    @OneToOne
    @JoinColumn(name = "request_type_id")
    RequestType requestType;

    @OneToOne
    @JoinColumn(name = "status_id")
    RequestStatus requestStatus;

    @OneToOne
    @JoinColumn(name = "response_purchase_id")
    ResponsePurchase responsePurchase;

    @ManyToMany(mappedBy = "requestPurchaseSet")
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Timestamp getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Timestamp creationTime) {
        this.creationTime = creationTime;
    }

    public Timestamp getLastModificationTime() {
        return lastModificationTime;
    }

    public void setLastModificationTime(Timestamp lastModificationTime) {
        this.lastModificationTime = lastModificationTime;
    }

    public RequestType getRequestType() {
        return requestType;
    }

    public void setRequestType(RequestType requestType) {
        this.requestType = requestType;
    }

    public RequestStatus getRequestStatus() {
        return requestStatus;
    }

    public void setRequestStatus(RequestStatus requestStatus) {
        this.requestStatus = requestStatus;
    }

    public ResponsePurchase getResponsePurchase() {
        return responsePurchase;
    }

    public void setResponsePurchase(ResponsePurchase responsePurchase) {
        this.responsePurchase = responsePurchase;
    }

    public Set<UserAccount> getUserAccountSet() {
        return userAccountSet;
    }

    public void setUserAccountSet(Set<UserAccount> userAccountSet) {
        this.userAccountSet = userAccountSet;
    }

    public String getSerializedProperties() {
        return serializedProperties;
    }

    public void setSerializedProperties(String serializedProperties) {
        this.serializedProperties = serializedProperties;
    }

    public RequestPurchase() {
    }

    public RequestPurchase(String name, String description) {
        this.name = name;
        this.description = description;
    }

    @Override
    public String toString() {
        return "RequestPurchase{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
















































