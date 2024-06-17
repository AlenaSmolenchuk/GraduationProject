package ru.mts.graduation_project.deposit.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

/**
 * Сущность, представляющая запрос на открытие вклада.
 */
@Getter
@Setter
@Entity
@Table(schema = "bank", name = "requests")
public class Request {

    @Id
    @Column(name = "id_request")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idRequest;


    @Column(name = "customer_id")
    private Integer customerId;

    @Column(name = "request_date")
    @Temporal(TemporalType.DATE)
    private Date requestDate;

    @ManyToOne
    @JoinColumn(name = "deposit_id", referencedColumnName = "id_deposit")
    private Deposit deposit;

    @OneToMany(mappedBy = "request")
    private List<CurrentRequestStatus> currentRequestStatuses;

    public Request() {
    }

    public Request(Integer customerId, Date requestDate, Deposit deposit, List<CurrentRequestStatus> currentRequestStatuses) {
        this.customerId = customerId;
        this.requestDate = requestDate;
        this.deposit = deposit;
        this.currentRequestStatuses = currentRequestStatuses;
    }
}
