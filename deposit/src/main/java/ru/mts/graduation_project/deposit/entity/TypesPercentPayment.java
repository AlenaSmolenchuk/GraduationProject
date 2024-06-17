package ru.mts.graduation_project.deposit.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Objects;

/**
 * Сущность, представляющая тип процентной ставки.
 */
@Getter
@Setter
@Entity
@Table(schema = "bank", name = "types_percent_payment")
public class TypesPercentPayment {

    @Id
    @Column(name = "id_type_percent_payment")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer idTypePercentPayment;

    @Column(name = "type_percent_payment_period")
    private String typePercentPaymentPeriod;

    @OneToMany(mappedBy = "typePercentPayment")
    private List<Deposit> deposits;

    public TypesPercentPayment(String typePercentPaymentPeriod, List<Deposit> deposits) {
        this.typePercentPaymentPeriod = typePercentPaymentPeriod;
        this.deposits = deposits;
    }

    public TypesPercentPayment(String typePercentPaymentPeriod) {
        this.typePercentPaymentPeriod = typePercentPaymentPeriod;
    }

    public TypesPercentPayment() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TypesPercentPayment that = (TypesPercentPayment) o;
        return Objects.equals(idTypePercentPayment, that.idTypePercentPayment) && Objects.equals(typePercentPaymentPeriod, that.typePercentPaymentPeriod);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idTypePercentPayment, typePercentPaymentPeriod);
    }

    @Override
    public String toString() {
        return "TypesPercentPayment{" +
                "idTypePercentPayment=" + idTypePercentPayment +
                ", typePercentPaymentPeriod='" + typePercentPaymentPeriod + '\'' +
                '}';
    }
}
