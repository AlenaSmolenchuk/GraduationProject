package ru.mts.graduation_project.account.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

/**
 * Сущность банковского счета.
 */
@Getter
@Setter
@ToString
@Entity
@Table(schema = "bank", name = "bank_accounts")
public class BankAccount {

    @Id
    @Column(name = "id_bank_account")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idBankAccount;

    @Column(name = "num_bank_account", columnDefinition = "NUMERIC(20,0)")
    private BigDecimal numBankAccount;

    @Column(name = "amount", columnDefinition = "MONEY")
    private BigDecimal amount;

    public BankAccount(BigDecimal numBankAccount, BigDecimal amount) {
        this.numBankAccount = numBankAccount;
        this.amount = amount;
    }

    public BankAccount() {
    }
}
