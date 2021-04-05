package fr.romdhani.scaridae.core.orm;

import fr.romdhani.scaridae.utils.version.UUIDVersion;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Set;


@Entity
@Table(name = "request_access")
@NamedQueries({
        @NamedQuery(name = "findRequestAccessByUser", query = "select ra from fr.romdhani.scaridae.core.orm.RequestAccess ra join ra.userAccountSet as ua where (ua = :user)"
        )})
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
    private String group;

    @Column(name = "priority")
    private String priority;

    @Column(name = "label")
    private String label;

    @Column(name = "status")
    String status;

    @Column(name = "creation_timestamp")
    private Timestamp creationTime = new Timestamp(new Date().getTime());

    @Column(name = "last_modification_time")
    private Timestamp lastModificationTime = new Timestamp(new Date().getTime());

    @Column(name = "serialized_properties")
    private String serializedProperties;

    @OneToOne
    @JoinColumn(name = "request_type_id")
    RequestType requestType;


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

    public RequestAccess(String name, String reporter, String assignee, String priority, String label) {
        this.name = name;
        this.reporter = reporter;
        this.assignee = assignee;
        this.priority = priority;
        this.label = label;
    }

    public RequestAccess(String name, String reporter, String requestPriority) {
        this.name = name;
        this.reporter = reporter;
        this.priority = requestPriority;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "RequestAccess{" +
                "name='" + name + '\'' +
                ", ref='" + ref + '\'' +
                ", description='" + description + '\'' +
                ", reporter='" + reporter + '\'' +
                ", assignee='" + assignee + '\'' +
                ", priority='" + priority + '\'' +
                '}';
    }
}
















































