package ru.mts.graduation_project.deposit.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Сущность, представляющая информацию о депозите.
 */
@Getter
@Setter
@Entity
@Table(schema = "bank", name = "deposits")
public class Deposit {

    @Id
    @Column(name = "id_deposit")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idDeposit;

    @Column(name = "deposit_account_id")
    private Integer depositAccountId;

    @ManyToOne
    @JoinColumn(name = "deposit_type_id", referencedColumnName = "id_deposit_type")
    private DepositTypes depositType;

    @Column(name = "deposit_refill")
    private Boolean depositRefill;

    @Column(name = "deposit_amount")
    private BigDecimal depositAmount;

    @Column(name = "start_date")
    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date startDate;

    @Column(name = "end_date")
    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date endDate;

    @Column(name = "deposit_rate", columnDefinition = "DECIMAL(4, 2)")
    private BigDecimal depositRate;

    @ManyToOne
    @JoinColumn(name = "type_percent_payment_id", referencedColumnName = "id_type_percent_payment")
    private TypesPercentPayment typePercentPayment;

    @Column(name = "percent_payment_date")
    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date percentPaymentDate;

    @Column(name = "capitalization")
    private Boolean capitalization;

    public Deposit() {
    }

    public Deposit(Integer depositAccountId,
                   DepositTypes depositType,
                   Boolean depositRefill,
                   BigDecimal depositAmount,
                   Date startDate,
                   Date endDate,
                   BigDecimal depositRate,
                   TypesPercentPayment typePercentPayment,
                   Date percentPaymentDate,
                   Boolean capitalization) {
        this.depositAccountId = depositAccountId;
        this.depositType = depositType;
        this.depositRefill = depositRefill;
        this.depositAmount = depositAmount;
        this.startDate = startDate;
        this.endDate = endDate;
        this.depositRate = depositRate;
        this.typePercentPayment = typePercentPayment;
        this.percentPaymentDate = percentPaymentDate;
        this.capitalization = capitalization;
    }

    @Override
    public String toString() {
        return "Deposit{" +
                "idDeposit=" + idDeposit +
                ", depositAccountId=" + depositAccountId +
                ", depositType=" + depositType +
                ", depositRefill=" + depositRefill +
                ", depositAmount=" + depositAmount +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", depositRate=" + depositRate +
                ", typePercentPayment=" + typePercentPayment +
                ", percentPaymentDate=" + percentPaymentDate +
                ", capitalization=" + capitalization +
                '}';
    }
}
