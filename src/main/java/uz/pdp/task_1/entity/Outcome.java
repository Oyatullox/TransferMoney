package uz.pdp.task_1.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.boot.orm.jpa.hibernate.SpringImplicitNamingStrategy;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Outcome {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(optional = false)
    private Card fromCard;

    @ManyToOne(optional = false)
    private Card toCard;

    @Column(nullable = false)
    private Double amount;

    @CreationTimestamp
    private Timestamp timestamp;

    @Column(nullable = false)
    private Double commissionAmount;

    public Outcome(Card fromCard, Card toCard, Double amount, Double commissionAmount) {
        this.fromCard = fromCard;
        this.toCard = toCard;
        this.amount = amount;
        this.commissionAmount = commissionAmount;
    }
}
