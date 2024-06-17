package ru.mts.graduation_project.deposit.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Objects;

/**
 * Сущность, представляющая статус запроса.
 */
@Getter
@Setter
@Entity
@Table(schema = "bank", name = "request_statuses")
public class RequestStatuses {

    @Id
    @Column(name = "id_request_status")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer idRequestStatus;

    @Column(name = "request_status_name", length = 21)
    private String requestStatusName;

    @OneToMany(mappedBy = "requestStatus")
    private List<CurrentRequestStatus> currentRequestStatus;

    public RequestStatuses() {
    }

    public RequestStatuses(String requestStatusName, List<CurrentRequestStatus> currentRequestStatus) {
        this.requestStatusName = requestStatusName;
        this.currentRequestStatus = currentRequestStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RequestStatuses that = (RequestStatuses) o;
        return Objects.equals(idRequestStatus, that.idRequestStatus) && Objects.equals(requestStatusName, that.requestStatusName) && Objects.equals(currentRequestStatus, that.currentRequestStatus);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idRequestStatus, requestStatusName, currentRequestStatus);
    }

    @Override
    public String toString() {
        return "RequestStatuses{" +
                "idRequestStatus=" + idRequestStatus +
                ", requestStatusName='" + requestStatusName + '\'' +
                ", currentRequestStatus=" + currentRequestStatus +
                '}';
    }
}
