package fr.romdhani.scaridae.core.orm;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;


@Entity
@Table(name = "response_purchase")
public class ResponsePurchase implements Serializable {
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

    @Column(name = "creation_time")
    private Timestamp creationTime = new Timestamp(new Date().getTime());

    @Column(name = "last_modification_time")
    private Timestamp lastModificationTime = new Timestamp(new Date().getTime());

    @Column(name = "serialized_properties")
    private String serializedProperties;

    @OneToOne
    @JoinColumn(name = "user_account_id")
    UserAccount owner;

    @OneToOne
    @JoinColumn(name = "status_id")
    RequestStatus requestStatus;

    @OneToOne
    @JoinColumn(name = "response_purchase_id")
    ResponsePurchase responsePurchase;

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

    public String getSerializedProperties() {
        return serializedProperties;
    }

    public void setSerializedProperties(String serializedProperties) {
        this.serializedProperties = serializedProperties;
    }

    public UserAccount getOwner() {
        return owner;
    }

    public void setOwner(UserAccount owner) {
        this.owner = owner;
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

    public ResponsePurchase() {
    }

    public ResponsePurchase(String name, String description) {
        this.name = name;
        this.description = description;
    }

    @Override
    public String toString() {
        return "ResponsePurchase{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
















































