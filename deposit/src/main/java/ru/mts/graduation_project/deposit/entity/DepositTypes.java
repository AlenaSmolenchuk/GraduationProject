package ru.mts.graduation_project.deposit.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Objects;

/**
 * Сущность, представляющая тип вклада.
 */
@Getter
@Setter
@Entity
@Table(schema = "bank", name = "deposits_types")
public class DepositTypes {

    @Id
    @Column(name = "id_deposit_type")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer idDepositType;

    @Column(name = "deposit_type_name")
    private String depositTypeName;

    @OneToMany(mappedBy = "depositType")
    private List<Deposit> deposits;

    public DepositTypes(String depositTypeName, List<Deposit> deposits) {
        this.depositTypeName = depositTypeName;
        this.deposits = deposits;
    }

    public DepositTypes(String depositTypeName) {
        this.depositTypeName = depositTypeName;
    }

    public DepositTypes() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DepositTypes that = (DepositTypes) o;
        return Objects.equals(idDepositType, that.idDepositType) && Objects.equals(depositTypeName, that.depositTypeName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idDepositType, depositTypeName);
    }

    @Override
    public String toString() {
        return "DepositTypes{" +
                "idDepositType=" + idDepositType +
                ", depositTypeName='" + depositTypeName + '\'' +
                '}';
    }
}
