package ru.mts.graduation_project.deposit.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


import java.time.OffsetDateTime;
import java.util.Objects;

/**
 * Сущность, представляющая текущий статус запроса.
 */
@Getter
@Setter
@Entity
@Table(schema = "bank", name = "current_request_status")
public class CurrentRequestStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idCurrentRequestStatus;

    @ManyToOne
    @JoinColumn(name = "request_id", referencedColumnName = "id_request")
    private Request request;

    @ManyToOne
    @JoinColumn(name = "request_status_id", referencedColumnName = "id_request_status")
    private RequestStatuses requestStatus;

    @Column(name = "change_datetime", columnDefinition = "TIME WITH TIME ZONE")
    private OffsetDateTime changeDatetime;

    public CurrentRequestStatus(Request request, RequestStatuses requestStatus, OffsetDateTime changeDatetime) {
        this.request = request;
        this.requestStatus = requestStatus;
        this.changeDatetime = changeDatetime;
    }

    public CurrentRequestStatus() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CurrentRequestStatus that = (CurrentRequestStatus) o;
        return Objects.equals(idCurrentRequestStatus, that.idCurrentRequestStatus) && Objects.equals(request, that.request) && Objects.equals(requestStatus, that.requestStatus) && Objects.equals(changeDatetime, that.changeDatetime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idCurrentRequestStatus, request, requestStatus, changeDatetime);
    }

    @Override
    public String toString() {
        return "CurrentRequestStatus{" +
                "idCurrentRequestStatus=" + idCurrentRequestStatus +
                ", request=" + request +
                ", requestStatus=" + requestStatus +
                ", changeDatetime=" + changeDatetime +
                '}';
    }
}
