package co.uk.virginmoneyexercise.entity;

import co.uk.virginmoneyexercise.entity.base.BaseEntity;
import co.uk.virginmoneyexercise.enums.TransactionType;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Table(name = "transaction")
public class Transaction extends BaseEntity {

    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "transaction_date")
    private LocalDate transactionDate;

    @Column(name = "vendor")
    private String vendor;

    @Column(name = "transaction_type")
    private TransactionType type;

    @Column(name = "amount")
    private BigDecimal amount;

    @Column(name = "category")
    private String category;
}