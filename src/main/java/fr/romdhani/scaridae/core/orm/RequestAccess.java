package fr.romdhani.scaridae.core.orm;

import fr.romdhani.scaridae.core.orm.enums.ReqGroup;
import fr.romdhani.scaridae.core.orm.enums.Labels;
import fr.romdhani.scaridae.core.orm.enums.Priority;
import fr.romdhani.scaridae.utils.version.UUIDVersion;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Set;


@Entity
@Table(name = "request_access")
public class RequestAccess implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "ref")
    private String ref = UUIDVersion.get().toString();

    @Column(name = "description")
    private String description;

    @Column(name = "reporter")
    private String reporter;

    @Column(name = "assignee")
    private String assignee;

    @Column(name = "request_group")
    private String requestGroup ;

    @Column(name = "request_priority")
    private String requestPriority ;

    @Column(name = "request_label")
    private String requestLabel;

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
    @JoinColumn(name = "response_access_id")
    ResponseAccess responseAccess;

    @ManyToMany(mappedBy = "requestAccessSet")
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

    public String getRequestGroup() {
        return requestGroup;
    }

    public void setRequestGroup(String requestGroup) {
        this.requestGroup = requestGroup;
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

    public ResponseAccess getResponseAccess() {
        return responseAccess;
    }

    public void setResponseAccess(ResponseAccess responseAccess) {
        this.responseAccess = responseAccess;
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

    public RequestAccess() {
    }

    public RequestAccess(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getRequestPriority() {
        return requestPriority;
    }

    public void setRequestPriority(Priority priority) {
        this.requestPriority = priority.getName();
    }

    public void setRequestPriority(String requestPriority) {
        this.requestPriority = requestPriority;
    }

    public String getRequestLabel() {
        return requestLabel;
    }

    public void setRequestLabel(Labels label) {
        this.requestLabel = label.name();
    }

    public void setRequestLabel(String requestLabel) {
        this.requestLabel = requestLabel;
    }

    public String getReporter() {
        return reporter;
    }

    public void setReporter(String reporter) {
        this.reporter = reporter;
    }

    public String getAssignee() {
        return assignee;
    }

    public void setAssignee(String assignee) {
        this.assignee = assignee;
    }

    public RequestAccess(String name, String reporter, String assignee, String requestPriority, String requestLabel) {
        this.name = name;
        this.reporter = reporter;
        this.assignee = assignee;
        this.requestPriority = requestPriority;
        this.requestLabel = requestLabel;
    }

    public RequestAccess(String name, String reporter, String requestPriority) {
        this.name = name;
        this.reporter = reporter;
        this.requestPriority = requestPriority;
    }

    @Override
    public String toString() {
        return "RequestAccess{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", type='" + requestGroup + '\'' +
                '}';
    }
}
















































